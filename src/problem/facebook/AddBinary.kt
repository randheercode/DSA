package problem.facebook

// https://leetcode.com/problems/add-binary/
class AddBinary {
    fun addBinary(a: String, b: String): String? {
        val n = a.length
        val m = b.length
        if (n < m) return addBinary(b, a)
        val L = maxOf(n, m)
        val sb = StringBuilder()
        var carry = 0
        var j = m - 1
        for (i in L - 1 downTo -1 + 1) {
            if (a[i] == '1') ++carry
            if (j > -1 && b[j--] == '1') ++carry
            if (carry % 2 == 1) sb.append('1') else sb.append('0')
            carry /= 2
        }
        if (carry == 1) sb.append('1')
        sb.reverse()
        return sb.toString()
    }
}

fun main() {
    println(AddBinary().addBinary("1", "1"))
}