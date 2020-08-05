package problems


/**
 * Created by randheercode
 * Date: 5/8/20
 * Time: 6:48 pm
 */
class TrieNode {
    var children: MutableMap<Char, TrieNode> = mutableMapOf()
    var word = false
}

class WordDictionary {
    var trie: TrieNode = TrieNode()

    fun addWord(word: String) {
        var node = trie
        for (ch in word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children[ch] = TrieNode()
            }
            node = node.children[ch]!!
        }
        node.word = true
    }

    /** Returns if the word is in the node.  */
    fun searchInNode(word: String, node: TrieNode): Boolean {
        var node = node
        for (i in word.indices) {
            val ch = word[i]
            node = if (!node.children.containsKey(ch)) {
                if (ch == '.') {
                    for (x in node.children.keys) {
                        val child = node.children[x]!!
                        if (searchInNode(word.substring(i + 1), child)) {
                            return true
                        }
                    }
                }
                return false
            } else {
                node.children[ch]!!
            }
        }
        return node.word
    }

    fun search(word: String): Boolean {
        return searchInNode(word, trie)
    }

}