package problem.old.lc_contest.bw.bw32

import java.util.*


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun findKthPositive(arr: IntArray, k: Int): Int {
        var curr = 0
        var missCount = 0
        var idx = 0
        while (missCount != k && idx < arr.size) {
            if (curr + 1 == arr[idx]) {
                idx++
            } else {
                missCount++
            }
            curr++
        }
        if (missCount != k) curr = curr.plus(k - missCount)
        return curr
    }
}

private fun test1() {
    println(Prob1().findKthPositive(intArrayOf(2, 3, 4, 7, 11), 5))
    println(Prob1().findKthPositive(intArrayOf(1, 2, 3, 4), 2))
    println(Prob1().findKthPositive(intArrayOf(2, 3, 4, 7, 11), 5))
}

private class Prob2 {
    fun canConvertString(s: String, t: String, k: Int): Boolean {
        if (s.length != t.length) {
            return false
        }
        val a = IntArray(26)
        for (i in s.indices) {
            var value = (t[i].toInt() + 26 - s[i].toInt()) % 26
            if (a[value] != 0) {
                value = a[value] + 26
            }
            a[value % 26] = value
            if (value > k) {
                return false
            }
        }
        return true
    }
}

private fun test2() {
    println(Prob2().canConvertString("input", "ouput", 9))
    println(Prob2().canConvertString("abc", "bcd", 10))
    println(Prob2().canConvertString("aab", "bbb", 27))
    println(Prob2().canConvertString("atmtxzjkz", "tvbtjhvjd", 35))
    println(Prob2().canConvertString("mpzzwh", "kaeblv", 24))
    println(Prob2().canConvertString("mygdwuntwkoc", "btydmdiatnhx", 48))
}

private class Prob3 {
    fun minInsertions(s: String): Int {
        var res = 0
        var left = 0
        var i = 0
        while (i < s.length) {
            if (s[i] == '(') { // when (
                left++
            } else if (i == s.length - 1 || s[i + 1] == '(') { // when single )
                if (left > 0) {
                    res++
                    left--
                } else {
                    res += 2
                }
            } else { // when double )
                if (left > 0) left-- else res++
                i++ // advance pointer since we have process double ) in a time
            }
            i++
        }
        res += left * 2
        return res
    }
}

private fun test3() {
    println(Prob3().minInsertions("(()))"))
    println(Prob3().minInsertions("())"))
    println(Prob3().minInsertions("))())("))
    println(Prob3().minInsertions("(((((("))
    println(Prob3().minInsertions(")))))))"))
}

private class Prob4 {
    fun longestAwesome(s: String): Int {
        val map: MutableMap<Int, Int?> = HashMap()
        map[0] = -1
        var res = 0
        var mask = 0
        for (i in s.indices) {
            mask = mask xor (1 shl s[i] - '0')
            if (!map.containsKey(mask)) map[mask] = i
            res = maxOf(res, i - map[mask]!!)
            var temp = mask
            for (j in 0..9) {
                temp = mask xor (1 shl j)
                if (map.containsKey(temp)) res = maxOf(res, i - map[temp]!!)
            }
        }
        return res
    }
}

private fun test4() {
    println(Prob4())
}

fun main() {
    when (3) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
