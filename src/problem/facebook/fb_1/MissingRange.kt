package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 7:48 am
 */
class MissingRange {

    private val sep = "->"
    private val strBuilder = StringBuilder()

    private fun makeStr(first: Int, last: Int): String {
        strBuilder.clear()
        strBuilder.append(first)
        if (first != last) strBuilder.append(sep).append(last)
        return strBuilder.toString()
    }

    fun findMissingRanges(nums: IntArray, lower: Int, upper: Int): List<String> {

        if (nums.isEmpty()) {
            return listOf(makeStr(lower, upper))
        }

        val result = mutableListOf<String>()

        if (nums[0] != lower) result.add(makeStr(lower, nums[0] - 1))

        for (i in 1 until nums.size) {
            if (nums[i] > nums[i - 1] + 1) {
                result.add(makeStr(nums[i - 1] + 1, nums[i] - 1))
            }
        }

        if (nums.last() != upper && upper != lower) result.add(makeStr(nums.last() + 1, upper))

        return result
    }
}