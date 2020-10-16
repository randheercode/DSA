package problem.facebook.old

// https://leetcode.com/problems/add-strings/
class AddStrings {
    fun addStrings(num1: String, num2: String): String {
        val res = StringBuilder()
        var carry = 0
        var p1: Int = num1.length - 1
        var p2: Int = num2.length - 1
        while (p1 >= 0 || p2 >= 0) {
            val x1 = if (p1 >= 0) num1[p1] - '0' else 0
            val x2 = if (p2 >= 0) num2[p2] - '0' else 0
            val value = (x1 + x2 + carry) % 10
            carry = (x1 + x2 + carry) / 10
            res.insert(0, value)
            p1--
            p2--
        }
        if (carry != 0) res.insert(0, carry)
        return res.toString()
    }
}

fun main() {
    println(AddStrings().addStrings("9", "99"))
}