package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 13/11/20
 * Time: 10:33 am
 */
class KthInSortedMatrix {
    fun kthSmallest(matrix: Array<IntArray>, k: Int): Int {
        val n = matrix.size
        var start = matrix[0][0]
        var end = matrix[n - 1][n - 1]
        while (start < end) {
            val mid = start + (end - start) / 2
            // first number is the smallest and the second number is the largest
            val smallLargePair = intArrayOf(matrix[0][0], matrix[n - 1][n - 1])
            val count = countLessEqual(matrix, mid, smallLargePair)
            if (count == k) return smallLargePair[0]
            if (count < k) start = smallLargePair[1] // search higher
            else end = smallLargePair[0] // search lower
        }
        return start
    }

    private fun countLessEqual(matrix: Array<IntArray>, mid: Int, smallLargePair: IntArray): Int {
        var count = 0
        val n = matrix.size
        var row = n - 1
        var col = 0
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {

                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col])
                row--
            } else {

                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col])
                count += row + 1
                col++
            }
        }
        return count
    }
}