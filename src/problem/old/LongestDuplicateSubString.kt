package problem.old

import java.util.*

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
class Solution {
    var a = 26
    var mod = 1 shl 30

    fun longestDupSubstring(S: String): String {
        var lo = 1
        var hi = S.length - 1
        while (lo <= hi) {
            val mid = lo + (hi - lo) / 2
            val startIndex = search(S, mid)
            if (startIndex == -1) {
                hi = mid - 1
            } else {
                lo = mid + 1
            }
        }
        val startIndex = search(S, hi)
        return if (startIndex == -1) "" else S.substring(startIndex, startIndex + hi)
    }

    private fun search(S: String, len: Int): Int {
        var h: Long = 0
        var aL: Long = 1
        for (i in 0 until len) {
            h = (h * a % mod + S[i].toLong()) % mod
            aL = aL * a % mod
        }
        val visited = mutableMapOf<Long, MutableList<Int>>()
        visited[h] = ArrayList()
        visited[h]!!.add(0)
        for (i in 1 until S.length - len + 1) {
            h = ((h * a % mod - S[i - 1].toLong() * aL % mod + mod) % mod + S[i + len - 1].toLong()) % mod
            if (visited.containsKey(h)) {
                for (start in visited[h]!!) {
                    if (S.substring(start, start + len) == S.substring(i, i + len)) return i
                }
            } else {
                visited[h] = ArrayList()
            }
            visited[h]!!.add(i)
        }
        return -1
    }
}