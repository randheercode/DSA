package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 16/11/20
 * Time: 12:06 pm
 */
class MultiplyString {
    fun multiply(num1: String, num2: String): String? {
        if (num1 == "0" || num2 == "0") return "0"
        val len1 = num1.length
        val len2 = num2.length
        val len = len1 + len2 - 1
        val lc = IntArray(len)
        var carry = 0
        val result = StringBuilder(len + 1)
        for (i in len1 - 1 downTo 0) {
            for (j in len2 - 1 downTo 0) {
                val x = num1[i] - '0'
                val y = num2[j] - '0'
                lc[i + j] += x * y
            }
        }
        for (i in len - 1 downTo 0) {
            lc[i] += carry
            result.insert(0, lc[i] % 10)
            carry = lc[i] / 10
        }
        if (carry > 0) result.insert(0, carry)
        return result.toString()
    }
}