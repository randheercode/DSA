package problem.facebook

// https://leetcode.com/problems/shuffle-string/
class ShuffleString {
    fun restoreString(s: String, indices: IntArray): String {
        val result = StringBuilder(s)
        for (i in 0..indices.lastIndex) {
            result[indices[i]] = s[i]
        }
        return result.toString()
    }

    fun restoreStringOptimal(s: String, indices: IntArray): String {
        val result = StringBuilder(s)
        fun swap(i: Int, j: Int) {

            val temp = result[i]
            val tempIdx = indices[i]

            result[i] = result[j]
            indices[i] = indices[j]

            result[j] = temp
            indices[j] = tempIdx
        }
        for (i in 0..indices.lastIndex) {
            while (indices[i] != i) {
                swap(i, indices[i])
            }
        }
        return result.toString()
    }

    fun restoreStringBestSubmission(s: String, indices: IntArray): String {
        val array = s.toCharArray()

        val result = CharArray(array.size)

        for (i in array.indices) {
            result[indices[i]] = array[i]
        }

        return String(result)
    }
}

fun main() {
    println(ShuffleString().restoreString("codeleet", intArrayOf(4, 5, 6, 7, 0, 2, 1, 3)))
    println(ShuffleString().restoreStringOptimal("codeleet", intArrayOf(4, 5, 6, 7, 0, 2, 1, 3)))
    println(ShuffleString().restoreStringBestSubmission("codeleet", intArrayOf(4, 5, 6, 7, 0, 2, 1, 3)))
}