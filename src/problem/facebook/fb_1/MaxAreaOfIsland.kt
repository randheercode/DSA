package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:43 am
 */
class MaxAreaOfIsland {
    lateinit var grid: Array<IntArray>
    lateinit var seen: Array<BooleanArray>

    private fun area(r: Int, c: Int): Int {
        if (r < 0 || r >= grid.size || c < 0 || c >= grid[0].size ||
                seen[r][c] || grid[r][c] == 0) return 0
        seen[r][c] = true
        return (1 + area(r + 1, c) + area(r - 1, c)
                + area(r, c - 1) + area(r, c + 1))
    }

    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        this.grid = grid
        seen = Array(grid.size) { BooleanArray(grid[0].size) }
        var ans = 0
        for (r in grid.indices) {
            for (c in grid[0].indices) {
                ans = maxOf(ans, area(r, c))
            }
        }
        return ans
    }
}