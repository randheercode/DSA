package problem.old

import java.util.*


/**
 * Created by randheercode
 * Date: 21/6/20
 * Time: 4:38 pm
 */
internal class Dungeon {

    var inf = Int.MAX_VALUE
    lateinit var dp: Array<IntArray>
    var rows = 0
    var cols = 0

    private fun getMinHealth(currCell: Int, nextRow: Int, nextCol: Int): Int {
        if (nextRow >= rows || nextCol >= cols) return inf
        val nextCell = dp[nextRow][nextCol]
        return maxOf(1, nextCell - currCell)
    }

    fun calculateMinimumHP(dungeon: Array<IntArray>): Int {
        rows = dungeon.size
        cols = dungeon[0].size
        dp = Array(rows) { IntArray(cols) }
        for (arr in dp) {
            Arrays.fill(arr, inf)
        }
        var currCell: Int
        var rightHealth: Int
        var downHealth: Int
        var nextHealth: Int
        var minHealth: Int
        for (row in rows - 1 downTo 0) {
            for (col in cols - 1 downTo 0) {
                currCell = dungeon[row][col]
                rightHealth = getMinHealth(currCell, row, col + 1)
                downHealth = getMinHealth(currCell, row + 1, col)
                nextHealth = minOf(rightHealth, downHealth)
                minHealth = if (nextHealth != inf) {
                    nextHealth
                } else {
                    if (currCell >= 0) 1 else 1 - currCell
                }
                dp[row][col] = minHealth
            }
        }
        return dp[0][0]
    }
}