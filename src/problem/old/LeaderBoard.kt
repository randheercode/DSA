package problem.old

import java.util.*


/**
 * Created by randheercode
 * Date: 8/6/20
 * Time: 11:44 am
 */
internal class Leaderboard {
    var players: MutableMap<Int, Player> = mutableMapOf()
    var scores = TreeSet(Comparator { a: Player, b: Player -> if (b.score - a.score == 0) a.id - b.id else b.score - a.score })

    fun addScore(playerId: Int, score: Int) {
        val cur: Player?
        if (players.containsKey(playerId)) {
            cur = players[playerId]
            scores.remove(cur)
            cur!!.score += score
            scores.add(cur)
        } else {
            cur = Player(playerId, score)
            players[playerId] = cur
            scores.add(cur)
        }
    }

    fun top(K: Int): Int {
        var K = K
        val iterator: Iterator<Player?> = scores.iterator()
        var res = 0
        while (K-- > 0 && iterator.hasNext()) {
            res += iterator.next()?.score ?: 0
        }
        return res
    }

    fun reset(playerId: Int) {
        val cur = players[playerId]
        scores.remove(cur)
        cur!!.score = 0
    }

    inner class Player(var id: Int, var score: Int)
}