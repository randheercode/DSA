package problem.lc_contest.bw.bw28

/**
 * Created by randheercode
 * Date: 13/6/20
 * Time: 10:07 pm
 * LeetCode Contest.
 */
private class Prob1 {
    fun finalPrices(prices: IntArray): IntArray {
        val len = prices.size
        val disCountArray = IntArray(len)
        prices.forEachIndexed { index, price ->
            var discount = 0
            for (i in (index + 1) until len) {
                if (prices[i] <= price) {
                    discount = prices[i]
                    break
                }
            }
            disCountArray[index] = price - discount
        }
        return disCountArray
    }
}

private fun test1() {
    println(Prob1().finalPrices(intArrayOf(8, 4, 6, 2, 3)).toList())
    println(Prob1().finalPrices(intArrayOf(1, 2, 3, 4, 5)).toList())
    println(Prob1().finalPrices(intArrayOf(10, 1, 1, 6)).toList())
}

private class Prob2() {
    val rectangle: Array<IntArray> = arrayOf()
    fun updateSubrectangle(row1: Int, col1: Int, row2: Int, col2: Int, newValue: Int) {
        for (row in row1..row2) {
            for (col in col1..col2) {
                rectangle[row][col] = newValue
            }
        }
    }

    fun getValue(row: Int, col: Int): Int {
        return rectangle[row][col]
    }

}


private fun test2() {
}

private class Prob3 { // TODO not completed
    fun minSumOfLengths(arr: IntArray, target: Int): Int {
        fun targetSum(array: IntArray, sum: Int): IntArray {
            val result = IntArray(2) { -1 }
            if (array.isEmpty()) return result
            val len = array.size
            var curSum = array[0]
            var start = 0
            var i = 1
            while (i <= len) {
                while (curSum > sum && start < i - 1) {
                    curSum -= array[start]
                    start++
                }
                if (curSum == sum) {
                    val p = i - 1
                    result[0] = start
                    result[1] = p
                    return result
                }
                if (i < len) curSum += array[i]
                i++
            }
            return result
        }

        val last = arr.lastIndex
        val result = mutableListOf<Int>()
        val indexSet = mutableListOf<String>()
        var lastIndex = 0
        do {
            val res = targetSum(arr.sliceArray(lastIndex..last), target)
            if (res[0] != -1 ) {
                result.add(res[1] - res[0] + 1)
            }
            lastIndex++
        } while (lastIndex < last)

        return if (result.size < 2) -1
        else {
            result.sort()
            print(result)
            result.take(2).sum()
        }
    }
}

private fun test3() {
//    println(Prob3().minSumOfLengths(intArrayOf(3, 2, 2, 4, 3), 3))
//    println(Prob3().minSumOfLengths(intArrayOf(7, 3, 4, 7), 7))
//    println(Prob3().minSumOfLengths(intArrayOf(4, 3, 2, 6, 2, 3, 4), 6))
//    println(Prob3().minSumOfLengths(intArrayOf(5, 5, 4, 4, 5), 3))
//    println(Prob3().minSumOfLengths(intArrayOf(3, 1, 1, 1, 5, 1, 2, 1), 3))
    println(Prob3().minSumOfLengths(intArrayOf(78, 18, 1, 94, 1, 1, 1, 29, 58, 3, 4, 1, 2, 56, 17, 19, 4, 1, 63, 2, 16, 11, 1, 1, 2, 1, 25,
            62, 10, 69, 12, 7, 1, 6, 2, 92, 4, 1, 61, 7, 26, 1, 1, 1, 67, 26, 2, 2, 70, 25, 2, 68, 13, 4, 11, 1, 34, 14, 7, 37, 4, 1, 12, 51,
            25, 2, 4, 3, 56, 21, 7, 8, 5, 93, 1, 1, 2, 55, 14, 25, 1, 1, 1, 89, 6, 1, 1, 24, 22, 50, 1, 28, 9, 51, 9, 88, 1, 7, 1, 30, 32, 18,
            12, 3, 2, 18, 10, 4, 11, 43, 6, 5, 93, 2, 2, 68, 18, 11, 47, 33, 17, 27, 56, 13, 1, 2, 29, 1, 17, 1, 10, 15, 18, 3, 1, 86, 7, 4, 16,
            45, 3, 29, 2, 1, 1, 31, 19, 18, 16, 12, 1, 56, 4, 35, 1, 1, 36, 59, 1, 1, 16, 58, 18, 4, 1, 43, 31, 15, 6, 1, 1, 6, 49, 27, 12, 1, 2,
            80, 14, 2, 1, 21, 32, 18, 15, 11, 59, 10, 1, 14, 3, 3, 7, 15, 4, 55, 4, 1, 12, 4, 1, 1, 53, 37, 2, 5, 72, 3, 6, 10, 3, 3, 83, 8, 1, 5),
            97))
}

private class Prob4 {

}

private fun test4() {
    println(Prob4())
}

fun main() {
    when (3) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}



