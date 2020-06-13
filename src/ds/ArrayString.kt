package ds

/**
 * Created by randheercode
 * Date: 13/6/20
 * Time: 1:55 pm
 * Source: https://leetcode.com/explore/learn/card/array-and-string/
 */
class ArrayString {
    // https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
    fun pivotIndex(nums: IntArray): Int {
        var sum = 0
        var leftsum = 0
        for (x in nums) sum += x
        for (i in nums.indices) {
            if (leftsum == sum - leftsum - nums[i]) return i
            leftsum += nums[i]
        }
        return -1
    }

    fun dominantIndex(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        if (nums.size == 1) return 0
        var maxValue = Int.MIN_VALUE
        var maxIndex = 0
        var secondMaxValue = maxValue
        var secondMaxIndex = 0
        nums.mapIndexed { index, num ->
            if (num >= maxValue) {
                secondMaxIndex = maxIndex
                secondMaxValue = maxValue
                maxValue = num
                maxIndex = index
            } else if (num > secondMaxValue) {
                secondMaxIndex = index
                secondMaxValue = num
            }
        }
        return if (maxValue < secondMaxValue * 2) return -1 else maxIndex
    }

    fun plusOne(digits: IntArray): IntArray {
        if (digits.isEmpty()) return intArrayOf(1)
        var rem = 1
        var i = digits.lastIndex

        while (rem > 0 && i >= 0) {
            val sum = digits[i].plus(rem)
            digits[i] = sum % 10
            rem = sum / 10
            i--
        }

        return if (rem == 0) {
            digits
        } else {
            val newDigits = IntArray(digits.size.plus(1))
            newDigits[0] = rem
            digits.forEachIndexed { index, num -> newDigits[index.plus(1)] = num }
            newDigits
        }

    }
}

fun main() {
    println(ArrayString().plusOne(intArrayOf(3, 2, 1)).toList())
}