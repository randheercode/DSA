package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 13/11/20
 * Time: 10:41 am
 */
class ToeplitzMatrix {
    fun isToeplitzMatrix(matrix: Array<IntArray>): Boolean {
        for (r in matrix.indices)
            for (c in matrix[0].indices)
                if (r > 0 && c > 0 && matrix[r - 1][c - 1] != matrix[r][c]) return false
        return true
    }
}