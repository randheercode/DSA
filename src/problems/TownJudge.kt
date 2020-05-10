package problems

/**
 * Created by randheercode
 * Date: 11/5/20
 * Time: 12:41 am
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.
 * If the town judge exists, then:
 * The town judge trusts nobody.
 * Everybody (except for the town judge) trusts the town judge.
 * There is exactly one person that satisfies properties 1 and 2.
 * You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.
 * If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.
 */
class TownJudge {
    fun findJudge(N: Int, trust: Array<IntArray>): Int {
        val people = (1..N).toMutableSet()
        val trustCount = mutableMapOf<Int, Int>()
        trust.forEach {
            if (trustCount.containsKey(it[1])) {
                trustCount[it[1]] = (trustCount[it[1]] ?: 0) + 1
            } else {
                trustCount[it[1]] = 1
            }
            people.remove(it[0])
        }
        if (people.isEmpty()) return -1
        var finalResult = -1
        println(people)
        people.forEach {
            if (trustCount[it] ?: 0 == N - 1) {
                finalResult = it
                return@forEach
            }
        }
        return finalResult
    }
}

fun main() {
    println(TownJudge().findJudge(1, arrayOf()))
}