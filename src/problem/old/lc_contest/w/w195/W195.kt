package problem.old.lc_contest.w.w195


/**
 * Created by randheercode
 * LeetCode Contest.
 */
private class Prob1 {

}

private fun test1() {
    println(Prob1())
}

private class Prob2 {

}

private fun test2() {
    println(Prob2())
}

private class Prob3 {


}

private fun test3() {
    println(Prob3())
}

private class Prob4 {

}

private fun test4() {
    println(Prob4())
}

// Java Implementation of above approach


internal object GFG {
    // Function to return the total
    // requried sub-sequences
    fun solve(test: String, MOD: Int): Int {
        val size = test.length
        var total = 0

        // Find ways for all values of x
        for (i in 0..8) {

            // x+1
            val y = i + 1
            var newtest = ""

            // Removing all unnecessary digits
            for (j in 0 until size) {
                if (test[j].toInt() == i + 48 || test[j].toInt() == y + 48) {
                    newtest += test[j]
                }
            }
            if (newtest.length > 0) {
                val size1 = newtest.length

                // Prefix Sum Array for X+1 digit
                val prefix = IntArray(size1)
                for (j in 0 until size1) {
                    prefix[j] = 0
                    if (newtest[j].toInt() == y + 48) {
                        prefix[j]++
                    }
                }
                for (j in 1 until size1) {
                    prefix[j] += prefix[j - 1]
                }
                var count = 0
                var firstcount = 0

                // Sum of squares
                var ss = 0

                // Previous sum of all possible pairs
                var prev = 0
                for (j in 0 until size1) {
                    if (newtest[j].toInt() == i + 48) {
                        count++
                        firstcount++
                    } else {
                        ss += count * count

                        // To find sum of multiplication of all
                        // possible pairs
                        var pairsum = (firstcount * firstcount - ss) / 2
                        val temp = pairsum

                        // To prevent overcounting
                        pairsum -= prev
                        prev = temp
                        var secondway = prefix[size1 - 1]
                        if (j != 0) secondway -= prefix[j - 1]
                        var answer = (count * (count - 1)
                                * secondway * (secondway - 1))
                        answer /= 4
                        answer += (pairsum * secondway
                                * (secondway - 1)) / 2

                        // Adding ways for all possible x
                        total += answer
                        count = 0
                    }
                }
            }
        }
        return total
    }

    // Driver code
    @JvmStatic
    fun main(args: Array<String>) {
        val test = "13134422"
        val MOD = 1000000007
        println(solve(test, MOD))
    }
}

// This code is contributed by krikti..


fun main() {
    when (1) {
        1 -> test1()
        2 -> test2()
        3 -> test3()
        4 -> test4()
    }
}
