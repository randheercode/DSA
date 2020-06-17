package problems

import utils.TreeNode
import java.util.*


/**
 * Created by randheercode
 * Date: 3/6/20
 * Time: 5:46 pm
 * Problems:
 * https://leetcode.com/problems/reverse-string-ii/
 * https://leetcode.com/problems/strobogrammatic-number/
 * https://leetcode.com/problems/string-compression/
 */
class MultipleProblemLCEasy {
    fun reverseStr(s: String, k: Int): String {
        val resultBuilder = StringBuilder()
        var startIndex = 0
        val oneGo = 2.times(k)
        val length = s.length
        while (startIndex + oneGo < length) {
            resultBuilder.append(s.substring(startIndex until startIndex.plus(k)).reversed())
            resultBuilder.append(s.substring(startIndex.plus(k) until startIndex.plus(oneGo)))
            startIndex = startIndex.plus(oneGo)
        }

        if (length.minus(startIndex) >= k) {
            resultBuilder.append(s.substring(startIndex until startIndex.plus(k)).reversed())
            resultBuilder.append(s.substring(startIndex.plus(k) until length))
        } else {
            resultBuilder.append(s.substring(startIndex until length).reversed())
        }
        return resultBuilder.toString()
    }

    fun isStrobogrammatic(num: String): Boolean {
        var start = 0
        var end: Int = num.lastIndex
        while (start <= end) {
            when (num[start]) {
                '0', '1', '8' -> if (num[end] != num[start]) {
                    return false
                }
                '6' -> if (num[end] != '9') {
                    return false
                }
                '9' -> if (num[end] != '6') {
                    return false
                }
                else -> return false
            }
            start++
            end--
        }
        return true
    }

    fun compress(chars: CharArray): Int {
        val length = chars.size
        var scanIndex = 0
        var startIndex = 0
        var writeIndex = 0
        var currentChar: Char
        while (scanIndex < length) {
            currentChar = chars[scanIndex]
            while (scanIndex + 1 < length && chars[scanIndex + 1] == currentChar) scanIndex += 1
            val charCount = scanIndex - startIndex + 1
            if (charCount == 1) {
                chars[writeIndex] = currentChar
                writeIndex += 1
                scanIndex += 1
                startIndex = scanIndex
            } else {
                chars[writeIndex] = currentChar
                writeIndex += 1
                val charCountS = charCount.toString()
                for (i in charCountS.indices) {
                    chars[writeIndex] = charCountS[i]
                    writeIndex += 1
                }
                scanIndex += 1
                startIndex = scanIndex
            }
        }
        return writeIndex
    }

    fun countAndSay(n: Int): String {
        if (n == 1) return "1"
        val last = countAndSay(n - 1)
        val length = last.length
        val builder = StringBuilder()
        var start = 0
        var current = 0
        var char: Char
        while (current < length) {
            char = last[current]
            while (current + 1 < length && last[current + 1] == char) current += 1
            val count = current - start + 1
            builder.append(count).append(char)
            start = current + 1
            current = start
        }
        return builder.toString()

    }

    fun countBinarySubString(s: String): Int {
        var ans = 0
        var prev = 0
        var cur = 1
        for (i in 1 until s.length) {
            if (s[i - 1] != s[i]) {
                ans += minOf(prev, cur)
                prev = cur
                cur = 1
            } else {
                cur++
            }
        }
        return ans + minOf(prev, cur)
    }

    fun twoSum(numbers: IntArray, target: Int): IntArray {
        val indices = IntArray(2)
        if (numbers.size < 2) return indices
        var left = 0
        var right: Int = numbers.size - 1
        while (left < right) {
            val v: Int = numbers.get(left) + numbers.get(right)
            if (v == target) {
                indices[0] = left + 1
                indices[1] = right + 1
                break
            } else if (v > target) {
                right--
            } else {
                left++
            }
        }
        return indices
    }

    fun twoSumAnother(numbers: IntArray, target: Int): IntArray {
        for (i in numbers.indices) {
            val j = numbers.binarySearch(target - numbers[i], i + 1)
            if (j > 0) return intArrayOf(i + 1, j + 1)
        }
        return intArrayOf()
    }

    fun twoSumAnother1(numbers: IntArray, target: Int): IntArray {
        var l = 0
        var r: Int = numbers.size - 1
        while (numbers[l] + numbers[r] != target) {
            if (numbers[l] + numbers[r] > target) r-- else l++
        }
        return intArrayOf(l + 1, r + 1)
    }

    fun isIsomorphic(s: String, t: String): Boolean {
        val mapS = mutableMapOf<Char, Char>()
        val mapT = mutableMapOf<Char, Char>()
        for (i in s.indices) {
            val sMapping = mapS.getOrPut(s[i]) { t[i] }
            val tMapping = mapT.getOrPut(t[i]) { s[i] }
            if (sMapping != t[i] || tMapping != s[i])
                return false
        }
        return true
    }

    fun isHappy(n: Int): Boolean {
        fun getNext(n: Int): Int {
            var n = n
            var totalSum = 0
            while (n > 0) {
                val d = n % 10
                n /= 10
                totalSum += d * d
            }
            return totalSum
        }

        var n = n
        val seen: MutableSet<Int> = HashSet()
        while (n != 1 && !seen.contains(n)) {
            seen.add(n)
            n = getNext(n)
        }
        return n == 1
    }

    fun constructRectangle(area: Int): IntArray? {
        var w = Math.sqrt(area.toDouble()).toInt()
        while (area % w != 0) w--
        return intArrayOf(area / w, w)
    }

    fun prevPermOpt1(A: IntArray): IntArray {
        val length = A.size
        var first = length.minus(2)
        while (first >= 0 && A[first] <= A[first + 1]) first -= 1
        if (first < 0) return A
        var second = length.minus(1)
        while (second > first && A[second] >= A[first]) second--
        while (A[second] == A[second - 1]) second--
        A[second] = A[first] + A[second]
        A[first] = A[second] - A[first]
        A[second] = A[second] - A[first]
        return A
    }

    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val ransomNotes = ransomNote.groupingBy { it }.eachCount()
        val magazines = magazine.groupingBy { it }.eachCount()
        return ransomNotes.none { magazines[it.key] ?: 0 < it.value }
    }

    fun isMonotonic(A: IntArray): Boolean {
        if (A.size <= 1) return true
        val range = 1..A.lastIndex
        var increasing = false
        for (i in range) {
            if (A[i - 1] > A[i]) {
                increasing = false
                break
            } else if (A[i - 1] < A[i]) {
                increasing = true
                break
            }
        }
        for (i in 1..A.lastIndex) {
            if ((A[i - 1] > A[i] && increasing) || (A[i - 1] < A[i] && !increasing)) {
                return false
            }
        }
        return true
    }

    fun isMonotonic1(A: IntArray): Boolean {
        var k = 0
        var a = 0
        for (i in 0 until A.size - 1) {
            if (A[i] >= A[i + 1])
                k++
            if (A[i] <= A[i + 1])
                a++
        }
        return k == A.size - 1 || a == A.size - 1
    }

    fun moveZeroes(nums: IntArray) {
        var cp = 0
        var wp = 0
        for (i in nums.indices) {
            if (nums[cp] != 0) {
                nums[wp] = nums[cp]
                if (wp != cp) nums[cp] = 0
                cp++
                wp++
            } else {
                cp++
            }
        }
    }

    fun reverseString(s: CharArray): Unit {
        var start = 0
        var end = s.lastIndex
        var temp: Char
        while (start < end) {
            temp = s[start]
            s[start] = s[end]
            s[end] = temp
            start++
            end--
        }
    }

    fun countElements(arr: IntArray): Int {
        val elements = arr.toHashSet()
        var count = 0
        for (a in arr) if (elements.contains(a + 1)) count++
        return count
    }

    fun groupAnagrams(strs: Array<String>): List<List<String>>? {
        if (strs.isEmpty()) return listOf()
        val ans: MutableMap<String, MutableList<String>> = HashMap()
        for (s in strs) {
            val ca = s.toCharArray()
            Arrays.sort(ca)
            val key = String(ca)
            if (!ans.containsKey(key)) ans[key] = mutableListOf()
            ans[key]!!.add(s)
        }
        return ArrayList(ans.values)
    }

    fun productExceptSelf(nums: IntArray): IntArray {
        val forwardProd = IntArray(nums.size) { 1 }
        val reverseProd = IntArray(nums.size) { 1 }

        for (i in nums.indices) {
            forwardProd[i] = if (i == 0) nums.first() else forwardProd[i - 1].times(nums[i])
            val reverseIndex = nums.lastIndex.minus(i)
            reverseProd[reverseIndex] = if (reverseIndex == nums.lastIndex) nums.last() else reverseProd[reverseIndex + 1].times(nums[reverseIndex])
        }

        for (i in nums.indices) {
            nums[i] = when (i) {
                0 -> reverseProd[i + 1]
                nums.lastIndex -> forwardProd[i - 1]
                else -> reverseProd[i + 1] * forwardProd[i - 1]
            }
        }
        return nums
    }

    fun checkValidString(s: String): Boolean {
        var left = 0
        var right = 0
        for (c in s.toCharArray()) {
            left += if (c == '(') 1 else -1
            right += if (c != ')') 1 else -1
            if (right < 0) break
            left = maxOf(left, 0)
        }
        return left == 0
    }

    fun sumEvenAfterQueries(A: IntArray, queries: Array<IntArray>): IntArray {
        var sum = 0
        for (x in A) if (x % 2 == 0) sum += x
        val ans = IntArray(queries.size)
        for (i in queries.indices) {
            val value = queries[i][0]
            val index = queries[i][1]
            if (A[index] % 2 == 0) sum -= A[index]
            A[index] += value
            if (A[index] % 2 == 0) sum += A[index]
            ans[i] = sum
        }
        return ans
    }

    fun minPathSum(grid: Array<IntArray>): Int {
        for (i in grid.size - 1 downTo 0) {
            for (j in grid[0].size - 1 downTo 0) {
                if (i == grid.size - 1 && j != grid[0].size - 1) grid[i][j] = grid[i][j] + grid[i][j + 1]
                else if (j == grid[0].size - 1 && i != grid.size - 1) grid[i][j] = grid[i][j] + grid[i + 1][j]
                else if (j != grid[0].size - 1 && i != grid.size - 1) grid[i][j] = grid[i][j] + minOf(grid[i + 1][j], grid[i][j + 1])
            }
        }
        return grid[0][0]
    }

    fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
        val result = hashSetOf<Int>()
        val first: IntArray
        val second: IntArray
        if (nums1.size <= nums2.size) {
            first = nums1
            second = nums2
        } else {
            first = nums2
            second = nums1
        }
        val set = second.toHashSet()
        for (num in first) {
            if (set.contains(num)) result.add(num)
        }
        return result.toIntArray()
    }

    fun stringShift(string: String, shift: Array<IntArray>): String {
        val overallShifts = IntArray(2)
        for (move in shift) {
            overallShifts[move[0]] += move[1]
        }
        var leftShifts = overallShifts[0]
        var rightShifts = overallShifts[1]

        // Determine which shift (if any) to perform.
        val len = string.length
        return when {
            leftShifts > rightShifts -> {
                leftShifts = (leftShifts - rightShifts) % len
                string.substring(leftShifts) + string.substring(0, leftShifts)
            }
            rightShifts > leftShifts -> {
                rightShifts = (rightShifts - leftShifts) % len
                string.substring(len - rightShifts) + string.substring(0, len - rightShifts)
            }
            else -> {
                string
            }
        }
    }

    // Middle of the Linked List
    fun middleNode(head: ListNode?): ListNode? {
        var mp = head
        var ep = head
        if (mp?.next == null) return mp
        while (ep?.next != null) {
            mp = mp?.next
            ep = ep.next?.next
        }
        return mp
    }

    // Backspace String Compare
    fun backspaceCompare(S: String, T: String): Boolean {
        var i = S.length - 1
        var j = T.length - 1
        var skipS = 0
        var skipT = 0
        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (S[i] == '#') {
                    skipS++
                    i--
                } else if (skipS > 0) {
                    skipS--
                    i--
                } else break
            }
            while (j >= 0) {
                if (T[j] == '#') {
                    skipT++
                    j--
                } else if (skipT > 0) {
                    skipT--
                    j--
                } else break
            }
            if (i >= 0 && j >= 0 && S[i] != T[j]) return false
            if (i >= 0 != j >= 0) return false
            i--
            j--
        }
        return true
    }

    fun searchInsert(nums: IntArray, target: Int): Int {
        var high = nums.size - 1
        var low = 0
        while (low <= high) {
            val mid = (high + low) / 2
            if (target == nums[mid]) return mid
            if (target < nums[mid]) high = mid - 1
            if (target > nums[mid]) low = mid + 1
        }
        return low
    }

    fun sortColors(nums: IntArray): Unit {
        val count = mutableMapOf<Int, Int>()
        for (num in nums) count[num] = count.getOrDefault(num, 0) + 1
        var index = 0
        for (i in 0..2) {
            val countI = count.getOrDefault(i, 0)
            for (ind in index until index.plus(countI)) nums[ind] = i
            index = index.plus(countI)
        }
    }

    fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
        if (root == null) return null
        if (root.`val` == `val`) return root
        return if (root.`val` > `val`) searchBST(root.left, `val`)
        else searchBST(root.right, `val`)
    }

    fun decompressRLElist(nums: IntArray): IntArray {
        val n = nums.size.div(2)
        val result = mutableListOf<Int>()
        for (i in 0..n) {
            repeat(nums[2 * i]) { result.add(nums[(2 * i) + 1]) }
        }
        return result.toIntArray()
    }

    fun findLengthOfLCIS(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var current = 1
        var max = 1
        val len = nums.size
        for (i in 1 until len) {
            if (nums[i] > nums[i - 1]) current++
            else {
                max = maxOf(current, max)
                current = 1
            }
        }
        return maxOf(current, max)
    }

    fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
        var i = 0
        var count = 0
        while (i < flowerbed.size) {
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0) && (i == flowerbed.size - 1 || flowerbed[i + 1] == 0)) {
                flowerbed[i++] = 1
                count++
            }
            if (count >= n) return true
            i++
        }
        return false
    }

    fun sumZero(n: Int): IntArray {
        val result = IntArray(n)
        var no = n / 2
        var start = 0
        var end = n - 1
        while (start < end) {
            result[start] = no.times(-1)
            result[end] = no
            start++
            end--
            no--
        }
        if (n.rem(2) != 0) result[n.div(2).plus(1)] = 0
        return result
    }

    fun tictactoe(moves: Array<IntArray>): String {
        val board = Array(3) { CharArray(3) { ' ' } }
        fun findWinner(): Char? {
            val lines = mutableListOf(
                    listOf(0, 0, 0, 1, 0, 2),
                    listOf(1, 0, 1, 1, 1, 2),
                    listOf(2, 0, 2, 1, 2, 2),
                    listOf(0, 0, 1, 0, 2, 0),
                    listOf(0, 1, 1, 1, 2, 2),
                    listOf(0, 2, 1, 2, 2, 2),
                    listOf(0, 0, 1, 1, 2, 2),
                    listOf(0, 2, 1, 1, 2, 0)
            )
            for (l in lines) {
                if (board[l[0]][l[1]] != ' ' && board[l[0]][l[1]] == board[l[2]][l[3]] && board[l[2]][l[3]] == board[l[4]][l[5]])
                    return board[l[0]][l[1]]
            }
            return null
        }

        var aMove = true
        for (move in moves) {
            board[move[0]][move[1]] = if (aMove) 'X' else 'O'
            aMove = !aMove
        }
        val winner = findWinner()
        return when {
            winner != null -> if (winner == 'X') "A" else "B"
            moves.size == 9 -> "Draw"
            else -> "Pending"
        }
    }

    fun tictactoe1(moves: Array<IntArray>): String {
        val array = Array<IntArray>(3) {
            IntArray(3)
        }

        for (i in moves.indices) {
            // player A draws X(1)
            if (i % 2 == 0) {
                array[moves[i][0]][moves[i][1]] = 1
            } else {
                array[moves[i][0]][moves[i][1]] = 2
            }
        }

        var result = ""
        var isPending = false

        // row
        for (row in array.indices) {
            if (array[row][0] == array[row][1] && array[row][1] == array[row][2]) {
                if (array[row][0] == 1) {
                    result = "A"
                } else if (array[row][0] == 2) {
                    result = "B"
                }
            }
        }

        // col
        for (col in array[0].indices) {
            if (array[0][col] == array[1][col] && array[1][col] == array[2][col]) {
                if (array[0][col] == 1) {
                    result = "A"
                } else if (array[0][col] == 2) {
                    result = "B"
                }
            }
        }

        if (array[0][0] == array[1][1] && array[1][1] == array[2][2]) {
            if (array[0][0] == 1) {
                result = "A"
            } else if (array[0][0] == 2) {
                result = "B"
            }
        }

        if (array[0][2] == array[1][1] && array[1][1] == array[2][0]) {
            if (array[0][2] == 1) {
                result = "A"
            } else if (array[0][2] == 2) {
                result = "B"
            }
        }

        if (result.isNotEmpty()) {
            return result
        }

        for (row in array.indices) {
            for (col in array.indices) {
                if (array[row][col] == 0) {
                    return "Pending"
                }
            }
        }

        return "Draw"
    }

    fun containsDuplicate(nums: IntArray): Boolean {
        return nums.toSet().size != nums.size
    }

    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val set: MutableSet<Int> = HashSet()
        for (i in nums.indices) {
            if (set.contains(nums[i])) return true
            set.add(nums[i])
            if (set.size > k) {
                set.remove(nums[i - k])
            }
        }
        return false
    }

    fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
        fun getID(x: Long, w: Long): Long {
            return if (x < 0) (x + 1) / w - 1 else x / w
        }
        if (t < 0) return false
        val d: MutableMap<Long, Long> = HashMap()
        val w = t.toLong() + 1
        for (i in nums.indices) {
            val m = getID(nums[i].toLong(), w)
            if (d.containsKey(m)) return true
            if (d.containsKey(m - 1) && Math.abs(nums[i] - d[m - 1]!!) < w) return true
            if (d.containsKey(m + 1) && Math.abs(nums[i] - d[m + 1]!!) < w) return true
            d[m] = nums[i].toLong()
            if (i >= k) d.remove(getID(nums[i - k].toLong(), w))
        }
        return false
    }

    fun rotateString(A: String, B: String): Boolean {
        val len = A.length
        if (A.isEmpty() && B.isEmpty()) return true
        return A.length == B.length && (A.indices).any {
            (A.substring(it, len) + A.substring(0, it)) == B
        }
    }

    fun removeDuplicates(S: String): String? {
        val sb = StringBuilder()
        var sbLength = 0
        for (character in S.toCharArray()) {
            if (sbLength != 0 && character == sb[sbLength - 1]) sb.deleteCharAt(sbLength-- - 1) else {
                sb.append(character)
                sbLength++
            }
        }
        return sb.toString()
    }

    fun binaryTreePaths(root: TreeNode?): List<String> {
        val path = mutableListOf<String>()

        fun constructPath(root: TreeNode?, currentPath: String) {
            if (root?.left == null && root?.right == null) {
                path.add(currentPath)
                return
            }
            if (root.left != null) constructPath(root.left, "$currentPath->${root.left?.`val`}")
            if (root.right != null) constructPath(root.right, "$currentPath->${root.right?.`val`}")
        }

        if (root != null) constructPath(root, root.`val`.toString())
        return path
    }

    fun intersect(nums1: IntArray, nums2: IntArray): IntArray? {
        if (nums1.size > nums2.size) {
            return intersect(nums2, nums1)
        }
        val m = HashMap<Int, Int>()
        for (n in nums1) {
            m[n] = m.getOrDefault(n, 0) + 1
        }
        var k = 0
        for (n in nums2) {
            val cnt = m.getOrDefault(n, 0)
            if (cnt > 0) {
                nums1[k++] = n
                m[n] = cnt - 1
            }
        }
        return nums1.copyOfRange(0, k)
    }

    fun intersect2(nums1: IntArray, nums2: IntArray): IntArray? {
        Arrays.sort(nums1)
        Arrays.sort(nums2)
        var i = 0
        var j = 0
        var k = 0
        while (i < nums1.size && j < nums2.size) {
            if (nums1[i] < nums2[j]) {
                ++i
            } else if (nums1[i] > nums2[j]) {
                ++j
            } else {
                nums1[k++] = nums1[i++]
                ++j
            }
        }
        return nums1.copyOfRange(0, k)
    }

    fun daysBetweenDates(date1: String, date2: String): Int {

        val monthDays = intArrayOf(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

        fun isLeap(year: Int): Boolean {
            if (year % 400 == 0)
                return true
            if (year % 100 == 0)
                return false
            if (year % 4 == 0)
                return true
            return false
        }

        fun daysByYear(year: Int): Int {
            return if (year == 1970) 0
            else (if (isLeap(year)) 366 else 365) + daysByYear(year - 1)
        }

        fun getDaysUntil(month: Int, year: Int): Int {
            return if (month < 1) 0
            else ((if (isLeap(year) && month == 2) 1 else 0) + monthDays[month - 1]) + getDaysUntil(month - 1, year)
        }

        fun getNumDays(year: Int, month: Int, day: Int): Int {
            return daysByYear(year - 1) + getDaysUntil(month - 1, year) + day
        }

        val firstDate = date1.split("-").map { it.toInt() }
        val secondDate = date2.split("-").map { it.toInt() }

        return Math.abs(getNumDays(secondDate[0], secondDate[1], secondDate[2]) - getNumDays(firstDate[0], firstDate[1], firstDate[2]))
    }
}

fun main() {
//     testReverseString2()
//    testisStrobogrammatic()
//    testStringCompression()
//    testCountAndSay()
//    testBinaryStringCount()
//    test2Sum()
//    testIsIsomorphic()
//    testMonotonic()
    testSearchInsert()
}

private fun testReverseString2() {
    println(MultipleProblemLCEasy().reverseStr("abcd", 4))
}

private fun testisStrobogrammatic() {
    println(MultipleProblemLCEasy().isStrobogrammatic("123"))
}

private fun testStringCompression() {
    println(MultipleProblemLCEasy().compress(charArrayOf('a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b')))
}

private fun testCountAndSay() {
    println(MultipleProblemLCEasy().countAndSay(10))
}

private fun testBinaryStringCount() {
    println(MultipleProblemLCEasy().countBinarySubString("1010"))
}

private fun test2Sum() {
    println(MultipleProblemLCEasy().twoSum(intArrayOf(0, 0, 3, 4), 0))
    println(MultipleProblemLCEasy().twoSumAnother(intArrayOf(2, 7, 11, 15), 9))
    println(MultipleProblemLCEasy().twoSumAnother1(intArrayOf(2, 7, 11, 15), 9))
}

private fun testIsIsomorphic() {
    println(MultipleProblemLCEasy().isIsomorphic("egg", "add"))
    println(MultipleProblemLCEasy().isIsomorphic("foo", "bar"))
    println(MultipleProblemLCEasy().isIsomorphic("paper", "title"))
    println(MultipleProblemLCEasy().isIsomorphic("ab", "aa"))
}

private fun testMonotonic() {
    println(MultipleProblemLCEasy().isMonotonic(intArrayOf(1, 2, 2, 3)))
    println(MultipleProblemLCEasy().checkValidString("(**)"))
}

private fun testSearchInsert() {
    println(MultipleProblemLCEasy().searchInsert(intArrayOf(1, 3, 5, 6), 5))
    println(MultipleProblemLCEasy().searchInsert(intArrayOf(1, 3, 5, 6), 2))
    println(MultipleProblemLCEasy().searchInsert(intArrayOf(1, 3, 5, 6), 7))
    println(MultipleProblemLCEasy().searchInsert(intArrayOf(1, 3, 5, 6), 0))
    println(MultipleProblemLCEasy().searchInsert(intArrayOf(1), 1))
}
