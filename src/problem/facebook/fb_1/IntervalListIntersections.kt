package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 10/11/20
 * Time: 10:28 am
 */
class IntervalListIntersections {
    fun intervalIntersection(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
        var a = 0
        var b = 0
        val aLength = A.size
        val bLength = B.size
        val result = mutableListOf<IntArray>()

        fun common(first: IntArray, second: IntArray) = intArrayOf(maxOf(first[0], second[0]), minOf(first[1], second[1]))

        while (a < aLength && b < bLength) {
            val common = common(A[a], B[b])
            if (common[1] >= common[0]) {
                result.add(common)
            }
            if (common[1] >= A[a][1]) a += 1
            if (common[1] >= B[b][1]) b += 1
        }
        return result.toTypedArray()
    }
}