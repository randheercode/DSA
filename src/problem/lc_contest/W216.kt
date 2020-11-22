package problem.lc_contest


class W216 {

    fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
        val first = word1.joinToString("")
        val second = word2.joinToString("")
        return first == second
    }

    private fun getChar(idx: Int): Char {
        return ('a'.toInt().plus(idx.minus(1))).toChar()
    }

    fun getSmallestString(n: Int, k: Int): String {

        var count = n
        var sum = k
        val result = StringBuilder()

        val last = (sum - count) / 25

        if (last > 0) {
            for (i in 1..last) result.append(getChar(26))
            sum -= (26 * last)
            count -= last
        }

        while (count > 0) {
            val currentSum = sum - count + 1
            val idx = minOf(currentSum, 26)
            result.insert(0, getChar(idx))
            sum -= idx
            count--

        }
        return result.toString()
    }

}
