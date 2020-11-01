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
    fun leftMostColumnWithOne(binaryMatrix: BinaryMatrix): Int {

        val dimension = binaryMatrix.dimensions()
        var row = 0
        var col = dimension[1] - 1

        while (row < dimension[0] && col >= 0) {
            if (binaryMatrix.get(row, col) == 0) {
                row++
            } else if (binaryMatrix.get(row, col) == 1){
                col--
            }
        }

        return if (col == dimension[1] - 1) -1 else col + 1

    }
}