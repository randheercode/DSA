package problem.facebook.old


// https://leetcode.com/problems/integer-to-english-words/
class IntegerToEnglishWords {
    private val oneS = arrayOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen")
    private val tensS = arrayOf("", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")
    private val thousandS = arrayOf("", "Thousand", "Million", "Billion", "Trillion", "Quadrillion", "", "", "")

    private fun helper(num: Int): String {
        val result = StringBuilder()

        if (num == 0) return result.toString()
        if (num > 999) throw RuntimeException("Invalid Number")

        var n = num
        val hundreds = n / 100
        n %= 100
        if (hundreds > 0) result.append(oneS[hundreds]).append(" ").append("Hundred")
        if (n in 1..19) {
            if (result.isNotEmpty()) result.append(" ")
            result.append(oneS[n])
        } else if (n >= 20) {
            val tens = n / 10
            n %= 10
            if (tens > 0) {
                if (result.isNotEmpty()) result.append(" ")
                result.append(tensS[tens])
            }
            if (n > 0) {
                if (result.isNotEmpty()) result.append(" ")
                result.append(oneS[n])
            }
        }

        return result.toString()
    }

    fun numberToWords(num: Int): String {
        val result = StringBuilder()
        val list = mutableListOf<Int>()
        var n = num
        while (n > 0) {
            list.add(n % 1000)
            n /= 1000
        }
        for (i in list.indices) {
            val res = helper(list[list.lastIndex - i])
            val res1 = thousandS[list.lastIndex - i]
            if (result.isNotEmpty() && res.isNotEmpty()) {
                result.append(" ")
            }
            if (res.isNotEmpty()) result.append(res)
            if (result.isNotEmpty() && res.isNotEmpty() && res1.isNotEmpty()) {
                result.append(" ")
                result.append(res1)
            }
        }
        if (result.isEmpty()) result.append("Zero")
        return result.toString()
    }
}

fun main() {
    println(IntegerToEnglishWords().numberToWords(123) == "One Hundred Twenty Three")
    println(IntegerToEnglishWords().numberToWords(12345) == "Twelve Thousand Three Hundred Forty Five")
    println(IntegerToEnglishWords().numberToWords(1234567) == "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven")
    println(IntegerToEnglishWords().numberToWords(1234567891) == "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One")
    println(IntegerToEnglishWords().numberToWords(1000) == "One Thousand")
    println(IntegerToEnglishWords().numberToWords(1000000))
}