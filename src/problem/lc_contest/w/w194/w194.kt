package problem.lc_contest.w.w194

import java.util.*


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {
    fun xorOperation(n: Int, start: Int): Int {
        var result = start
        for (i in 1 until n) {
            result = result xor (start + 2 * i)
        }
        return result
    }
}

private fun test1() {
    println(Prob1().xorOperation(5, 0))
    println(Prob1().xorOperation(4, 3))
    println(Prob1().xorOperation(1, 7))
    println(Prob1().xorOperation(10, 5))
}

private class Prob2 {
    fun getFolderNames(names: Array<String>): Array<String> {
        val nameMap = hashMapOf<String, Int>()
        names.forEachIndexed { index, name ->
            if (nameMap.contains(name)) {
                var count = (nameMap[name] ?: 0).plus(1)
                while (nameMap.containsKey("$name($count)")) {
                    count++
                }
                val actualName = "$name($count)"
                nameMap[name] = count
                names[index] = actualName
                nameMap[actualName] = 0
            } else {
                nameMap[name] = 0
            }
        }
        return names
    }
}

private fun test2() {
    println(Prob2().getFolderNames(arrayOf("pes", "fifa", "gta", "pes(2019)")).toList())
    println(Prob2().getFolderNames(arrayOf("gta", "gta(1)", "gta", "avalon")).toList())
    println(Prob2().getFolderNames(arrayOf("onepiece", "onepiece(1)", "onepiece(2)", "onepiece(3)", "onepiece")).toList())
    println(Prob2().getFolderNames(arrayOf("wano", "wano", "wano", "wano")).toList())
    println(Prob2().getFolderNames(arrayOf("kaido", "kaido(1)", "kaido", "kaido(1)")).toList())
    println(Prob2().getFolderNames(arrayOf("kaido", "kaido(1)", "kaido", "kaido(1)", "kaido(2)")).toList())
}

private class Prob3 {
    fun avoidFlood(rains: IntArray): IntArray {
        val map: MutableMap<Int, Int> = mutableMapOf()
        val zeros = TreeSet<Int>()
        val res = IntArray(rains.size)
        for (i in rains.indices) {
            if (rains[i] == 0) {
                zeros.add(i)
            } else {
                res[i] = -1
                if (map.containsKey(rains[i])) {
                    // find the location of zero that can be used to empty the previous rains[i]
                    val next = zeros.ceiling(map[rains[i]]) ?: return IntArray(0)
                    res[next] = rains[i]
                    zeros.remove(next)
                }
                map[rains[i]] = i
            }
        }
        val iter: Iterator<Int> = zeros.iterator()
        while (iter.hasNext()) res[iter.next()] = 1
        return res
    }
}

private fun test3() {
    println(Prob3().avoidFlood(intArrayOf(1, 2, 3, 4)).toList())
    println(Prob3().avoidFlood(intArrayOf(1, 2, 0, 0, 2, 1)).toList())
    println(Prob3().avoidFlood(intArrayOf(1, 2, 0, 1, 2)).toList())
    println(Prob3().avoidFlood(intArrayOf(69, 0, 0, 0, 69)).toList())
    println(Prob3().avoidFlood(intArrayOf(10, 20, 20)).toList())
}

private class Prob4 {

}

private fun test4() {
    println(Prob4())
}

fun main() {
    when (3) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
