package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 2/11/20
 * Time: 8:44 pm
 */
class IntegerToEnglishWords {

    private val after1000 = listOf(
            "", "Thousand", "Million", "Billion", "Trillion"
    )

    private val till19 = listOf(
            "",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"
    )

    private val tens = listOf(
            "",
            "",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    )

    private fun parseBelow1000(num: Int): String {
        val resultBuilder = StringBuilder()

        var num = num

        // 100s
        var res = num.div(100)
        if (res > 0) {
            resultBuilder.append(till19[res]).append(" ").append("Hundred").append(" ")
        }

        num = num.rem(100)
        // if below 20
        if (num in 0 until 20) {
            resultBuilder.append(till19[num])
        } else {
            // 10's
            res = num.div(10)
            if (res > 0) {
                resultBuilder.append(tens[res]).append(" ")
            }
            // 1's
            num = num.rem(10)
            if (num > 0) {
                resultBuilder.append(till19[num])
            }
        }

        return resultBuilder.toString().trim()
    }

    private fun parseTill1000(num: Int, idx: Int): String {
        val res = parseBelow1000(num)
        if (res.isEmpty()) return ""
        return "$res ${after1000[idx]}".trim()
    }


    fun numberToWords(num: Int): String {
        if (num == 0) return "Zero"
        val resultBuilder = StringBuilder()

        var num = num
        var after1000Idx = 0

        while (num > 0) {
            val currentNum = num.rem(1000)
            val res = parseTill1000(currentNum, after1000Idx)
            if (resultBuilder.isNotEmpty() && res.isNotEmpty()) resultBuilder.insert(0, " ")
            resultBuilder.insert(0, res)
            num = num.div(1000)
            after1000Idx++
        }

        return resultBuilder.toString()
    }

}