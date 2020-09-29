package problem.facebook

// https://leetcode.com/problems/intersection-of-three-sorted-arrays/
class IntersectionOfThreeSortedArrays {
    fun arraysIntersection(arr1: IntArray, arr2: IntArray, arr3: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        var j = 0
        var k = 0
        while (i < arr1.size && j < arr2.size && k < arr3.size) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                result.add(arr1[i])
                i++
                j++
                k++
            } else if (arr1[i] < arr2[j]) {
                i++
            } else if (arr2[j] < arr3[k]) {
                j++
            } else k++
        }
        return result
    }
}

fun main() {
    println(IntersectionOfThreeSortedArrays().arraysIntersection(
            intArrayOf(4, 6, 8, 10),
            intArrayOf(6, 7, 8),
            intArrayOf(5, 6, 8)
    ))
}