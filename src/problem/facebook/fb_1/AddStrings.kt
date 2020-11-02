package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:09 pm
 */
class AddStrings {
    fun addStrings(num1: String, num2: String): String {
        val resultBuilder = StringBuilder()
        var idx1 = num1.lastIndex
        var idx2 = num2.lastIndex

        var carry = 0
        while (idx1 >= 0 && idx2 >= 0) {
            val res = carry + num1[idx1].toInt().minus(48) + num2[idx2].toInt().minus(48)
            resultBuilder.insert(0, res.rem(10))
            carry = res / 10;
            idx1--
            idx2--
        }

        while (idx1 >= 0) {
            val res = carry + num1[idx1].toInt().minus(48)
            resultBuilder.insert(0, res.rem(10))
            carry = res / 10;
            idx1--
        }

        while (idx2 >= 0) {
            val res = carry + num2[idx2].toInt().minus(48)
            resultBuilder.insert(0, res.rem(10))
            carry = res / 10;
            idx2--
        }

        if (carry > 0) {
            resultBuilder.insert(0, carry)
        }

        return resultBuilder.toString()
    }
}