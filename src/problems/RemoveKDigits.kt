package problems

/**
 * Created by randheercode
 * Date: 13/5/20
 * Time: 10:32 pm
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.
 * Note:
 * The length of num is less than 10002 and will be ≥ k.
 * The given num does not contain any leading zero.
 *
 */
class RemoveKDigits {
    fun removeKdigits(num: String, k: Int): String {
        if (num.length == k)
            return "0";

        val sb = StringBuilder(num);

        for (j in 0 until k) {
            var i = 0;
            while (i < sb.length - 1 && sb[i] <= sb[i + 1]) {
                i++
            }
            sb.delete(i, i + 1);
        }

        while (sb.length > 1 && sb[0] == '0')
            sb.delete(0, 1);

        if (sb.isEmpty()) {
            return "0";
        }

        return sb.toString();
    }
}

fun main() {
    println(RemoveKDigits().removeKdigits("5337", 2))
}