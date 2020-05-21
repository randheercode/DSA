package problems

/**
 * Created by randheercode
 * Date: 21/5/20
 * Time: 1:23 pm
 * Problem Statement: We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.
 * Each glass holds one cup (250ml) of champagne.i Then, some champagne is poured in the first glass at the top.  When the top most glass is full,
 * any excess liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full, any excess
 * champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom row has it's excess champagne fall on the floor.)
 */
class ChampagneTower { // TODO complete the problem.
    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
        val query_row = query_row + 1
        val query_glass = query_glass + 1
        fun requiredToFullRow(row: Int): Long = row.toLong().times(row.plus(1)).div(2)

        if (poured >= requiredToFullRow(query_row)) return 1.0
        val rowAmount = (poured.minus(requiredToFullRow(query_row - 1)))
        if (rowAmount > 0) {
            val perGlass = rowAmount.toDouble().div(query_row - 1)
            return if (query_glass == 1 || query_glass == query_row) return perGlass.div(2) else perGlass
        } else {
            return 0.0
        }
    }
}

fun main() {
    testCase1()
}

private fun testCase1() {
    println(ChampagneTower().champagneTower(2, 1, 1))
    println(ChampagneTower().champagneTower(1,1,1))
}