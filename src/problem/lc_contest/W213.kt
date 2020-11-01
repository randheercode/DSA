package problem.lc_contest

import java.util.*


class W213 {

    private fun findArrayWithElement(pieces: Array<IntArray>, element: Int): IntArray? {
        return pieces.find { it.indexOf(element) == 0 }
    }

    fun canFormArray(arr: IntArray, pieces: Array<IntArray>): Boolean {
        var arrIdx = 0

        while (arrIdx < arr.size) {
            val piece = findArrayWithElement(pieces, arr[arrIdx]) ?: return false
            var pieceIdx = 0
            while (arrIdx < arr.size && pieceIdx < piece.size) {
                if (arr[arrIdx] == piece[pieceIdx]) {
                    arrIdx++
                    pieceIdx++
                } else {
                    return false
                }
            }
            if (pieceIdx != piece.size) return false
        }

        return arrIdx == arr.size
    }

    private val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')
    private val vowelIdx = mutableMapOf<Char, Int>(
            'a' to 0,
            'e' to 1,
            'i' to 2,
            'o' to 3,
            'u' to 4
    )

    private fun getVowelAt(i: Int): Char {
        return vowels[i]
    }

    private fun maxPrefixIdx(prefix: String): Int {
        var ans = 0
        for (p in prefix) {
            ans = maxOf(ans, vowelIdx[p]!!)
        }
        return ans
    }

    private fun generatePermutation(lenRem: Int, prefix: String, list: MutableList<String>) {
        if (lenRem != 0) {
            val maxPrefix = maxPrefixIdx(prefix)
            for (i in maxPrefix until vowels.size) {
                generatePermutation(lenRem - 1, prefix + getVowelAt(i), list)
            }
        } else {
            list.add(prefix)
        }
    }

    private fun isOrdered(str: String): Boolean {
        for (i in 1 until str.length) {
            if (str[i - 1] > str[i]) return false
        }
        return true
    }

    private fun countOrdered(permutation: List<String>): Int {
        return permutation.filter { isOrdered(it) }.count()
    }

    fun countVowelStrings(n: Int): Int {
        val permutations = mutableListOf<String>()
        generatePermutation(n, "", permutations)
        return countOrdered(permutations)
    }

    fun countVowelStringsOptimised(n: Int): Int {
        var ans = 0
        for (j in 1..n + 1) {
            var sum = 0
            for (i in 1..j) {
                sum += i
                ans += sum
            }
        }
        return ans
    }

    fun furthestBuilding(heights: IntArray, bricks: Int, ladders: Int): Int {
        var bricks = bricks
        var ladders = ladders
        val pq = PriorityQueue<Int>()
        for (i in 1 until heights.size) {
            val curDiff = heights[i] - heights[i - 1]
            // if current building is lower than previous one, then it’s no-op.
            if (curDiff <= 0) continue
            pq.add(curDiff)
            // Use bricks for the smallest gap.
            if (pq.size > ladders) bricks -= pq.poll()
            if (bricks < 0) return i - 1
        }
        return heights.size - 1
    }

}

