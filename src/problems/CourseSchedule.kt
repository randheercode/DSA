package problems

import java.util.*


/**
 * Created by randheercode
 * Date: 29/5/20
 * Time: 8:47 pm
 * Problem Statement: There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 */
class CourseSchedule {
    lateinit var visited: IntArray
    lateinit var graph: MutableList<MutableList<Int>>
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        visited = IntArray(numCourses)
        graph = mutableListOf()
        for (i in 0 until numCourses) graph.add(mutableListOf())
        for (p in prerequisites) graph[p[1]].add(p[0])
        for (i in 0 until numCourses) if (hasCycle(i)) return false
        return true
    }

    private fun hasCycle(u: Int): Boolean {
        if (visited[u] == 2) return false
        if (visited[u] == 1) return true
        visited[u] = 1
        for (v in graph[u]) if (hasCycle(v)) return true
        visited[u] = 2
        return false
    }
}