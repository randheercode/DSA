package problem.old.lc_contest.w.w191

/**
 * Created by randheercode
 * Date: 31/5/20
 * Time: 11:53 am
 * https://leetcode.com/contest/weekly-contest-191/problems/maximum-area-of-a-piece-of-cake-after-horizontal-and-vertical-cuts/
 */
class MaxArea {
    fun maxArea(h: Int, w: Int, horizontalCuts: IntArray, verticalCuts: IntArray): Int {
        val mod = 1000000007
        val vc = mutableListOf<Int>().apply {
            add(0)
            addAll(horizontalCuts.toSortedSet())
            add(h)
        }
        val hc = mutableListOf<Int>().apply {
            add(0)
            addAll(verticalCuts.toSortedSet())
            add(w)
        }

        fun findMax(cuts: List<Int>): Int {
            var max = Int.MIN_VALUE
            for (i in 0 until cuts.lastIndex) {
                max = maxOf(max, cuts[i + 1] - cuts[i])
            }
            return max
        }

        return (findMax(vc).toLong() * findMax(hc).toLong()).rem(mod).toInt()
    }

}

fun main() {
    println(MaxArea().maxArea(5, 4, intArrayOf(3, 1), intArrayOf(1)))
    println(MaxArea().maxArea(5, 4, intArrayOf(3), intArrayOf(3)))
}