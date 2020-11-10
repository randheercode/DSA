package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 10/11/20
 * Time: 9:52 am
 */
class SparseMatrixMultiplication {
    fun multiply(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
        val m: Int = A.size
        val n: Int = A[0].size
        val nB: Int = B[0].size
        val res = Array(m) { IntArray(nB) }

        for (i in 0 until m) {
            for (k in 0 until n) {
                if (A[i][k] != 0) {
                    for (j in 0 until nB) {
                        if (B[k][j] != 0) res[i][j] += A[i][k] * B[k][j]
                    }
                }
            }
        }
        return res
    }
}