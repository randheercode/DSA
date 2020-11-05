package algorithm

/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 11:38 pm
 */
class RadixSort {

    private fun getIdx(num: Int, exr: Int): Int {
        return (num.div(exr)).rem(10)
    }

    private fun countingSort(nums: IntArray, exr: Int) {

        val count = IntArray(10)
        val result = IntArray(nums.size)

        for (n in nums) count[getIdx(n, exr)] += 1

        for (i in 1 until 10) count[i] += count[i - 1]

        for (i in nums.lastIndex downTo 0) {
            val n = nums[i]
            val idx = getIdx(n, exr)
            result[count[idx] - 1] = n
            count[idx] -= 1
        }

        for (i in nums.indices) nums[i] = result[i]

    }

    private fun findMax(nums: IntArray): Int {
        return nums.max()!!
    }

    fun sort(nums: IntArray): IntArray {

        val max = findMax(nums)
        var exr = 1

        while (max / exr > 0) {
            countingSort(nums, exr)
            exr *= 10
        }

        return nums
    }


}