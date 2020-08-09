package problem.old

import java.util.*

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
internal class CheapestFlight {
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, K: Int): Int {
        val map: MutableMap<Int, MutableList<IntArray>> = mutableMapOf()
        for (f in flights) {
            map.putIfAbsent(f[0], mutableListOf())
            map[f[0]]!!.add(intArrayOf(f[1], f[2]))
        }
        val q = PriorityQueue<IntArray> { a, b -> a[0].compareTo(b[0]) }
        q.offer(intArrayOf(0, src, K + 1))
        while (!q.isEmpty()) {
            val c = q.poll()
            val cost = c[0]
            val curr = c[1]
            val stop = c[2]
            if (curr == dst) return cost
            if (stop > 0) {
                if (!map.containsKey(curr)) continue
                for (next in map[curr]!!) {
                    q.add(intArrayOf(cost + next[1], next[0], stop - 1))
                }
            }
        }
        return -1
    }
}