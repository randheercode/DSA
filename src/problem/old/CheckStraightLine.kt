package problem.old

/**
 * Created by randheercode
 * Date: 8/5/20
 * Time: 8:09 pm
 */
class CheckStraightLine {
    fun checkStraightLine(coordinates: Array<IntArray>): Boolean {

        if (coordinates.size <= 2) return true

        val slope = getSlope(coordinates[0], coordinates[1])

        for (i in 2..coordinates.lastIndex) {
            val nextSlope = getSlope(coordinates[i - 1], coordinates[i])
            if (nextSlope != slope) {
                return false
            }
        }

        return true
    }

    private fun getSlope(x: IntArray, y: IntArray): Double {
        return (y[1] - x[1]) / (y[0] - x[0]).toDouble()
    }
}

fun main() {
    println(CheckStraightLine().checkStraightLine(arrayOf(
            intArrayOf(-4, -3),
            intArrayOf(1, 0),
            intArrayOf(3, -1),
            intArrayOf(0, -1),
            intArrayOf(-5, 2)
    )))
}