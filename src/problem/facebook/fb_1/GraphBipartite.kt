package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 11/11/20
 * Time: 10:03 am
 */
class GraphBipartite {
    fun isBipartite(graph: Array<IntArray>): Boolean {
        val n = graph.size
        val color = IntArray(n)
        Arrays.fill(color, -1)
        for (start in 0 until n) {
            if (color[start] == -1) {
                val stack: Stack<Int> = Stack<Int>()
                stack.push(start)
                color[start] = 0
                while (!stack.empty()) {
                    val node = stack.pop()
                    for (nei in graph[node!!]) {
                        if (color[nei] == -1) {
                            stack.push(nei)
                            color[nei] = color[node] xor 1
                        } else if (color[nei] == color[node]) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    }
}