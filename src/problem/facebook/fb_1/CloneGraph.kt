package problem.facebook.fb_1

import java.util.*


/**
 * Created by randheercode
 * Date: 17/11/20
 * Time: 4:45 pm
 */
class CloneGraph {
    class Node {
        var `val`: Int
        var neighbors: MutableList<Node>

        constructor() {
            `val` = 0
            neighbors = ArrayList()
        }

        constructor(_val: Int) {
            `val` = _val
            neighbors = ArrayList()
        }

        constructor(_val: Int, _neighbors: ArrayList<Node>) {
            `val` = _val
            neighbors = _neighbors
        }
    }

    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        val q = ArrayDeque<Node>()
        val m = HashMap<Node, Node>()
        q.offer(node)
        m[node] = Node(node.`val`)
        while (!q.isEmpty()) {
            val cur = q.poll()
            for (nei in cur.neighbors) {
                if (!m.contains(nei)) {
                    m[nei] = Node(nei.`val`)
                    q.offer(nei)
                }
                m[cur]!!.neighbors.add(m[nei]!!)
            }
        }
        return m[node]
    }
}