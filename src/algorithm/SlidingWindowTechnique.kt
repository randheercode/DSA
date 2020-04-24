/**
 * Created by randheercode
 * Date: 7/4/20
 * Time: 09:10 pm
 * Problem Statement: Given an array arr[], an integer K and a Sum. The task is to check if there exists any sub array
 * with K elements whose sum is equal to the given sum. If any of the sub array with size K has the sum equal to the
 * given sum.
 * References: https://www.geeksforgeeks.org/subarray-of-size-k-with-given-sum/
 * References: https://medium.com/outco/how-to-solve-sliding-window-problems-28d67601a66
 */
class SlidingWindowTechnique {

    fun subArrayOfSizeWithSum(numbs: List<Int>, size: Int, sum: Int): List<Int> {

        if (numbs.size < size) return emptyList()

        var currentSum = 0

        for (i in 0..size.minus(1))
            currentSum += numbs[i]

        if (currentSum == sum) return numbs.subList(0, size)

        for (i in size..numbs.lastIndex) {
            currentSum = currentSum + numbs[i] - numbs[i.minus(size)]
            if (currentSum == sum) return numbs.subList(i.plus(1).minus(size), i.plus(1))
        }

        return emptyList()
    }

}

fun main() {
    println(SlidingWindowTechnique().subArrayOfSizeWithSum(listOf(-2, -4, 3, 5, -6, 14, 24, 1), 3, 13))
    println(SlidingWindowTechnique().subArrayOfSizeWithSum(listOf(-2, -4, 3, 5, -6, 14, 24, 1), 3, 32))
    println(SlidingWindowTechnique().subArrayOfSizeWithSum(listOf(-2, -4, 3, 5, -6, 14, 24, 1), 3, 0))
}