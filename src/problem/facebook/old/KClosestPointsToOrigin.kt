package problem.facebook.old


// https://leetcode.com/problems/k-closest-points-to-origin/
class KClosestPointsToOrigin {
    fun kClosest(points: Array<IntArray>, K: Int): Array<IntArray> {
        val len = points.size
        var l = 0
        var r = len - 1
        while (l <= r) {
            val mid = helper(points, l, r)
            if (mid == K) break
            if (mid < K) {
                l = mid + 1
            } else {
                r = mid - 1
            }
        }
        return points.copyOfRange(0, K)
    }

    private fun helper(A: Array<IntArray>, l: Int, r: Int): Int {
        var l = l
        var r = r
        val pivot = A[l]
        while (l < r) {
            while (l < r && compare(A[r], pivot) >= 0) r--
            A[l] = A[r]
            while (l < r && compare(A[l], pivot) <= 0) l++
            A[r] = A[l]
        }
        A[l] = pivot
        return l
    }

    private fun compare(p1: IntArray, p2: IntArray): Int {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]
    }
}

fun main() {
    println(KClosestPointsToOrigin().kClosest(arrayOf(
            intArrayOf(1, 3),
            intArrayOf(-2, 2)
    ), 1).toList())
}