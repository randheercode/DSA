package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 12/11/20
 * Time: 11:59 am
 */
class ShortestDistanceFromAllBuildings {
    private val dir = arrayOf(intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(1, 0), intArrayOf(-1, 0))
    fun shortestDistance(grid: Array<IntArray>): Int {
        val n = grid.size
        val m: Int = grid[0].size
        val dp = Array(n) { IntArray(m) }
        val reach = Array(n) { IntArray(m) }
        var countBuilding = 0
        val queue: Queue<IntArray> = LinkedList()

        // step 1
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == 1) {
                    queue.offer(intArrayOf(i, j))
                    bfs(queue, grid, dp, reach, n, m)
                    countBuilding++
                }
            }
        }

        // step 2
        var result = Int.MAX_VALUE
        for (i in 0 until n) {
            for (j in 0 until m) {
                // WARNING: DO NOT FORGET to check whether current point is 0 and whether current point can reach all buildings
                if (grid[i][j] == 0 && reach[i][j] == countBuilding) {
                    result = Math.min(result, dp[i][j])
                }
            }
        }
        return if (result == Int.MAX_VALUE) -1 else result
    }

    private fun bfs(queue: Queue<IntArray>, grid: Array<IntArray>, dp: Array<IntArray>, reach: Array<IntArray>, n: Int, m: Int) {
        var level = 1
        // DO NOT USE hashset, since hashset cannot determine whether it contains an array (coordinate)
        val visited = Array(n) { BooleanArray(m) }
        while (!queue.isEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val curPoint = queue.poll()
                val x = curPoint[0]
                val y = curPoint[1]
                for (j in 0..3) {
                    val dx = x + dir[j][0]
                    val dy = y + dir[j][1]
                    if (dx < 0 || dx > n - 1 || dy < 0 || dy > m - 1 || grid[dx][dy] != 0 || visited[dx][dy]) {
                        continue
                    }
                    queue.offer(intArrayOf(dx, dy))
                    visited[dx][dy] = true
                    dp[dx][dy] += level
                    reach[dx][dy]++
                }
            }
            level++
        }
    }
}