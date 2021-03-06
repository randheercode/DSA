package problem.old

import printArray
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

    fun getDecimalValue(head: ListNode?): Int {
        var head = head
        val builder = StringBuilder()
        while (head != null) {
            builder.append(head.`val`)
            head = head.next
        }
        return if (builder.isEmpty()) return 0 else builder.toString().toInt(2)
    }

    fun toHex(num: Int): String {
        var num = num
        if (num == 0) return "0"
        val result = StringBuilder()
        while (num != 0) {
            val i = num and 15
            if (i > 9) result.append(('a'.toInt() + i - 10).toChar()) else result.append(i)
            num = num ushr 4
        }
        return result.reverse().toString()
    }

    fun getSum(a: Int, b: Int): Int {
        if (a == 0) return b
        if (b == 0) return a
        var a = a
        var b = b
        while (b != 0) {
            val carry: Int = a and b shl 1
            a = a xor b
            b = carry
        }
        return a
    }

    fun mergeTrees(t1: TreeNode?, t2: TreeNode?): TreeNode? {
        fun mergeNode(first: TreeNode?, second: TreeNode?): TreeNode? {
            if (first == null && second == null) return null
            val treeNode = TreeNode((first?.`val` ?: 0).plus(second?.`val` ?: 0))
            treeNode.left = mergeNode(first?.left, second?.left)
            treeNode.right = mergeNode(first?.right, second?.right)
            return treeNode
        }
        return mergeTrees(t1, t2)
    }

    fun countNodes(root: TreeNode?): Int {
        return if (root == null) 0 else 1 + (countNodes(root.left) + countNodes(root.right))
    }

    fun numTrees(n: Int): Int {
        val G = IntArray(n + 1)
        G[1] = 1
        G[0] = G[1]
        for (i in 2..n) {
            for (j in 1..i) {
                G[i] += G[j - 1] * G[i - j]
            }
        }
        return G[n]
    }

    fun fib(N: Int): Int {
        return if (N < 2) {
            N;
        } else {
            fib(N - 1) + fib(N - 2);
        }
    }

    fun climbStairs(n: Int): Int {
        fun climbStairs(i: Int, n: Int, memo: IntArray): Int {
            if (i > n) {
                return 0
            }
            if (i == n) {
                return 1
            }
            if (memo[i] > 0) {
                return memo[i]
            }
            memo[i] = climbStairs(i + 1, n, memo) + climbStairs(i + 2, n, memo)
            return memo[i]
        }

        val memo = IntArray(n + 1)
        return climbStairs(0, n, memo)
    }

    fun maxDepth(root: TreeNode?): Int {
        return if (root == null) 0
        else 1 + maxOf(maxDepth(root.left), maxDepth(root.right))
    }

    fun myPow(x: Double, n: Int): Double {
        var x = x
        var N = n.toLong()
        if (N < 0) {
            x = 1 / x
            N = -N
        }
        fun fastPow(x: Double, n: Long): Double {
            if (n == 0L) {
                return 1.0
            }
            val half = fastPow(x, n / 2)
            return if (n % 2 == 0L) {
                half * half
            } else {
                half * half * x
            }
        }
        return fastPow(x, N)
    }

    fun kthGrammar(N: Int, K: Int): Int {
        return if (N == 1) 0 else K.inv() and 1 xor kthGrammar(N - 1, (K + 1) / 2)
    }

    fun generate(start: Int, end: Int): List<TreeNode?> {
        val ans: MutableList<TreeNode?> = ArrayList()
        if (start > end) {
            ans.add(null)
            return ans
        }
        for (i in start..end) {
            val left = generate(start, i - 1)
            val right = generate(i + 1, end)
            for (l in left) {
                for (r in right) {
                    val root = TreeNode(i)
                    root.left = l
                    root.right = r
                    ans.add(root)
                }
            }
        }
        return ans
    }


    fun generateTrees(n: Int): List<TreeNode?>? {
        return if (n == 0) emptyList<TreeNode>() else generate(1, n)
    }

    fun checkPossibility(nums: IntArray): Boolean {
        val len = nums.size
        if (len <= 2) return true
        var used = false
        for (i in 1 until len) {
            if (nums[i] < nums[i - 1]) {
                if (used) return false
                if (i != 1 && nums[i - 2] > nums[i]) nums[i] = nums[i - 1]
                used = true
            }
        }
        return true
    }

    fun findDuplicate(nums: IntArray): Int {
        if (nums.isEmpty()) return -1
        var tortoise = nums[0]
        var hare = nums[0]
        do {
            tortoise = nums[tortoise]
            hare = nums[nums[hare]]
        } while (tortoise != hare)
        tortoise = nums[0]
        while (tortoise != hare) {
            tortoise = nums[tortoise]
            hare = nums[hare]
        }
        return hare
    }

    fun sumNumbers(root: TreeNode?): Int {
        val path = mutableListOf<String>()

        fun constructPath(root: TreeNode?, currentPath: String) {
            if (root?.left == null && root?.right == null) {
                path.add(currentPath)
                return
            }
            if (root.left != null) constructPath(root.left, "$currentPath${root.left?.`val`}")
            if (root.right != null) constructPath(root.right, "$currentPath${root.right?.`val`}")
        }

        if (root != null) constructPath(root, root.`val`.toString()) else return 0
        return path.map { it.toInt() }.sum()
    }

    fun uniquePaths(m: Int, n: Int): Int {
        val d = Array(m) { IntArray(n) { 1 } }
        for (col in 1 until m) {
            for (row in 1 until n) {
                d[col][row] = d[col - 1][row] + d[col][row - 1]
            }
        }
        return d[m - 1][n - 1]
    }

    fun arrangeCoins(n: Int): Int {
        return (Math.sqrt(2 * n.toLong() + 0.25) - 0.5).toInt()
    }

    fun arrangeCoins1(n: Int): Int {
        var left: Long = 0
        var right = n.toLong()
        var k: Long
        var curr: Long
        while (left <= right) {
            k = left + (right - left) / 2
            curr = k * (k + 1) / 2
            if (curr == n.toLong()) return k.toInt()
            if (n < curr) {
                right = k - 1
            } else {
                left = k + 1
            }
        }
        return right.toInt()
    }

    fun levelOrderBottom(root: TreeNode?): List<List<Int>> {
        fun height(root: TreeNode?): Int {
            return if (root == null) 0 else (1 + maxOf(height(root.left), height(root.right)))
        }

        val height = height(root)
        val result = mutableListOf<MutableList<Int>>()
        repeat(height) { result.add(mutableListOf()) }

        fun traverse(root: TreeNode?, label: Int) {
            if (root == null) return
            result[height - label].add(root.`val`)
            traverse(root.left, label + 1)
            traverse(root.right, label + 1)
        }
        traverse(root, 1)
        return result

    }

    fun prisonAfterNDays(cells: IntArray, N: Int): IntArray? {
        fun nextDay(stateBitmap: Int): Int {
            var stateBitmap = stateBitmap
            stateBitmap = (stateBitmap shl 1).inv() xor (stateBitmap shr 1)
            // set the head and tail to zero
            stateBitmap = stateBitmap and 0x7e
            return stateBitmap
        }

        var N = N
        val seen = HashMap<Int, Int?>()
        var isFastForwarded = false

        var stateBitmap = 0x0
        for (cell in cells) {
            stateBitmap = stateBitmap shl 1
            stateBitmap = stateBitmap or cell
        }

        // step 2). run the simulation with hashmap
        while (N > 0) {
            if (!isFastForwarded) {
                if (seen.containsKey(stateBitmap)) {
                    // the length of the cycle is seen[state_key] - N
                    N %= seen[stateBitmap]!! - N
                    isFastForwarded = true
                } else seen[stateBitmap] = N
            }
            // check if there is still some steps remained,
            // with or without the fast forwarding.
            if (N > 0) {
                N -= 1
                stateBitmap = nextDay(stateBitmap)
            }
        }

        // step 3). convert the bitmap back to the state cells
        val ret = IntArray(cells.size)
        for (i in cells.indices.reversed()) {
            ret[i] = stateBitmap and 0x1
            stateBitmap = stateBitmap shr 1
        }
        return ret
    }

    fun nthUglyNumber(n: Int): Int {
        val ugly = IntArray(n)
        ugly[0] = 1
        var ind2 = 0
        var ind3 = 0
        var ind5 = 0
        var lastValue2 = 2
        var lastValue3 = 3
        var lastValue5 = 5
        for (i in 1 until n) {
            val min = minOf(lastValue2, lastValue3, lastValue5)
            ugly[i] = min
            if (lastValue2 == min) lastValue2 = 2 * ugly[++ind2]
            if (lastValue3 == min) lastValue3 = 3 * ugly[++ind3]
            if (lastValue5 == min) lastValue5 = 5 * ugly[++ind5]
        }
        return ugly[n - 1]
    }

    fun hammingDistance(x: Int, y: Int): Int {
        var calc = x xor y
        var diff = 0
        while (calc > 0) {
            diff += calc and 1
            calc = calc shr 1
        }
        return diff
    }

    fun plusOne(digits: IntArray): IntArray {
        if (digits.isEmpty()) return intArrayOf(1)
        var rem = 1
        var i = digits.lastIndex

        while (rem > 0 && i >= 0) {
            val sum = digits[i].plus(rem)
            digits[i] = sum % 10
            rem = sum / 10
            i--
        }

        return if (rem == 0) {
            digits
        } else {
            val newDigits = IntArray(digits.size.plus(1))
            newDigits[0] = rem
            digits.forEachIndexed { index, num -> newDigits[index.plus(1)] = num }
            newDigits
        }
    }

    fun islandPerimeter(grid: Array<IntArray>): Int {
        if (grid.isEmpty()) return 0
        val row = grid.size
        val col = grid[0].size
        fun calculateWeight(r: Int, c: Int): Int {
            var count = 0
            if (r == 0 || (r > 0 && grid[r - 1][c] != 1)) count++
            if (r == row - 1 || (r + 1 < row && grid[r + 1][c] != 1)) count++
            if (c == 0 || (c > 0 && grid[r][c - 1] != 1)) count++
            if (c == col - 1 || (c + 1 < col && grid[r][c + 1] != 1)) count++
            return count
        }

        val weight = Array(row) { IntArray(col) { 0 } }
        for (i in 0 until row) {
            for (j in 0 until col) {
                if (grid[i][j] == 1)
                    weight[i][j] = calculateWeight(i, j)
            }
        }

        printArray(weight)

        return weight.map { it.sum() }.sum()

    }

    fun widthOfBinaryTree(root: TreeNode?): Int {
        var maxWidth = 0
        val firstColIndexTable = mutableMapOf<Int, Int>()
        fun dfs(node: TreeNode?, depth: Int, colIndex: Int) {
            if (node == null) return
            if (!firstColIndexTable.containsKey(depth)) {
                firstColIndexTable[depth] = colIndex
            }
            val firstColIndex = firstColIndexTable!![depth]
            maxWidth = maxOf(maxWidth, colIndex - firstColIndex!! + 1)

            dfs(node.left, depth + 1, 2 * colIndex)
            dfs(node.right, depth + 1, 2 * colIndex + 1)
        }

        dfs(root, 0, 0)
        return maxWidth
    }

    fun subsets(nums: IntArray): List<List<Int>> {
        val resultSize = Math.pow(2.0, nums.size.toDouble()).toLong()
        val resultSet = mutableListOf<List<Int>>()
        for (i in 0 until resultSize) {
            resultSet.add(nums.filterIndexed { index, _ -> (i and (1 shl index).toLong()) > 0 }.toList())
        }
        return resultSet
    }

    fun subsets2(nums: IntArray): List<List<Int>> {
        fun helper(nums: IntArray, i: Int, subset: MutableList<Int>, res: MutableList<List<Int>>) {
            if (i == nums.size) {
                res.add(subset)
            } else {
                val newSubset = mutableListOf<Int>()
                newSubset.addAll(subset)
                helper(nums, i + 1, newSubset, res)
                subset.add(nums[i])
                helper(nums, i + 1, subset, res)
            }
        }

        val res = mutableListOf<List<Int>>()
        helper(nums, 0, mutableListOf(), res)
        return res
    }

    fun twoSumMy(nums: IntArray, target: Int): IntArray {
        val map = nums.mapIndexed { index, i -> i to index }.toMap()
        var i = 0
        while (i < nums.size) {
            val req = target - nums[i]
            if (map.containsKey(req) && map[req] != i) {
                return intArrayOf(i, map[req]!!)
            }
            i++
        }
        return IntArray(2)
    }

    fun twoSum2(numbers: IntArray, target: Int): IntArray {
        var l = 0
        var e = numbers.size - 1
        while (l < e) {
            if (numbers[l] + numbers[e] == target) return intArrayOf(l.plus(1), e.plus(1))
            if (numbers[l] + numbers[e] > target) e--
            else l++
        }
        return intArrayOf()
    }

    fun lengthOfLongestSubstring(s: String): Int {
        val n = s.length
        val set = mutableSetOf<Char>()
        var maxLen = 0
        var i = 0
        var j = 0
        while (i < n && j < n) {
            if (!set.contains(s[j])) {
                set.add(s[j++])
                maxLen = maxOf(maxLen, j - i)
            } else {
                set.remove(s[i++])
            }
        }
        return maxLen
    }

    fun reverseBits(n: Int): Int {
        var n = n
        var ans = 0
        for (i in 0..31) {
            ans = ans shl 1
            ans = ans or (n and 1)
            n = n shr 1
        }
        return ans
    }

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val total = nums1.toMutableList()
        total.addAll(nums2.toList())
        total.sort()
        return if (total.size.rem(2) == 0) {
            val mid = total.size.div(2).minus(1)
            total[mid].plus(total[mid + 1]).div(2.0)
        } else {
            total[total.size.div(2)].toDouble()
        }
    }

    fun reverse(x: Int): Int {
        val isNeg = x < 0
        var x = Math.abs(x)
        var ans: Long = 0
        while (x > 0) {
            val temp = x.rem(10)
            ans = ans.times(10).plus(temp)
            x = x.div(10)
        }
        if (ans > Int.MAX_VALUE) return 0
        return if (isNeg) ans.toInt().times(-1) else ans.toInt()
    }

    fun isPalindrome(x: Int): Boolean {
        if (x < 0) return false
        fun reverse(x: Int): Int {
            val isNeg = x < 0
            var x = Math.abs(x)
            var ans: Long = 0
            while (x > 0) {
                val temp = x.rem(10)
                ans = ans.times(10).plus(temp)
                x = x.div(10)
            }
            if (ans > Int.MAX_VALUE) return 0
            return if (isNeg) ans.toInt().times(-1) else ans.toInt()
        }
        return x == reverse(x)
    }

    fun isPalindrome(head: ListNode?): Boolean {
        var root = head
        val strB = StringBuilder()
        val strRevB = StringBuilder()
        while (root != null) {
            strB.append(root.`val`)
            strRevB.insert(0, root.`val`.toString())
            root = root.next
        }
        return strB.toString() == strRevB.toString()
    }

    fun reverseList(head: ListNode?): ListNode? {
        val dummyNode = ListNode(0)
        var current = head
        while (current != null) {
            val next = current.next
            current.next = dummyNode.next
            dummyNode.next = current
            current = next
        }
        return dummyNode.next
    }

    fun reverseBetween(head: ListNode?, m: Int, n: Int): ListNode? {
        if (head == null) return null
        var pos = 1
        val dummyNode = ListNode(0)
        var ansNode: ListNode? = dummyNode
        var currentNode = head

        fun appendNormal(range: IntRange) {
            while (currentNode != null && pos in range) {
                ansNode?.next = currentNode
                currentNode = currentNode?.next
                ansNode = ansNode?.next
                ansNode?.next = null
                pos++
            }
        }

        fun appendReverse(range: IntRange) {
            while (currentNode != null && pos in range) {
                val next = currentNode?.next
                currentNode?.next = ansNode?.next
                ansNode?.next = currentNode
                currentNode = next
                pos++
            }
            while (ansNode?.next != null) ansNode = ansNode?.next
        }

        appendNormal(0 until m)
        appendReverse(m..n)
        appendNormal(n.plus(1)..Int.MAX_VALUE)

        return dummyNode.next
    }

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) return true
        return p?.`val` == q?.`val` && isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
    }


    fun angleClock(hour: Int, minutes: Int): Double {
        val minAngleFrom12 = minutes.times(6)
        val hourAngleFrom12 = (if (hour == 12) 0 else hour.times(30)).plus(minutes.div(2.0))
        val angle = Math.abs(minAngleFrom12.minus(hourAngleFrom12))
        return minOf(angle, 360.minus(angle))
    }

    fun reverseWords(s: String): String {
        return s.split(" ").reversed().filter { it.isNotEmpty() }.joinToString(" ")
    }

    fun myPow1(x: Double, n: Int): Double {
        var x = x
        var N = n.toLong()
        if (N < 0) {
            x = 1 / x
            N = -N
        }
        fun fastPow(x: Double, n: Long): Double {
            if (n == 0L) {
                return 1.0
            }
            val half = fastPow(x, n / 2)
            return if (n % 2 == 0L) {
                half * half
            } else {
                half * half * x
            }
        }
        return fastPow(x, N)
    }

    fun myAtoi(str: String): Int {
        var str = str
        str = str.trim { it <= ' ' }
        if (str.isEmpty()) return 0
        var neg = 0
        val ret: Int
        if (str[0] == '-') neg = 1
        if (str[0] == '+') neg = -1
        var num = ""
        for (i in str.indices) {
            if (neg != 0 && i == 0) continue
            num += if (str[i].toInt() in 48..57) {
                str[i]
            } else break
        }
        ret = try {
            num.toInt()
        } catch (e: Exception) {
            if (num.length < 10) return 0
            return if (neg == 1) Int.MIN_VALUE else Int.MAX_VALUE
        }
        return if (neg != 1) ret else ret * -1
    }

    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()
        var idx = 0
        while (idx < s.length) {
            if (stack.isEmpty() || s[idx] == '(' || s[idx] == '{' || s[idx] == '[') {
                stack.add(s[idx])
            } else {
                if ((stack.peek() == '(' && s[idx] != ')') || (stack.peek() == '{' && s[idx] != '}') || stack.peek() == '[' && s[idx] != ']') {
                    return false
                } else {
                    stack.pop()
                }
            }
            idx++
        }
        return true
    }

    fun strStr(haystack: String, needle: String): Int {
        fun matchFrom(index: Int): Boolean {
            var idx = 0
            while (idx < needle.length) {
                if (haystack[index + idx] != needle[idx]) return false
                idx++
            }
            return true
        }
        if (needle.isEmpty()) return 0
        if (haystack.length < needle.length) return -1
        if (haystack.length == needle.length && haystack == needle) return 0
        var idx = 0
        while (idx <= haystack.length.minus(needle.length)) {
            if (haystack[idx] == needle[0] && matchFrom(idx)) return idx
            idx++
        }
        return -1
    }

    fun topKFrequent(nums: IntArray, k: Int): IntArray? {
        // O(1) time
        if (k == nums.size) {
            return nums
        }

        // 1. build hash map : character and how often it appears
        // O(N) time
        val count = mutableMapOf<Int, Int>()
        for (n in nums) {
            count[n] = count.getOrDefault(n, 0)!! + 1
        }

        // init heap 'the less frequent element first'
        val heap: Queue<Int> = PriorityQueue(
                Comparator { n1: Int?, n2: Int? -> count[n1]!! - count[n2]!! })

        // 2. keep k top frequent elements in the heap
        // O(N log k) < O(N log N) time
        for (n in count.keys) {
            heap.add(n)
            if (heap.size > k) heap.poll()
        }

        // 3. build an output array
        // O(k log k) time
        val top = IntArray(k)
        for (i in k - 1 downTo 0) {
            top[i] = heap.poll()
        }
        return top
    }

    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        val dummyNode = ListNode(0)
        dummyNode.next = head
        var currentNode: ListNode? = dummyNode
        while (currentNode?.next != null) {
            if (currentNode.next?.`val` == `val`) {
                currentNode.next = currentNode.next?.next
            } else {
                currentNode = currentNode.next
            }
        }
        return dummyNode.next
    }


    internal class Solution {
        private lateinit var board: Array<CharArray>
        private var ROWS = 0
        private var COLS = 0
        fun exist(board: Array<CharArray>, word: String): Boolean {
            this.board = board
            ROWS = board.size
            COLS = board[0].size
            for (row in 0 until ROWS) for (col in 0 until COLS) if (backtrack(row, col, word, 0)) return true
            return false
        }

        private fun backtrack(row: Int, col: Int, word: String, index: Int): Boolean {
            if (index >= word.length) return true
            if (row < 0 || row == ROWS || col < 0 || col == COLS || board[row][col] != word[index]) return false
            var ret = false
            board[row][col] = '#'
            val rowOffsets = intArrayOf(0, 1, 0, -1)
            val colOffsets = intArrayOf(1, 0, -1, 0)
            for (d in 0..3) {
                ret = backtrack(row + rowOffsets[d], col + colOffsets[d], word, index + 1)
                if (ret) break
            }
            board[row][col] = word[index]
            return ret
        }
    }

    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        fun height(root: TreeNode?): Int {
            return if (root == null) 0 else (1 + maxOf(height(root.left), height(root.right)))
        }

        val height = height(root)
        val result = mutableListOf<MutableList<Int>>()
        repeat(height) { result.add(mutableListOf()) }

        fun traverse(root: TreeNode?, label: Int) {
            if (root == null) return
            result[label].add(root.`val`)
            traverse(root.left, label + 1)
            traverse(root.right, label + 1)
        }
        traverse(root, 0)

        for (i in result.indices) if (i.rem(2) != 0) result[i] = result[i].asReversed()
        return result
    }

    fun singleNumber(nums: IntArray): IntArray {
        val result = mutableMapOf<Int, Int>()
        for (num in nums) {
            if (result.containsKey(num)) {
                result.remove(num)
            } else {
                result[num] = num
            }
        }
        return result.keys.toIntArray()
    }

    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
        val target = graph.size - 1
        var results: MutableList<List<Int>> = mutableListOf()
        fun backtrack(currNode: Int, path: LinkedList<Int>) {
            if (currNode == target) {
                results.add(ArrayList(path))
                return
            }
            for (nextNode in graph[currNode]) {
                path.addLast(nextNode)
                backtrack(nextNode, path)
                path.removeLast()
            }
        }
        results = ArrayList()
        val path = LinkedList<Int>()
        path.addLast(0)
        backtrack(0, path)
        return results
    }

    fun findMin(nums: IntArray): Int {
        var low = 0
        var high = nums.size - 1
        while (low < high) {
            val pivot = low + (high - low) / 2
            if (nums[pivot] < nums[high]) high = pivot else if (nums[pivot] > nums[high]) low = pivot + 1 else high -= 1
        }
        return nums[low]
    }

    fun addDigits(num: Int): Int {
        fun digitSum(n: Int): Int {
            var number = n
            var sum = 0
            while (number > 0) {
                val r = number % 10
                sum += r
                number /= 10
            }
            return sum
        }

        var sum = digitSum(num)
        while (sum >= 10) {
            sum = digitSum(sum)
        }
        return sum
    }

    fun buildTreeInPost(inorder: IntArray, postorder: IntArray): TreeNode? {
        var postOrderPos = postorder.size - 1
        val indexMap = mutableMapOf<Int, Int>()
        fun helper(inOrderLeft: Int, inOrderRight: Int): TreeNode? {
            if (inOrderLeft > inOrderRight) return null
            val rootVal = postorder[postOrderPos]
            val root = TreeNode(rootVal)
            val index = indexMap[rootVal]!!
            postOrderPos--
            root.right = helper(index + 1, inOrderRight)
            root.left = helper(inOrderLeft, index - 1)
            return root
        }

        var idx = 0
        for (`val` in inorder) indexMap[`val`] = idx++
        return helper(0, inorder.size - 1)
    }

    fun leastInterval(tasks: CharArray, n: Int): Int {
        val frequencies = IntArray(26)
        for (t in tasks) {
            frequencies[t.toInt() - 'A'.toInt()]++
        }
        var fMax = 0
        for (f in frequencies) {
            fMax = maxOf(fMax, f)
        }
        var nMax = 0
        for (f in frequencies) {
            if (f == fMax) nMax++
        }

        return maxOf(tasks.size, (fMax - 1) * (n + 1) + nMax)
    }

    fun deleteNodes(head: ListNode?, m: Int, n: Int): ListNode? {
        val dummy = ListNode(0)
        dummy.next = head
        var current: ListNode? = dummy


        while (current != null) {
            var keep = 0
            while (keep < m && current != null) {
                current = current.next
                keep++
            }
            var delete = 0
            while (delete < n && current != null) {
                current.next = current.next?.next
                delete++
            }
        }
        return dummy.next
    }

    fun detectCapitalUse(word: String): Boolean {
        val caps = 65..90
        val capsCount = word.filter { it.toInt() in caps }.length
        return capsCount == 0 || (capsCount == 1 && word[0].toInt() in caps) || capsCount == word.length
    }

    fun isPrefixOfWord(sentence: String, searchWord: String): Int {
        val words = sentence.split(" ")
        var i = 0
        while (i < words.size) {
            if (words[i].startsWith(searchWord)) return i.plus(1)
            i++
        }
        return -1
    }

    fun reverseOnlyLetters(S: String): String {
        val s = StringBuilder(S)
        var left = 0
        var right = s.lastIndex
        while (left < right) {
            while (left < right && !s[left].isLetter()) left++
            while (left < right && !s[right].isLetter()) right--
            if (s[left].isLetter() && s[right].isLetter()) {
                val temp = s[left]
                s[left] = s[right]
                s[right] = temp
                left++
                right--
            }
        }
        return s.toString()
    }

    fun isPowerOfFour(num: Int): Boolean {
        return num > 0 && Math.log(num.toDouble()) / Math.log(2.0) % 2 == 0.0
    }

    fun isPathCrossing(path: String): Boolean {
        val visited = mutableSetOf<Pair<Int, Int>>()
        var currentX = 0
        var currentY = 0
        visited.add(Pair(0, 0))
        var i = 0
        while (i < path.length) {
            when (path[i]) {
                'N', 'S' -> {
                    currentY += if (path[i] == 'N') 1 else -1
                }
                'E', 'W' -> {
                    currentX += if (path[i] == 'E') 1 else -1
                }
            }
            val newPoint = Pair(currentX, currentY)
            if (visited.contains(newPoint)) return true
            else visited.add(newPoint)
            i++
        }
        return false
    }

    fun isPalindrome(s: String): Boolean {
        fun isLetterOrDigit(char: Char): Boolean {
            return char in 'A'..'Z' || char in 'a'..'z' || (char.toInt() - 48) in 0..9
        }

        val charDigits = StringBuilder()
        for (char in s) if (isLetterOrDigit(char)) charDigits.append(char)
        return charDigits.toString().toLowerCase() == charDigits.toString().reversed().toLowerCase()
    }

    fun findDuplicates(nums: IntArray): List<Int>? {
        val ans: MutableList<Int> = ArrayList()
        for (num in nums) {
            if (nums[Math.abs(num) - 1] < 0) {
                ans.add(Math.abs(num))
            }
            nums[Math.abs(num) - 1] *= -1
        }
        return ans
    }

    fun increasingBST(root: TreeNode?): TreeNode? {
        var cur: TreeNode? = null
        fun inorder(node: TreeNode?) {
            if (node == null) return
            inorder(node.left)
            node.left = null
            cur?.right = node
            cur = node
            inorder(node.right)
        }

        val ans = TreeNode(0)
        cur = ans
        inorder(root)
        return ans.right
    }

    fun matrixReshape(nums: Array<IntArray>, r: Int, c: Int): Array<IntArray> {
        val iMax = nums.size
        val jMax = nums[0].size
        if (iMax.times(jMax) != r.times(c)) return nums
        var i = 0
        var j = 0
        val result = Array(r) { IntArray(c) { 0 } }
        for (currI in 0 until r) {
            for (currJ in 0 until c) {
                result[currI]!![currJ] = nums[i][j]
                if (j + 1 == jMax) {
                    j = 0
                    i++
                } else {
                    j++
                }
            }
        }
        return result
    }

    fun distributeCandies(candies: IntArray): Int {
        val map = candies.groupBy { it }.map { it.key to it.value.size }.toMap()
        val maxNum = candies.size.div(2)
        return if (maxNum >= map.size) map.size
        else maxNum
    }

    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        for (i in nums.indices) {
            val key = Math.abs(nums[i]) - 1
            if (nums[key] > 0)
                nums[key] = nums[key] * -1
        }

        val notP = mutableListOf<Int>()
        for (i in nums.indices) {
            if (nums[i] > 0) notP.add(i + 1)
        }

        return notP
    }

    fun firstMissingPositive(nums: IntArray): Int {
        val n = nums.size
        var contains = 0
        for (i in 0 until n) if (nums[i] == 1) {
            contains++
            break
        }
        if (contains == 0) return 1
        if (n == 1) return 2
        for (i in 0 until n) if (nums[i] <= 0 || nums[i] > n) nums[i] = 1
        for (i in 0 until n) {
            val a = Math.abs(nums[i])
            if (a == n) nums[0] = -Math.abs(nums[0]) else nums[a] = -Math.abs(nums[a])
        }
        for (i in 1 until n) {
            if (nums[i] > 0) return i
        }
        return if (nums[0] > 0) n else n + 1
    }

    fun closestValue(root: TreeNode?, target: Double): Int {
        fun diff(node: TreeNode) = Math.abs(target.minus(node.`val`))
        var result: TreeNode? = null
        var diffrence = Double.MAX_VALUE

        fun closest(node: TreeNode?) {
            if (node == null) return
            val currDiff = diff(node)
            if (currDiff < diffrence) {
                diffrence = currDiff
                result = node
            }
            closest(node.left)
            closest(node.right)
        }
        closest(root)
        return result?.`val` ?: -1
    }

    fun pathSum(root: TreeNode?, sum: Int): Int {
        var count = 0
        val h = mutableMapOf<Int, Int>()

        fun countSum(node: TreeNode?, sumTill: Int) {
            var currSum = sumTill
            if (node == null) return

            currSum += node.`val`

            if (currSum == sum) count++

            count += h.getOrDefault(currSum - sum, 0)

            h[currSum] = h.getOrDefault(currSum, 0) + 1

            countSum(node.left, currSum)
            countSum(node.right, currSum)

            h[currSum] = h[currSum]!! - 1
        }

        countSum(root, 0)
        return count
    }


}

fun main() {
    println(MultipleProblemLCEasy().findDisappearedNumbers(intArrayOf(4, 3, 2, 7, 8, 2, 3, 1)))
}

