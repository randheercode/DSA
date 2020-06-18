package problems

/**
 * Created by randheercode
 * Date: 18/6/20
 * Time: 7:39 pm
 */
class HIndex2 {
    fun hIndex1(citations: IntArray): Int {
        val n: Int = citations.size
        var s = 0
        var e = n - 1

        while (s < e) {
            val m = (s + e) / 2
            if (citations[m] < n - m) {
                s = m + 1
            } else {
                e = m
            }
        }

        return if (s < n && citations[s] >= n - s) n - s else 0
    }

    fun hIndex(citations: IntArray): Int {
        var l = 0
        var r = citations.size - 1
        while (l <= r) {
            val mid = (l + r) / 2
            val cit = citations[mid]
            val h1 = citations.size - mid
            if (cit < h1) {
                l = mid + 1
                continue
            }
            if (mid > 0 && citations[mid - 1] >= h1 + 1) {
                r = mid - 1
                continue
            }
            return h1
        }
        return 0
    }

    fun hIndexNotSorted(citations: IntArray): Int {
        citations.sort()
        var l = 0
        var r = citations.size - 1
        while (l <= r) {
            val mid = (l + r) / 2
            val cit = citations[mid]
            val h1 = citations.size - mid
            if (cit < h1) {
                l = mid + 1
                continue
            }
            if (mid > 0 && citations[mid - 1] >= h1 + 1) {
                r = mid - 1
                continue
            }
            return h1
        }
        return 0
    }
}