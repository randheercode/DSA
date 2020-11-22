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


    fun waysToMakeFair(nums: IntArray): Int {
        var count = 0
        if (nums.size < 3) return nums.size.rem(2)
        val rightSumEven = IntArray(nums.size)
        val rightSumOdd = IntArray(nums.size)
        for (i in nums.lastIndex downTo 0) {
            if (i.rem(2) == 0) {
                rightSumEven[i] = rightSumEven[minOf(i + 1, nums.lastIndex)] + nums[i]
                rightSumOdd[i] = rightSumOdd[minOf(i + 1, nums.lastIndex)]
            } else {
                rightSumOdd[i] = rightSumOdd[minOf(i + 1, nums.lastIndex)] + nums[i]
                rightSumEven[i] = rightSumEven[minOf(i + 1, nums.lastIndex)]
            }
        }
        val leftSumEven = IntArray(nums.size)
        val leftSumOdd = IntArray(nums.size)
        for (i in nums.indices) {
            if (i.rem(2) == 0) {
                leftSumEven[i] = leftSumEven[maxOf(i - 1, 0)] + nums[i]
                leftSumOdd[i] = leftSumOdd[maxOf(i - 1, 0)]
            } else {
                leftSumOdd[i] = leftSumOdd[maxOf(i - 1, 0)] + nums[i]
                leftSumEven[i] = leftSumEven[maxOf(i - 1, 0)]
            }
        }

        for (i in nums.indices) {
            if (i == 0) {
                if (rightSumEven[1] == rightSumOdd[1]) count++
            } else if (i == nums.lastIndex) {
                if (leftSumEven[i - 1] == leftSumOdd[i - 1]) count++
            } else {
                val first = leftSumEven[i - 1] + rightSumOdd[i + 1]
                val second = leftSumOdd[i - 1] + rightSumEven[i + 1]
                if (first == second) count++
            }
        }
        return count
    }

}
