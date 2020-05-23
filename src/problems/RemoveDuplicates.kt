package problems

/**
 * Created by randheercode
 * Date: 24/5/20
 * Time: 12:09 am
 * Problem Statement: Given a sorted array nums, remove the duplicates in-place such that each
 * element appear only once and return the new length.
 */
class RemoveDuplicates {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var currentPointer = 0
        val lastIndex = nums.lastIndex
        for (i in 1..lastIndex) {
            if (nums[i] != nums[currentPointer]) {
                currentPointer += 1
                nums[currentPointer] = nums[i]
            }
        }
        return currentPointer + 1
    }
}