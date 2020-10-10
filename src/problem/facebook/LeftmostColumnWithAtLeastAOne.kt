package problem.facebook

// https://leetcode.com/problems/leftmost-column-with-at-least-a-one/
interface BinaryMatrix {
    fun get(row: Int, col: Int): Int
    fun dimensions(): List<Int>
}

class LeftmostColumnWithAtLeastAOne {
    fun leftMostColumnWithOne(binaryMatrix: BinaryMatrix): Int {
        val dimen = binaryMatrix.dimensions()
        var row = dimen[0] - 1
        var col = dimen[1] - 1
        var result = -1

        while (row >= 0 && col >= 0) {
            if (binaryMatrix.get(row, col) == 1) {
                result = col
                col--
            } else if (binaryMatrix.get(row, col) == 0) {
                row--
            }
        }
        return result
    }
}

