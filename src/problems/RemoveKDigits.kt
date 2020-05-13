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

        return num.substring(k, num.length)
    }
}

fun main() {
    println(RemoveKDigits().removeKdigits("12345", 2))
}