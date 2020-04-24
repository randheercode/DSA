/**
 * Created by randheercode
 * Date: 6/4/20
 * Time: 10:07 pm
 * Problem Statement: Given an array of integers, find contiguous sub array with largest sum.
 * Solution: Kadane's Algorithm
 * References: https://www.youtube.com/watch?v=86CQq3pKSUw&t=341s
 * References: https://en.wikipedia.org/wiki/Maximum_subarray_problem
 */
class Kadanes {

    fun maxSubArray(numbs: IntArray): List<Int> {

        var maxGlobal = numbs[0] // Max Sum across array
        var maxLocal = numbs[0] // Current Sub Array sum

        var indexStart = 0 // Current Sub Array start index
        var indexEnd = 0 // Current Sub Array end index
        var maxStartIndex = 0 //

        for (i in 1..numbs.lastIndex) {
            maxLocal += numbs[i]

            if (maxLocal < numbs[i]) {
                maxLocal = numbs[i]
                maxStartIndex = i
            }

            if (maxGlobal < maxLocal) {
                maxGlobal = maxLocal
                indexStart = maxStartIndex
                indexEnd = i
            }
        }

        return numbs.slice(indexStart..indexEnd)
    }

}

fun main() {
    println(Kadanes().maxSubArray(intArrayOf(-2, -3, 4, -1, -2, 1, 5, -3)))
    println(Kadanes().maxSubArray(intArrayOf(-2, -3, -4, -1, -2, -1, -5, -3)))
}