package problem.lc_contest.w.w192

/**
 * Created by randheercode
 * Date: 7/6/20
 * Time: 10:23 am
 */
class W192 {

    fun shuffle(nums: IntArray, n: Int): IntArray {
        val returnArray = IntArray(2 * n)
        for (i in 0 until n) {
            returnArray[2 * i] = nums[i]
            returnArray[2 * i + 1] = nums[n + i]
        }
        return returnArray
    }

    fun getStrongest(arr: IntArray, k: Int): IntArray {
        val m = arr.sortedBy { it }[arr.lastIndex.div(2)]
        val sorted = arr.sortedWith(Comparator { i, j ->
            val diff = Math.abs(i - m) - Math.abs(j - m)
            return@Comparator if (diff == 0) {
                i - j
            } else diff
        }).reversed()
        return sorted.subList(0, k).toIntArray()
    }

    class BrowserHistory(homepage: String) {
        val history = mutableListOf<String>()
        private var cpIndex = 0

        init {
            history.add(homepage)
        }

        fun visit(url: String) {
            cpIndex += 1
            history.add(cpIndex, url)
            while (history.size > cpIndex + 1) {
                history.removeAt(history.lastIndex)
            }
        }

        fun back(steps: Int): String {
            cpIndex = maxOf(0, cpIndex - steps)
            return history[cpIndex]
        }

        fun forward(steps: Int): String {
            cpIndex = minOf(history.lastIndex, cpIndex + steps)
            return history[cpIndex]
        }

    }

}

fun main() {
//    test1()
//    test2()
}

private fun test1() {
    println(W192().shuffle(intArrayOf(2, 5, 1, 3, 4, 7), 3).toList())
    println(W192().shuffle(intArrayOf(1, 1, 2, 2), 2).toList())
}

private fun test2() {
    println(W192().getStrongest(intArrayOf(1, 2, 3, 4, 5), 2).toList())
    println(W192().getStrongest(intArrayOf(1, 1, 3, 5, 5), 2).toList())
    println(W192().getStrongest(intArrayOf(6, 7, 11, 7, 6, 8), 5).toList())
    println(W192().getStrongest(intArrayOf(6, -3, 7, 2, 11), 3).toList())
    println(W192().getStrongest(intArrayOf(-7, 22, 17, 3), 2).toList())
    println(W192().getStrongest(intArrayOf(-2, -4, -6, -8, -9, -7, -5, -3, -1), 3).toList())
}
