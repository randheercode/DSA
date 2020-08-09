package problem.old

/**
 * Created by randheer.kumar
 * Date: 21/4/20
 * Time: 10:18 pm
 * Problem Statement: Given a non-empty array of integers, every element appears twice except for one. Find that single
 * one. Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Solution: The best solution is to use XOR. XOR of all array elements gives us the number with single occurrence. The idea is based on following two facts.
 * a) XOR of a number with itself is 0.
 * b) XOR of a number with 0 is number itself.
 */
class SingleNumber {
    fun singleNumber(nums: IntArray): Int {
        var number = 0;
        for (i in nums.indices)
            number = number.xor(nums[i])
        return number
    }

    fun singleNumber2(nums: IntArray): Int {
        var one = 0
        var two = 0
        for (num in nums) {
            two = two xor num and one.inv()
            one = one xor num and two.inv()
        }
        return two
    }

}


fun main() {
    print(SingleNumber().singleNumber(intArrayOf(2, 1, 1, 2, 4)))
}