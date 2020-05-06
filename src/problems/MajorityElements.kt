package problems

/**
 * Created by randheercode
 * Date: 6/5/20
 * Time: 4:49 pm
 * Problem: Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
class MajorityElements {
    fun majorityElement(nums: IntArray): Int {
        nums.sort()
        val mid = nums.size / 2
        return if (nums[0] == nums[mid]) {
            nums.first()
        } else if (nums.last() == nums[mid]) {
            nums.last()
        } else {
            var maxIndex = 1
            while (maxIndex + mid <= nums.size) {
                if (nums[maxIndex] == nums[maxIndex + mid]) {
                    break
                }
                maxIndex += 1
            }
            nums[maxIndex]
        }
    }

    fun majorityElementOther(nums: IntArray): Int {
        nums.sort()
        return nums[nums.size / 2]
    }
}

fun main() {
    println(MajorityElements().majorityElement(intArrayOf(1, 2, 2, 1, 2, 2)))
    println(MajorityElements().majorityElementOther(intArrayOf(1, 2, 2, 1, 2, 2)))
    println(MajorityElements().majorityElement(intArrayOf(-1, 1, 1, 1, 2, 1)))
    println(MajorityElements().majorityElementOther(intArrayOf(-1, 1, 1, 1, 2, 1)))
}