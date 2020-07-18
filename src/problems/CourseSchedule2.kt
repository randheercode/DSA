package problems

import java.util.*


/**
 * Created by randheercode
 * Date: 29/5/20
 * Time: 8:47 pm
 * https://leetcode.com/problems/course-schedule-ii/solution/
 */
internal class CourseSchedule2 {
    var isPossible = true
    var color: MutableMap<Int, Int> = mutableMapOf()
    var adjList: MutableMap<Int, MutableList<Int>> = mutableMapOf()
    var topologicalOrder: MutableList<Int> = mutableListOf()

    private fun init(numCourses: Int) {
        isPossible = true
        for (i in 0 until numCourses) {
            color[i] = WHITE
        }
    }

    private fun dfs(node: Int) {

        // Don't recurse further if we found a cycle already
        if (!isPossible) {
            return
        }

        // Start the recursion
        color[node] = GRAY

        // Traverse on neighboring vertices
        for (neighbor in adjList.getOrDefault(node, ArrayList())) {
            if (color[neighbor] == WHITE) {
                dfs(neighbor)
            } else if (color[neighbor] == GRAY) {
                // An edge to a GRAY vertex represents a cycle
                isPossible = false
            }
        }

        // Recursion ends. We mark it as black
        color[node] = BLACK
        topologicalOrder.add(node)
    }

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        init(numCourses)

        // Create the adjacency list representation of the graph
        for (i in prerequisites.indices) {
            val dest = prerequisites[i][0]
            val src = prerequisites[i][1]
            val lst = adjList.getOrDefault(src, mutableListOf())
            lst.add(dest)
            adjList[src] = lst
        }

        // If the node is unprocessed, then call dfs on it.
        for (i in 0 until numCourses) {
            if (color[i] == WHITE) {
                dfs(i)
            }
        }
        val order: IntArray
        if (isPossible) {
            order = IntArray(numCourses)
            for (i in 0 until numCourses) {
                order[i] = topologicalOrder[numCourses - i - 1]
            }
        } else {
            order = IntArray(0)
        }
        return order
    }

    companion object {
        var WHITE = 1
        var GRAY = 2
        var BLACK = 3
    }
}