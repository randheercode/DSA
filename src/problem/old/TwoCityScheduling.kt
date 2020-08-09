package problem.old

import generateIntArray


/**
 * Created by randheercode
 * Date: 3/6/20
 * Time: 3:26 pm
 * Problem: There are 2N people a company is planning to interview. The cost of flying the i-th person to city A is costs[i][0],
 * and the cost of flying the i-th person to city B is costs[i][1].
 * Return the minimum cost to fly every person to a city such that exactly N people arrive in each city.
 */
class TwoCityScheduling {
    fun twoCitySchedCost(costs: Array<IntArray>): Int {
        costs.sortWith(Comparator { x, y -> x[0] - x[1] - (y[0] - y[1]) })

        var i = 0
        var j: Int = costs.size - 1
        var sum = 0
        while (i < j) {
            sum += costs[i][0] + costs[j][1]
            ++i
            --j
        }

        return sum
    }
}

fun main() {
    println(TwoCityScheduling().twoCitySchedCost(generateIntArray("[[10,20],[30,200],[400,50],[30,20],[100,50],[50,100]]")))
}