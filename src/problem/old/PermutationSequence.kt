package problem.old

import java.util.*


/**
 * Created by randheercode
 * Date: 20/6/20
 * Time: 7:32 pm
 */
class PermutationSequence {


    fun permute(nums: IntArray): List<List<Int?>> {
        fun backtrack(n: Int, nums: MutableList<Int>, output: MutableList<List<Int>>, first: Int) {
            if (first == n) output.add(ArrayList(nums))
            for (i in first until n) {
                Collections.swap(nums, first, i)
                backtrack(n, nums, output, first + 1)
                Collections.swap(nums, first, i)
            }
        }

        val output: MutableList<List<Int>> = LinkedList<List<Int>>()
        val numsList = mutableListOf<Int>()
        for (num in nums) numsList.add(num)
        val n = nums.size
        backtrack(n, numsList, output, 0)
        return output
    }

    fun nextPermutation(nums: IntArray) {

        fun swap(nums: IntArray, i: Int, j: Int) {
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp
        }

        fun reverse(nums: IntArray, start: Int) {
            var i = start
            var j = nums.size - 1
            while (i < j) {
                swap(nums, i, j)
                i++
                j--
            }
        }

        var i = nums.size - 2
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--
        }
        if (i >= 0) {
            var j = nums.size - 1
            while (j >= 0 && nums[j] <= nums[i]) {
                j--
            }
            swap(nums, i, j)
        }
        reverse(nums, i + 1)
    }


    fun kthPermutation(n: Int, k: Int): String {
        var y = k
        val factorials = IntArray(n)
        val nums: MutableList<Int> = mutableListOf(1)
        factorials[0] = 1
        for (i in 1 until n) {
            factorials[i] = factorials[i - 1] * i
            nums.add(i + 1)
        }
        --y
        val sb = StringBuilder()
        for (i in n - 1 downTo 0) {
            val idx = y / factorials[i]
            y -= idx * factorials[i]
            sb.append(nums[idx])
            nums.removeAt(idx)
        }
        return sb.toString()
    }
}

fun main() {
    println(PermutationSequence().kthPermutation(4, 9))
    println(PermutationSequence().permute(intArrayOf(1, 2, 3, 4)))
    val input = intArrayOf(4, 3, 2, 1)
    PermutationSequence().nextPermutation(input)
    println(input.toList())
}