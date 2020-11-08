package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 8/11/20
 * Time: 1:13 pm
 */
class LongestSubstringKDistChar {
    fun lengthOfLongestSubstringKDistinct(s: String, k: Int): Int {
        val n = s.length
        if (n * k == 0) return 0
        var left = 0
        var right = 0
        val hashmap = HashMap<Char, Int>()
        var maxLen = 1
        while (right < n) {
            hashmap[s[right]] = right++
            if (hashmap.size == k + 1) {
                val delIdx = Collections.min(hashmap.values)
                hashmap.remove(s[delIdx])
                left = delIdx + 1
            }
            maxLen = maxOf(maxLen, right - left)
        }
        return maxLen
    }
}