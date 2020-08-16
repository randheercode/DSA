package problem.lc_contest.w.w200

/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun countGoodTriplets(arr: IntArray, a: Int, b: Int, c: Int): Int {
        var count = 0
        for (i in arr.indices) {
            for (j in i until arr.size) {
                for (k in j until arr.size) {
                    val f = Math.abs(arr[i] - arr[j]) <= a
                    val s = Math.abs(arr[j] - arr[k]) <= b
                    val t = Math.abs(arr[i] - arr[k]) <= c
                    if (f && s && t && k > j && j > i) count++
                }
            }
        }
        return count
    }
}

private fun test1() {
    println(Prob1().countGoodTriplets(intArrayOf(3, 0, 1, 1, 9, 7), 7, 2, 3))
}

private class Prob2 {
    fun getWinner(arr: IntArray, k: Int): Int {
        var i = 1
        var maxSoFar = arr[0]
        while (i < arr.size) {
            if (maxSoFar > arr[i]) {
                i++
                continue
            }
            maxSoFar = arr[i]
            var j = i + 1
            var count = 1
            if (count == k) return arr[i]
            while (j < arr.size) {
                if (arr[i] > arr[j]) {
                    count++
                    if (count == k) return arr[i]
                }
                j++
            }
            i++
        }

        return arr.max()!!
    }
}

private fun test2() {
    println(Prob2().getWinner(intArrayOf(2, 1, 3, 5, 4, 6, 7), 2))
    println(Prob2().getWinner(intArrayOf(3, 2, 1), 10))
    println(Prob2().getWinner(intArrayOf(1, 9, 8, 2, 3, 7, 6, 4, 5), 7))
    println(Prob2().getWinner(intArrayOf(1, 11, 22, 33, 44, 55, 66, 77, 88, 99), 1000000000))
    println(Prob2().getWinner(intArrayOf(1,25,35,42,68,70), 1))
}

private class Prob3 {

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
