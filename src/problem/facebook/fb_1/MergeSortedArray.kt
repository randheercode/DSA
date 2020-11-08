package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 8/11/20
 * Time: 12:38 pm
 */
class MergeSortedArray {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var num1Idx = nums1.size - nums2.size - 1
        var num2Idx = nums2.lastIndex

        var currentIdx = nums1.lastIndex
        while (num1Idx >= 0 && num2Idx >= 0) {
            if (nums1[num1Idx] >= nums2[num2Idx]) {
                nums1[currentIdx--] = nums1[num1Idx]
                num1Idx--
            } else {
                nums1[currentIdx--] = nums2[num2Idx]
                num2Idx--
            }
        }
        while (num1Idx >= 0) {
            nums1[currentIdx--] = nums1[num1Idx]
            num1Idx--
        }

        while (num2Idx >= 0) {
            nums1[currentIdx--] = nums2[num2Idx]
            num2Idx--
        }
    }

}