package problem.facebook.fb_1


/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 11:26 am
 */
class TrieNode {
    var children: MutableMap<Char, TrieNode> = mutableMapOf()
    var word = false
}

class WordDictionary {

    var trie: TrieNode = TrieNode()


    fun addWord(word: String) {
        var node: TrieNode = trie
        for (ch in word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children[ch] = TrieNode()
            }
            node = node.children[ch]!!
        }
        node.word = true
    }


    private fun searchInNode(word: String, node: TrieNode): Boolean {
        var node = node
        for (i in word.indices) {
            val ch = word[i]
            node = if (!node.children.containsKey(ch)) {
                // if the current character is '.'
                // check all possible nodes at this level
                if (ch == '.') {
                    for (x in node.children.keys) {
                        val child = node.children[x]!!
                        if (searchInNode(word.substring(i + 1), child)) {
                            return true
                        }
                    }
                }
                // if no nodes lead to answer
                // or the current character != '.'
                return false
            } else {
                // if the character is found
                // go down to the next level in trie
                node.children[ch]!!
            }
        }
        return node.word
    }

    fun search(word: String): Boolean {
        return searchInNode(word, trie)
    }
}