package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 7:44 am
 */
class NumMatrixRangeSum(private val matrix: Array<IntArray>) {

    fun sumRegion(row1: Int, col1: Int, row2: Int, col2: Int): Int {
        var sum = 0
        for ( i in row1..row2) {
            for (j in col1..col2) {
                sum += matrix[i][j]
            }
        }
        return sum
    }

}