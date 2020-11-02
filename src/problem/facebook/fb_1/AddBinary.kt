package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:36 pm
 */
class AddBinary {
    fun addBinary(a: String, b: String): String {
        val resultBuilder = StringBuilder()
        var idx1 = a.lastIndex
        var idx2 = b.lastIndex

        var carry = 0
        while (idx1 >= 0 && idx2 >= 0) {
            val res = carry + a[idx1].toInt().minus(48) + b[idx2].toInt().minus(48)
            resultBuilder.insert(0, res.rem(2))
            carry = res / 2
            idx1--
            idx2--
        }

        while (idx1 >= 0) {
            val res = carry + a[idx1].toInt().minus(48)
            resultBuilder.insert(0, res.rem(2))
            carry = res / 2
            idx1--
        }

        while (idx2 >= 0) {
            val res = carry + b[idx2].toInt().minus(48)
            resultBuilder.insert(0, res.rem(2))
            carry = res / 2
            idx2--
        }

        if (carry > 0) {
            resultBuilder.insert(0, carry)
        }

        return resultBuilder.toString()
    }
}