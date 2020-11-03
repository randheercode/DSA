package problem.facebook.fb_1

import java.util.*

/**
 * Created by randheercode
 * Date: 3/11/20
 * Time: 11:15 am
 */
class KthLargestElementInAnArray {
    lateinit var nums: IntArray

    private fun swap(a: Int, b: Int) {
        val tmp = nums[a]
        nums[a] = nums[b]
        nums[b] = tmp
    }


    private fun partition(left: Int, right: Int, pivot_index: Int): Int {
        val pivot = nums[pivot_index]
        // 1. move pivot to end
        swap(pivot_index, right)
        var storeIdx = left
        // 2. move all smaller elements to the left
        for (i in left..right) {
            if (nums[i] < pivot) {
                swap(storeIdx, i)
                storeIdx++
            }
        }
        // 3. move pivot to its final place
        swap(storeIdx, right)
        return storeIdx
    }

    /**
     * Returns the k-th smallest element of list within left..right.
     * */
    private fun quickSelect(left: Int, right: Int, k_smallest: Int): Int {

        if (left == right) // If the list contains only one element,
            return nums[left] // return that element

        // select a random pivot_index
        val randomNum = Random()
        var pivotIndex: Int = left + randomNum.nextInt(right - left)
        pivotIndex = partition(left, right, pivotIndex)

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivotIndex) return nums[k_smallest]
        else if (k_smallest < pivotIndex) return quickSelect(left, pivotIndex - 1, k_smallest)
        // go right side
        return quickSelect(pivotIndex + 1, right, k_smallest)
    }

    fun findKthLargest(nums: IntArray, k: Int): Int {
        this.nums = nums
        val size = nums.size
        // kth largest is (N - k)th smallest
        return quickSelect(0, size - 1, size - k)
    }
}