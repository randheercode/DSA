package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:19 am
 */
class DiagonalTraverseII {
    fun findDiagonalOrder(nums: List<List<Int>>): IntArray {
        if (nums.isEmpty()) {
            return IntArray(0)
        }

        val rows: Int = nums.size
        val queue: Queue<IntArray> = LinkedList()
        queue.offer(intArrayOf(0, 0))

        val resultList: MutableList<Int> = ArrayList()

        while (!queue.isEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val current = queue.poll()
                resultList.add(nums[current[0]][current[1]])

                // add child below the current element only if the current is the first one in a row
                if (current[1] == 0 && current[0] < rows - 1) {
                    queue.offer(intArrayOf(current[0] + 1, current[1]))
                }
                // add child right to the current element if there is any
                if (current[1] < nums[current[0]].size - 1) {
                    queue.offer(intArrayOf(current[0], current[1] + 1))
                }
            }
        }
        return resultList.stream().mapToInt { i: Int? -> i!! }.toArray()
    }
}