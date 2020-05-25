package problems

import java.util.*


/**
 * Created by randheercode
 * Date: 25/5/20
 * Time: 3:51 pm
 */
class UnCrossedLine {
    fun maxUncrossedLinesOther(A: IntArray, B: IntArray): Int {
        fun lcs(A: IntArray, B: IntArray, i: Int, j: Int): Int {
            if (i == A.size || j == B.size) {
                return 0
            }
            return if (A[i] == B[j]) {
                1 + lcs(A, B, i + 1, j + 1)
            } else {
                maxOf(lcs(A, B, i + 1, j),
                        lcs(A, B, i, j + 1))
            }
        }
        return lcs(A, B, 0, 0)
    }

    fun maxUncrossedLines(A: IntArray, B: IntArray): Int {
        fun helper(memo: HashMap<String?, Int?>, i: Int, j: Int, A: IntArray, B: IntArray): Int {
            if (i >= A.size || j >= B.size) return 0
            val pair = "$i $j"
            if (memo.containsKey(pair)) return memo[pair]!!
            var ans = 0
            ans = if (A[i] == B[j]) 1 + helper(memo, i + 1, j + 1, A, B) else Math.max(helper(memo, i + 1, j, A, B), helper(memo, i, j + 1, A, B))
            memo[pair] = ans
            return ans
        }
        return helper(HashMap(), 0, 0, A, B)
    }


}


fun main() {
    println(UnCrossedLine().maxUncrossedLines(intArrayOf(1, 4, 2), intArrayOf(1, 2, 4)))
    println(UnCrossedLine().maxUncrossedLines(intArrayOf(2, 5, 1, 2, 5), intArrayOf(10, 5, 2, 1, 5, 2)))
    println(UnCrossedLine().maxUncrossedLines(intArrayOf(1, 3, 7, 1, 7, 5), intArrayOf(1, 9, 2, 5, 1)))
}