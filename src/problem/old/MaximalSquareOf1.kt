package problem.old

/**
 * Created by randheercode
 * Date: 27/4/20
 * Time: 10:33 pm
 * Problem Statement: Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 */
class MaximalSquareOf1 {
    fun maximalSquare(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty()) return 0
        val rows = matrix.size
        val cols: Int = matrix[0].size
        val mat = Array(rows + 1) { IntArray(cols + 1) }
        var max = 0
        for (i in matrix.indices) {
            for (j in matrix[0].indices) {
                if (matrix[i][j] == '1' && i > 0 && j > 0) {
                    mat[i][j] = Math.min(mat[i - 1][j - 1], Math.min(mat[i][j - 1], mat[i - 1][j])) + 1
                } else if (matrix[i][j] == '1') {
                    mat[i][j] = 1
                }
                max = Math.max(max, mat[i][j])
            }
        }
        return max * max
    }
}

fun main() {
    println(MaximalSquareOf1().maximalSquare(arrayOf(
            charArrayOf('1', '0', '1', '0', '0'),
            charArrayOf('1', '0', '1', '1', '1'),
            charArrayOf('1', '1', '1', '1', '1'),
            charArrayOf('1', '0', '0', '1', '0')
    )))
}