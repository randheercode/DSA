package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 16/11/20
 * Time: 12:08 pm
 */
class TopKFrequentElements {
    fun topKFrequent(nums: IntArray, k: Int): IntArray? {
        // O(1) time
        if (k == nums.size) {
            return nums
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        val count: MutableMap<Int, Int> = HashMap()
        for (n in nums) {
            count[n] = count.getOrDefault(n, 0) + 1
        }

        // init heap 'the less frequent element first'
        val heap: Queue<Int> = PriorityQueue { n1: Int?, n2: Int? -> count[n1]!! - count[n2]!! }

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (n in count.keys) {
            heap.add(n)
            if (heap.size > k) heap.poll()
        }

        // 3. build an output array
        // O(k log k) time
        val top = IntArray(k)
        for (i in k - 1 downTo 0) {
            top[i] = heap.poll()
        }
        return top
    }
}