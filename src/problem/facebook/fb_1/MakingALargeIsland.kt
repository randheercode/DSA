package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 16/11/20
 * Time: 12:11 pm
 */
class MakingALargeIsland {
    var dr = intArrayOf(-1, 0, 1, 0)
    var dc = intArrayOf(0, -1, 0, 1)
    lateinit var grid: Array<IntArray>
    var N = 0

    fun largestIsland(grid: Array<IntArray>): Int {
        this.grid = grid
        N = grid.size
        var index = 2
        val area = IntArray(N * N + 2)
        for (r in 0 until N) for (c in 0 until N) if (grid[r][c] == 1) area[index] = dfs(r, c, index++)
        var ans = 0
        for (x in area) ans = Math.max(ans, x)
        for (r in 0 until N) for (c in 0 until N) if (grid[r][c] == 0) {
            val seen: MutableSet<Int> = HashSet()
            for (move in neighbors(r, c)) if (grid[move!! / N][move % N] > 1) seen.add(grid[move / N][move % N])
            var bns = 1
            for (i in seen) bns += area[i]
            ans = Math.max(ans, bns)
        }
        return ans
    }

    private fun dfs(r: Int, c: Int, index: Int): Int {
        var ans = 1
        grid[r][c] = index
        for (move in neighbors(r, c)) {
            if (grid[move!! / N][move % N] == 1) {
                grid[move / N][move % N] = index
                ans += dfs(move / N, move % N, index)
            }
        }
        return ans
    }

    private fun neighbors(r: Int, c: Int): List<Int?> {
        val ans: MutableList<Int> = ArrayList()
        for (k in 0..3) {
            val nr = r + dr[k]
            val nc = c + dc[k]
            if (nr in 0 until N && 0 <= nc && nc < N) ans.add(nr * N + nc)
        }
        return ans
    }
}