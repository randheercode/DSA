package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 18/11/20
 * Time: 11:45 am
 */
class RobotCleaner {
    interface Robot {
        // Returns true if the cell in front is open and robot moves into the cell.
        // Returns false if the cell in front is blocked and robot stays in the current cell.
        fun move(): Boolean

        // Robot will stay in the same cell after calling turnLeft/turnRight.
        // Each turn will be 90 degrees.
        fun turnLeft()
        fun turnRight()

        // Clean the current cell.
        fun clean()
    }

    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    var directions = arrayOf(intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1))
    var visited: MutableSet<Pair<Int, Int>> = mutableSetOf()
    var robot: problem.facebook.old.Robot? = null

    private fun goBack() {
        robot!!.turnRight()
        robot!!.turnRight()
        robot!!.move()
        robot!!.turnRight()
        robot!!.turnRight()
    }

    private fun backtrack(row: Int, col: Int, d: Int) {
        visited.add(Pair(row, col))
        robot!!.clean()
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (i in 0..3) {
            val newD = (d + i) % 4
            val newRow = row + directions[newD][0]
            val newCol = col + directions[newD][1]
            if (!visited.contains(Pair(newRow, newCol)) && robot!!.move()) {
                backtrack(newRow, newCol, newD)
                goBack()
            }
            // turn the robot following chosen direction : clockwise
            robot!!.turnRight()
        }
    }

    fun cleanRoom(robot: problem.facebook.old.Robot?) {
        this.robot = robot
        backtrack(0, 0, 0)
    }
}