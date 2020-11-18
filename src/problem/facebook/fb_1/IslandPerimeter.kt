package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:44 am
 */
class IslandPerimeter {
    fun islandPerimeter(grid: Array<IntArray>): Int {
        val visited = Array(grid.size) {
            BooleanArray(grid[0].size) { false }
        }
        var count = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    count += count(grid, i, j)
                    visited[i][j] = true
                }
            }
        }
        return count
    }

    private fun count(grid: Array<IntArray>, r: Int, c: Int): Int {
        var count = 0
        if (r == 0 || (r > 0 && grid[r - 1][c] != 1)) count++
        if (r == grid.size - 1 || (r + 1 < grid.size && grid[r + 1][c] != 1)) count++
        if (c == 0 || (c > 0 && grid[r][c - 1] != 1)) count++
        if (c == grid[0].size - 1 || (c + 1 < grid[0].size && grid[r][c + 1] != 1)) count++
        return count
    }
}