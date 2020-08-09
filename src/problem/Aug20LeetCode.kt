package problem

import generateIntArray
import printArray

/**
 * Created by randheercode
 * Date: 9/8/20
 * Time: 4:25 pm
 */
class Aug20LeetCode {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val iRange = grid.indices
        val jRange = grid[0].indices
        fun safeIndex(i: Int, j: Int) = i in iRange && j in jRange

        var dayTaken = 0
        var freshCount = 0
        //the value 0 representing an empty cell;
        //the value 1 representing a fresh orange;
        //the value 2 representing a rotten orange
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1) freshCount++
            }
        }

        if (freshCount == 0) return 0

        fun updateRotten(i: Int, j: Int, visited: MutableSet<String>) {
            val key = "$i$j"
            if (grid[i][j] != 1 || visited.contains(key)) return
            grid[i][j] = 2
            freshCount--
            visited.add(key)
        }

        while (freshCount > 0) {
            val lastCount = freshCount
            val visited = mutableSetOf<String>()
            for (i in grid.indices) {
                for (j in grid[0].indices) {
                    if (grid[i][j] == 2 && !visited.contains("$i$j")) {
                        if (safeIndex(i - 1, j)) updateRotten(i - 1, j, visited)
                        if (safeIndex(i + 1, j)) updateRotten(i + 1, j, visited)
                        if (safeIndex(i, j + 1)) updateRotten(i, j + 1, visited)
                        if (safeIndex(i, j - 1)) updateRotten(i, j - 1, visited)
                    }
                }
            }
            if (freshCount == lastCount) return -1
            dayTaken++
        }

        return dayTaken
    }

    companion object {
        fun orangesRotting() {
            val input = generateIntArray("[[2,1,1],[1,1,0],[0,1,1]]")
            printArray(input)
            println(Aug20LeetCode().orangesRotting(input))
        }
    }
}

fun main() {
    Aug20LeetCode.orangesRotting()
}