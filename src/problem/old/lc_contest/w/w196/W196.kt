package problem.old.lc_contest.w.w196

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun canMakeArithmeticProgression(arr: IntArray): Boolean {
        if (arr.size <= 2) return true
        arr.sort()
        val diff = Math.abs(arr[0] - arr[1])
        var i = 1
        while (i < arr.size) {
            val currentDiff = Math.abs(arr[i] - arr[i - 1])
            if (currentDiff != diff) return false
            i++
        }
        return true
    }
}

private fun test1() {
    println(Prob1().canMakeArithmeticProgression(intArrayOf(3, 5, 1)))
    println(Prob1().canMakeArithmeticProgression(intArrayOf(1, 2, 4)))
}

private class Prob2 {
    fun getLastMoment(n: Int, left: IntArray, right: IntArray): Int {
        if (left.isEmpty() && right.isEmpty()) return 0
        if (left.isEmpty()) return n - right.min()!!
        if (right.isEmpty()) return left.max()!!
        return maxOf(left.max()!!, n - right.min()!!)
    }
}

private fun test2() {
    println(Prob2().getLastMoment(4, intArrayOf(4, 3), intArrayOf(0, 1)))
    println(Prob2().getLastMoment(7, intArrayOf(), intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)))
    println(Prob2().getLastMoment(7, intArrayOf(0, 1, 2, 3, 4, 5, 6, 7), intArrayOf()))
    println(Prob2().getLastMoment(5, intArrayOf(5), intArrayOf(4)))
    println(Prob2().getLastMoment(6, intArrayOf(6), intArrayOf(0)))
    println(Prob2().getLastMoment(20, intArrayOf(4, 7, 15), intArrayOf(9, 3, 13, 10)))
}

private class Prob3 {
    fun numSubmat(mat: Array<IntArray>): Int {
        var count = 0
        for (i in mat.indices) {
            for (j in mat[0].indices) {
                if (i > 0 && j > 0 && mat[i][j] > 0)
                    mat[i][j] = minOf(mat[i - 1][j - 1], mat[i][j - 1], mat[i - 1][j]) + 1
                count += mat[i][j]
            }
        }
        return count
    }
}

private fun test3() {
    println(Prob3())
}

private class Prob4 {

}

private fun test4() {
    println(Prob4())
}

fun main() {
    when (2) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
