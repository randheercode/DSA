package problem.old

/**
 * Created by randheercode
 * Date: 15/5/20
 * Time: 4:29 pm
 */
class Trie() {

    /** Initialize your data structure here. */
    private val hashSet: MutableSet<String> = hashSetOf()

    /** Inserts a word into the trie. */
    fun insert(word: String) {
        hashSet.add(word)
    }

    /** Returns if the word is in the trie. */
    fun search(word: String): Boolean {
        return hashSet.contains(word)
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    fun startsWith(prefix: String): Boolean {
        return hashSet.find { it.startsWith(prefix) } != null
    }

}

fun main() {
    val trie = Trie()

    trie.insert("apple")
    println(trie.search("apple")) // returns true

    println(trie.search("app")) // returns false

    println(trie.startsWith("app")) // returns true

    trie.insert("app")
    println(trie.search("app")) // returns true

}