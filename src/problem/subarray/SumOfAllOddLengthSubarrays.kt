package problem.subarray

// https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
class SumOfAllOddLengthSubarrays {
    fun sumOddLengthSubarrays(arr: IntArray): Int {
        var sum = 0
        val n = arr.size
        val prefixSum = IntArray(n + 1)
        for (i in 0 until n) {
            prefixSum[i + 1] = prefixSum[i] + arr[i]
        }
        var i = 1
        while (i < prefixSum.size) {
            var j = 0
            while (j + i < prefixSum.size) {
                sum = sum + prefixSum[j + i] - prefixSum[j]
                j++
            }
            i += 2
        }
        return sum
    }

    fun sumOddLengthSubarraysMath(arr: IntArray): Int {
        var result = 0
        val n: Int = arr.size
        for (i in 0 until n) {
            result += ((i + 1) * (n - i) + 1) / 2 * arr[i]
        }
        return result
    }

    // https://www.youtube.com/watch?v=J5IIH35EBVE
    fun sumOddLengthSubarraysMath2(arr: IntArray): Int {
        var result = 0
        val n = arr.size
        for (i in 0 until n) {
            val endingHere = i + 1
            val startingHere = n - i
            val totalSubarrays = endingHere * startingHere
            var oddSubarrays = totalSubarrays / 2
            if (totalSubarrays % 2 == 1) {
                oddSubarrays++
            }
            result += oddSubarrays * arr[i]
        }
        return result
    }
}

fun main() {
    println(SumOfAllOddLengthSubarrays().sumOddLengthSubarrays(intArrayOf(1, 4, 2, 5, 3)))
    println(SumOfAllOddLengthSubarrays().sumOddLengthSubarraysMath(intArrayOf(1, 4, 2, 5, 3)))
}