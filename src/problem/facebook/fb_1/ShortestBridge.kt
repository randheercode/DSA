package problem.facebook.fb_1

import java.lang.Exception
import java.util.*


/**
 * Created by randheercode
 * Date: 16/11/20
 * Time: 12:13 pm
 */
class ShortestBridge {
    class Node(var r: Int, var c: Int, var depth: Int)

    fun shortestBridge(A: Array<IntArray>): Int {
        val R = A.size
        val C: Int = A[0].size
        val colors = getComponents(A)
        val queue: Queue<Node> = LinkedList()
        val target: MutableSet<Int> = HashSet()
        for (r in 0 until R) for (c in 0 until C) {
            if (colors[r][c] == 1) {
                queue.add(Node(r, c, 0))
            } else if (colors[r][c] == 2) {
                target.add(r * C + c)
            }
        }
        while (!queue.isEmpty()) {
            val node = queue.poll()
            if (target.contains(node.r * C + node.c)) return node.depth - 1
            for (nei in neighbors(A, node.r, node.c)) {
                val nr = nei?.div(C)!!
                val nc = nei.rem(C)
                if (colors[nr][nc] != 1) {
                    queue.add(Node(nr, nc, node.depth + 1))
                    colors[nr][nc] = 1
                }
            }
        }
        throw Exception()
    }

    private fun getComponents(A: Array<IntArray>): Array<IntArray> {
        val R = A.size
        val C: Int = A[0].size
        val colors = Array(R) { IntArray(C) }
        var t = 0
        for (r0 in 0 until R) for (c0 in 0 until C) if (colors[r0][c0] == 0 && A[r0][c0] == 1) {
            // Start dfs
            val stack: Stack<Int> = Stack()
            stack.push(r0 * C + c0)
            colors[r0][c0] = ++t
            while (!stack.isEmpty()) {
                val node = stack.pop()
                val r = node / C
                val c = node % C
                for (nei in neighbors(A, r, c)) {
                    val nr = nei?.div(C)!!
                    val nc = nei?.rem(C)!!
                    if (A[nr][nc] == 1 && colors[nr][nc] == 0) {
                        colors[nr][nc] = t
                        stack.push(nr * C + nc)
                    }
                }
            }
        }
        return colors
    }

    fun neighbors(A: Array<IntArray>, r: Int, c: Int): List<Int?> {
        val R = A.size
        val C: Int = A[0].size
        val ans: MutableList<Int> = ArrayList()
        if (0 <= r - 1) ans.add((r - 1) * R + c)
        if (0 <= c - 1) ans.add(r * R + (c - 1))
        if (r + 1 < R) ans.add((r + 1) * R + c)
        if (c + 1 < C) ans.add(r * R + (c + 1))
        return ans
    }
}