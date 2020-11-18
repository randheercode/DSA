package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:47 am
 */
class NumberOfIslands {
    fun dfs(grid: Array<CharArray>, r: Int, c: Int) {
        val nr = grid.size
        val nc: Int = grid[0].size
        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return
        }
        grid[r][c] = '0'
        dfs(grid, r - 1, c)
        dfs(grid, r + 1, c)
        dfs(grid, r, c - 1)
        dfs(grid, r, c + 1)
    }

    fun numIslands(grid: Array<CharArray>?): Int {
        if (grid == null || grid.isEmpty()) {
            return 0
        }
        val nr = grid.size
        val nc: Int = grid[0].size
        var numIslands = 0
        for (r in 0 until nr) {
            for (c in 0 until nc) {
                if (grid[r][c] == '1') {
                    ++numIslands
                    dfs(grid, r, c)
                }
            }
        }
        return numIslands
    }
}