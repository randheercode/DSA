/**
 * Created by randheercode
 * Date: 5/4/20
 * Time: 11:52 pm
 * Problem Statement: Given an array of length n + 1, having value in range 1 to n.
 * Find the duplicate value in linear time and constant space complexity.
 * Solution is inspired by Floyd's Tortoise and Hare Algorithm (Cycle Detection).
 * Reference: https://en.wikipedia.org/wiki/Cycle_detection
 * Reference: https://www.youtube.com/watch?v=pKO9UjSeLew
 *
 */
class FloydsTortoiseHare {

    fun findDuplicate(numbs: IntArray): Int {

        var tortoise = numbs[0]
        var hare = numbs[0]

        do {
            tortoise = numbs[tortoise]
            hare = numbs[numbs[hare]]
        } while (tortoise != hare)

        var pointer1 = numbs[0]
        var pointer2 = tortoise

        while (pointer1 != pointer2) {
            pointer1 = numbs[pointer1]
            pointer2 = numbs[pointer2]
        }

        return pointer1
    }

}

fun main() {
    println(FloydsTortoiseHare().findDuplicate(intArrayOf(6, 5, 5, 2, 3, 1, 4)))
    println(FloydsTortoiseHare().findDuplicate(intArrayOf(6, 5, 3, 2, 3, 1, 4)))
    println(FloydsTortoiseHare().findDuplicate(intArrayOf(6, 5, 1, 2, 3, 1, 4)))
    println(FloydsTortoiseHare().findDuplicate(intArrayOf(2, 5, 6, 2, 3, 1, 4)))
}