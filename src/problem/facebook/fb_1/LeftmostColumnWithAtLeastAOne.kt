package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 1/11/20
 * Time: 9:31 pm
 */
interface BinaryMatrix {
    fun get(row: Int, col: Int): Int
    fun dimensions(): List<Int>
}

class LeftmostColumnWithAtLeastAOne {
    fun leftMostColumnWithOneOptimal(binaryMatrix: BinaryMatrix): Int {
        val rows = binaryMatrix.dimensions()[0]
        val cols = binaryMatrix.dimensions()[1]
        var smallestIndex = cols
        for (row in 0 until rows) {
            // Binary Search for the first 1 in the row.
            var lo = 0
            var hi = cols - 1
            while (lo < hi) {
                val mid = (lo + hi) / 2
                if (binaryMatrix.get(row, mid) == 0) {
                    lo = mid + 1
                } else {
                    hi = mid
                }
                // If the last element in the search space is a 1, then this row
                // contained a 1.
                if (binaryMatrix.get(row, lo) == 1) {
                    smallestIndex = Math.min(smallestIndex, lo)
                }
            }
        }
        // If smallest_index is still set to cols, then there were no 1's in
        // the grid.
        return if (smallestIndex == cols) -1 else smallestIndex
    }

    fun leftMostColumnWithOne(binaryMatrix: BinaryMatrix): Int {

        val dimension = binaryMatrix.dimensions()
        var row = 0
        var col = dimension[1] - 1

        while (row < dimension[0] && col >= 0) {
            if (binaryMatrix.get(row, col) == 0) {
                row++
            } else if (binaryMatrix.get(row, col) == 1) {
                col--
            }
        }

        return if (col == dimension[1] - 1) -1 else col + 1

    }
}