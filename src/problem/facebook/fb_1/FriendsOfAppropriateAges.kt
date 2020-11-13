package problem.facebook.fb_1

/**
 * Created by randheercode
 * Date: 13/11/20
 * Time: 10:15 am
 */
class FriendsOfAppropriateAges {
    fun numFriendRequests(ages: IntArray): Int {
        var res = 0
        val numInAge = IntArray(121)
        val sumInAge = IntArray(121)

        for (i in ages) numInAge[i]++

        for (i in 1..120) sumInAge[i] = numInAge[i] + sumInAge[i - 1]

        for (i in 15..120) {
            if (numInAge[i] == 0) continue
            val count = sumInAge[i] - sumInAge[i / 2 + 7]
            res += count * numInAge[i] - numInAge[i] //people will not friend request themselves, so  - numInAge[i]
        }
        return res
    }
}