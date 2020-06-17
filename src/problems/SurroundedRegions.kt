package problems

import generateCharArray
import printCharArray


/**
 * Created by randheercode
 * Date: 17/6/20
 * Time: 3:05 pm
 */
class SurroundedRegions {
    fun solve(board: Array<CharArray>) {
        if (board.isEmpty()) return

        fun dfs(board: Array<CharArray>, x: Int, y: Int) {
            if (x < 0 || x >= board[0].size || y < 0 || y >= board.size) return
            if (board[y][x] != 'O') return
            board[y][x] = 'T'
            dfs(board, x - 1, y)
            dfs(board, x + 1, y)
            dfs(board, x, y - 1)
            dfs(board, x, y + 1)
        }

        for (y in board.indices) {
            if (board[y][0] == 'O') dfs(board, 0, y)
            if (board[y][board[y].size - 1] == 'O') dfs(board, board[y].size - 1, y)
        }
        for (x in board[0].indices) {
            if (board[0][x] == 'O') dfs(board, x, 0)
            if (board[board.size - 1][x] == 'O') dfs(board, x, board.size - 1)
        }
        for (y in board.indices) {
            for (x in board[y].indices) {
                if (board[y][x] == 'T') board[y][x] = 'O' else board[y][x] = 'X'
            }
        }
    }

}

fun main() {
//    val input = generateCharArray("[[X,O,X,X],[X,O,O,X],[X,X,O,X],[X,O,X,X]]")
    val input = generateCharArray("[[O,X,X,O,X],[X,O,O,X,O],[X,O,X,O,X],[O,X,O,O,O],[X,X,O,X,O]]")
    printCharArray(input, "Input: ")
    SurroundedRegions().solve(input)
    printCharArray(input, "Output: ")
    printCharArray(generateCharArray("[[O,X,X,O,X],[X,X,X,X,O],[X,X,X,O,X],[O,X,O,O,O],[X,X,O,X,O]]"), "Expected: ")
}