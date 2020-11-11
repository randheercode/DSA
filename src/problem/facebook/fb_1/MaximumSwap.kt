package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 11/11/20
 * Time: 9:53 am
 */
class MaximumSwap {
    fun maximumSwap(num: Int): Int {
        val A = num.toString().toCharArray()
        val last = IntArray(10)
        for (i in A.indices) {
            last[A[i] - '0'] = i
        }
        for (i in A.indices) {
            for (d in 9 downTo A[i] - '0' + 1) {
                if (last[d] > i) {
                    val tmp = A[i]
                    A[i] = A[last[d]]
                    A[last[d]] = tmp
                    return Integer.valueOf(String(A))
                }
            }
        }
        return num
    }
}