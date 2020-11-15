package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 15/11/20
 * Time: 11:37 am
 */
class MinimumKnightMoves {
    fun minKnightMoves(x: Int, y: Int): Int {
        var x = x
        var y = y
        x = Math.abs(x)
        y = Math.abs(y)
        // Symmetry for diagonal
        if (x < y) {
            val temp = x
            x = y
            y = temp
        }
        if (x == 1 && y == 0) {
            return 3
        }
        if (x == 2 && y == 2) {
            return 4
        }
        val delta = x - y
        return if (y > delta) {
            (delta - 2 * Math.floor((delta - y).toFloat() / 3.toDouble())).toInt()
        } else {
            (delta - 2 * Math.floor((delta - y) / 4.toDouble())).toInt()
        }
    }
}