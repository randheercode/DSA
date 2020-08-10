package mit6006

/**
 * Created by randheercode
 * Date: 10/8/20
 * Time: 9:03 am
 */
class Lecture1 {
    fun peakFinding(arr: IntArray): Int {
        // Peak is arr[i] if arr[i-1] <= arr[i] >= arr[i+1]

        var start = 0
        var end = arr.lastIndex

        while (start <= end) {
            val mid = start.plus((end.minus(start).div(2)))
            val firstOrLast = (mid == 0 && arr[mid + 1] <= arr[mid]) || (mid == arr.lastIndex && arr[mid - 1] <= arr[mid])
            val leftOk = mid > 0 && arr[mid - 1] <= arr[mid]
            val rightOk = mid < arr.lastIndex && arr[mid + 1] <= arr[mid]
            if (firstOrLast || (leftOk && rightOk)) {
                return arr[mid]
            }
            if (start == end) throw Exception("Peak doesn't exist")
            if (arr[mid - 1] > arr[mid]) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        throw Exception("Peak doesn't exist")
    }

    fun peakFinding2D(mat: Array<IntArray>): Int {
        var start = 0
        var end = mat.lastIndex

        val map = mutableMapOf<Int, Int>()
        fun max(i: Int): Int {
            if (!map.containsKey(i)) map[i] = mat[i].max()!!
            return map[i]!!
        }

        while (start <= end) {
            val mid = start.plus((end.minus(start).div(2)))
            val firstOrLast = (mid == 0 && max(mid + 1) <= max(mid)) || (mid == mat.lastIndex && max(mid - 1) <= max(mid))
            val leftOk = mid > 0 && max(mid - 1) <= max(mid)
            val rightOk = mid < mat.lastIndex && max(mid + 1) <= max(mid)
            if (firstOrLast || (leftOk && rightOk)) {
                return max(mid)
            }
            if (start == end) throw Exception("Peak doesn't exist")
            if (max(mid - 1) > max(mid)) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        throw Exception("Peak doesn't exist")
    }

    companion object {
        fun peakFinding() {
            println(Lecture1().peakFinding(intArrayOf(5, 10, 20, 15)))
            println(Lecture1().peakFinding(intArrayOf(10, 20, 15, 2, 23, 90, 67)))
            println(Lecture1().peakFinding(intArrayOf(1, 2, 3, 4, 5, 6)))
            println(Lecture1().peakFinding(intArrayOf(6, 5, 4, 3, 2, 1)))
            println(Lecture1().peakFinding(intArrayOf(10, 11, 7, 6, 4)))
        }
    }
}

fun main() {
    Lecture1.peakFinding()
}