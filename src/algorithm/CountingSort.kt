package algorithm

/**
 * Created by randheercode
 * Date: 5/11/20
 * Time: 11:38 pm
 */
class CountingSort {
    // Num to be in range of 0-9
    fun sort(nums: IntArray): IntArray {
        val count = IntArray(10)
        val result = IntArray(nums.size)

        for (n in nums) count[n] += 1

        for (i in 2 until 10) count[i] += count[i - 1]

        for (n in nums) {
            result[count[n] - 1] = n
            count[n] -= 1
        }

        return result
    }
}