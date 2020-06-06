package problems

import generateIntArray
import printArray

/**
 * Created by randheercode
 * Date: 6/6/20
 * Time: 5:44 pm
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater
 * than or equal to h. Write an algorithm to reconstruct the queue.
 */
class ReconstructQueue {
    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        // person = (h, k)
        // h = person height, k = people in front of person with height >= h
        // Sort with height decreasing and no of person in front increasing.
        people.sortWith(Comparator { first, second -> if (first[0] - second[0] != 0) second[0] - first[0] else first[1] - second[1] })
        // insert at index looking at number of person in front.
        val list = mutableListOf<IntArray>()
        for (p in people) {
            list.add(p[1], p)
        }
        return list.toTypedArray()
    }
}

fun main() {
    val input = generateIntArray("[[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]")
    printArray(input, "Input: ")
    printArray(ReconstructQueue().reconstructQueue(input), "Output: ")
}