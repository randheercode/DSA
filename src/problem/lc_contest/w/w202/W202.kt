package problem.lc_contest.w.w202

import java.util.*


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun threeConsecutiveOdds(arr: IntArray): Boolean {
        if (arr.size < 3) return false
        for (i in 0..arr.size.minus(3)) {
            if (arr[i].rem(2) != 0 && arr[i + 1].rem(2) != 0 && arr[i + 2].rem(2) != 0) return true
        }
        return false
    }
}

private fun test1() {
    println(Prob1().threeConsecutiveOdds(intArrayOf(2, 6, 4, 1)))
    println(Prob1().threeConsecutiveOdds(intArrayOf(1, 2, 34, 3, 4, 5, 7, 23, 12)))
    println(Prob1().threeConsecutiveOdds(intArrayOf(1, 1, 1)))
}

private class Prob2 {
    fun minOperations(n: Int): Int {
        if (n <= 1) return 0
        if (n == 2) return 1
        val step = n.div(2)
        val additive = if (n.rem(2) == 0) -1 else 0
        var result = 0
        for (i in 1..step) result += ((2 * i).plus(additive))
        return result
    }
}

private fun test2() {
    println(Prob2().minOperations(6))
}

private class Prob3 {
    fun maxDistance(position: IntArray, m: Int): Int {
        Arrays.sort(position) // sort the position so that they will be arranged in increasing order.
        val n = position.size
        /*
            l: left bound, set to minimum value 1.
            r: right bound, set to maximum value, extreme right position-extreme left position.
            mid: the gap to be checked for in the current iteration.
            cnt: the number of balls that can be placed.
            start: the position of the previous ball placed.
            i: for index of the array.
        */
        var l = 1L
        var r = (position[n - 1] - position[0]).toLong()
        var mid: Long
        var cnt: Long
        var start: Long
        var i: Int
        while (l <= r) {
            mid = (r - l) / 2L + l
            i = 0 // starting index
            cnt = 1L //the ball number to be placed.
            start = position[0].toLong() // first position is extreme left.
            while (i < n) {
                if (position[i] >= (start + mid)) { // if the distance between current position and the position in which the previous ball was placed is more than or equal to mid, then we place current ball in this position.
                    start = position[i].toLong() // updating the position of previously placed ball to current position.
                    cnt++ // increment ball number.
                }
                i++
            }
            if (cnt >= m) // all the balls can be placed and we might be able to increase the gap.
                l = mid + 1L else  // all the balls couldn't be placed and we have to decrease the gap.
                r = mid - 1L
        }
        return r.toInt()
    }
}

private fun test3() {
    println(Prob3())
}

private class Prob4 {
    fun minDays(n: Int): Int {
        val queue: Deque<Int> = ArrayDeque()
        queue.add(n)
        val visited: MutableSet<Int> = HashSet()
        var level = 0
        while (!queue.isEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val a = queue.remove()
                if (a == 0) return level
                if (!visited.contains(a - 1)) {
                    queue.addLast(a - 1)
                    visited.add(a - 1)
                }
                if (a % 2 == 0 && !visited.contains(a / 2)) {
                    val s = a - a / 2
                    queue.addLast(s)
                    visited.add(s)
                }
                if (a % 3 == 0) {
                    val s = a - 2 * (a / 3)
                    if (!visited.contains(s)) {
                        queue.addLast(s)
                        visited.add(s)
                    }
                }
            }
            level++
        }
        return level
    }
}

private fun test4() {
    println(Prob4().minDays(10))
    println(Prob4().minDays(6))
    println(Prob4().minDays(1))
    println(Prob4().minDays(56))
}

fun main() {
    when (4) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
