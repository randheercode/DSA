import java.util.*

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
internal class TrieNode {
    var children = HashMap<Char, TrieNode?>()
    var word: String? = null
}

internal class Solution {
    inner class TrieNode {
        var children = HashMap<Char, TrieNode?>()
        var word: String? = null
    }
    var _board: Array<CharArray>? = null
    var _result = ArrayList<String?>()
    fun findWords(board: Array<CharArray>, words: Array<String>): List<String?> {

        // Step 1). Construct the Trie
        val root = TrieNode()
        for (word in words) {
            var node: TrieNode? = root
            for (letter in word.toCharArray()) {
                if (node!!.children.containsKey(letter)) {
                    node = node.children[letter]
                } else {
                    val newNode = TrieNode()
                    node.children[letter] = newNode
                    node = newNode
                }
            }
            node!!.word = word // store words in Trie
        }
        _board = board
        // Step 2). Backtracking starting for each cell in the board
        for (row in board.indices) {
            for (col in 0 until board[row].size) {
                if (root.children.containsKey(board[row][col])) {
                    backtracking(row, col, root)
                }
            }
        }
        return _result
    }

    private fun backtracking(row: Int, col: Int, parent: TrieNode?) {
        val letter = _board!![row][col]
        val currNode = parent!!.children[letter]

        // check if there is any match
        if (currNode!!.word != null) {
            _result.add(currNode.word)
            currNode.word = null
        }

        // mark the current letter before the EXPLORATION
        _board!![row][col] = '#'

        // explore neighbor cells in around-clock directions: up, right, down, left
        val rowOffset = intArrayOf(-1, 0, 1, 0)
        val colOffset = intArrayOf(0, 1, 0, -1)
        for (i in 0..3) {
            val newRow = row + rowOffset[i]
            val newCol = col + colOffset[i]
            if (newRow < 0 || newRow >= _board!!.size || newCol < 0 || newCol >= _board!![0].size) {
                continue
            }
            if (currNode.children.containsKey(_board!![newRow][newCol])) {
                backtracking(newRow, newCol, currNode)
            }
        }

        // End of EXPLORATION, restore the original letter in the board.
        _board!![row][col] = letter

        // Optimization: incrementally remove the leaf nodes
        if (currNode.children.isEmpty()) {
            parent.children.remove(letter)
        }
    }
}