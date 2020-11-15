package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 15/11/20
 * Time: 11:40 am
 */
class RemoveSubFolders {
    class TrieNode {
        var children = HashMap<String, TrieNode?>()
        var word: String? = null
    }

    private val parentFolders: MutableList<String?> = ArrayList()
    private fun getResults(root: TrieNode?) {
        for ((_, value) in root!!.children) {
            if (value!!.word != null) {
                parentFolders.add(value.word)
            } else {
                getResults(value)
            }
        }
    }

    fun removeSubfolders(folders: Array<String>): List<String?>? {
        val root = TrieNode()
        for (folder in folders) {
            val parts = folder.substring(1).split("/".toRegex()).toTypedArray()
            var curr: TrieNode? = root
            for (part in parts) {
                if (!curr!!.children.containsKey(part)) {
                    curr.children[part] = TrieNode()
                }
                curr = curr.children[part]
            }
            curr!!.word = folder
        }
        parentFolders.clear()
        getResults(root)
        return parentFolders
    }
}