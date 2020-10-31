package problem.lc_contest

class BW38 {

    fun frequencySort(nums: IntArray): IntArray {
        val freqMap = mutableMapOf<Int, Int>()
        for (num in nums) freqMap[num] = freqMap.getOrDefault(num, 0) + 1
        val sortedNum = nums.sortedWith(Comparator { a, b ->
            if (freqMap[a]!! != freqMap[b]) {
                freqMap[a]!! - freqMap[b]!!
            } else {
                b - a
            }
        })
        return sortedNum.toIntArray()
    }

    fun maxWidthOfVerticalArea(points: Array<IntArray>): Int {
        points.sortBy { it[0] }
        var ans = 0
        for (i in 1 until points.size) {
            ans = maxOf(ans, points[i][0] - points[i - 1][0])
        }
        return ans
    }

    fun countSubstrings(s: String, t: String): Int {
        var ans = 0
        for (i in s.indices) {
            for (j in t.indices) {
                var x = i
                var y = j
                var diff = 0
                while (x < s.length && y < t.length) {
                    if (s[x] != t[y]) diff++
                    if (diff == 1) ans++
                    if (diff == 2) break
                    x++
                    y++
                }
            }
        }
        return ans
    }

}

