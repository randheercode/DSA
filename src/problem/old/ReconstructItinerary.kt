package problem.old

import java.util.*


/**
 * Created by randheercode
 * Date: 28/6/20
 * Time: 7:16 pm
 */
class ReconstructItinerary {

    // Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
    // Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
    fun findItinerary(tickets: List<List<String>>): List<String> {
        val res = ArrayList<String>()
        fun dfs(map: HashMap<String, PriorityQueue<String>>, from: String) {
            if (map[from] == null || map[from]!!.isEmpty()) {
                res.add(from)
                return
            }
            while (!map[from]!!.isEmpty()) {
                val next = map[from]!!.poll()
                dfs(map, next)
            }
            res.add(from)
        }

        val map = HashMap<String, PriorityQueue<String>>()
        tickets.forEach { (from, to) ->
            map.computeIfAbsent(from) { PriorityQueue() }.add(to)
        }
        dfs(map, "JFK")
        res.reverse()
        return res
    }

}