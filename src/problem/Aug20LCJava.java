package problem;

import kotlin.Pair;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
public class Aug20LCJava {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int i = 0;
        int last = citations.length - 1;
        while (i < citations.length && citations[last - i] > i) {
            i++;
        }
        return i;
    }

    public int hIndexSecond(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // counting papers for each citation number
        for (int c : citations)
            papers[Math.min(n, c)]++;
        // finding the h-index
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }

    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) return Collections.singletonList(1);
        if (rowIndex == 1) return new ArrayList<>(Arrays.asList(1, 1));

        List<Integer> last = getRow(rowIndex - 1);
        int prev = last.get(0);
        for (int i = 1; i < last.size(); i++) {
            int next = prev + last.get(i);
            prev = last.get(i);
            last.set(i, next);
        }
        last.set(0, 1);
        last.add(1);
        return last;
    }

    public int longestPalindrome(String s) {
        int[] count = new int[128];
        for (char c : s.toCharArray())
            count[c]++;

        int ans = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if (ans % 2 == 0 && v % 2 == 1)
                ans++;
        }
        return ans;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[1]));
        int end = intervals[0][1];

        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                count++;
            } else {
                end = intervals[i][1];
            }
        }

        return count;
    }

    public int[] findPermutation(String s) {
        int[] res = new int[s.length() + 1];
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == 'I') {
                stack.push(i);
                while (!stack.isEmpty())
                    res[j++] = stack.pop();
            } else
                stack.push(i);
        }
        stack.push(s.length() + 1);
        while (!stack.isEmpty())
            res[j++] = stack.pop();
        return res;
    }

    /*public int rand10() {
        int row, col, idx;
        do {
            row = rand7();
            col = rand7();
            idx = col + (row - 1) * 7;
        } while (idx > 40);
        return 1 + (idx - 1) % 10;
    }*/

    public int[] findRightInterval(int[][] intervals) {
        int[][] endIntervals = Arrays.copyOf(intervals, intervals.length);
        HashMap<int[], Integer> hash = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            hash.put(intervals[i], i);
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        Arrays.sort(endIntervals, (a, b) -> a[1] - b[1]);
        int j = 0;
        int[] res = new int[intervals.length];
        for (int i = 0; i < endIntervals.length; i++) {
            while (j < intervals.length && intervals[j][0] < endIntervals[i][1]) {
                j++;
            }
            res[hash.get(endIntervals[i])] = j == intervals.length ? -1 : hash.get(intervals[j]);
        }
        return res;
    }

    public int maxProfit3(int[] prices) {
        int t1Cost = Integer.MAX_VALUE,
                t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0,
                t2Profit = 0;

        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);
            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }

        return t2Profit;
    }

    public int[] distributeCandies(int candies, int num_people) {
        int n = 1;
        int[] result = new int[num_people];
        while (candies > 0) {
            int givenToCurrent = Math.min(candies, n);
            result[(n - 1) % num_people] += givenToCurrent;
            candies -= givenToCurrent;
            n++;
        }

        return result;
    }

    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1)
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> results = new ArrayList<Integer>();
        for (int num = 1; num < 10; ++num)
            this.DFS(N - 1, num, K, results);


        return results.stream().mapToInt(i -> i).toArray();
    }

    protected void DFS(int N, int num, int K, List<Integer> results) {
        if (N == 0) {
            results.add(num);
            return;
        }
        List<Integer> nextDigits = new ArrayList<>();

        int tailDigit = num % 10;
        nextDigits.add(tailDigit + K);
        if (K != 0)
            nextDigits.add(tailDigit - K);
        for (Integer nextDigit : nextDigits) {
            if (0 <= nextDigit && nextDigit < 10) {
                int newNum = num * 10 + nextDigit;
                this.DFS(N - 1, newNum, K, results);
            }
        }
    }

    public String toGoatLatin(String S) {
        Set<Character> vowels =
                new HashSet<>() {
                    {
                        add('a');
                        add('e');
                        add('i');
                        add('o');
                        add('u');
                        add('A');
                        add('E');
                        add('I');
                        add('O');
                        add('U');
                    }
                };
        String suff = "";
        StringBuilder sb = new StringBuilder();

        for (String tok : S.split(" ")) {
            suff += 'a';

            if (!vowels.contains(tok.charAt(0))) {
                tok = tok.substring(1) + tok.charAt(0);
            }

            sb.append(tok).append("ma").append(suff).append(' ');
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null) return;

        // find the middle of linked list [Problem 876]
        // in 1->2->3->4->5->6 find 4
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the second part of the list [Problem 206]
        // convert 1->2->3->4->5->6 into 1->2->3->4 and 6->5->4
        // reverse the second half in-place
        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // merge two sorted linked lists [Problem 21]
        // merge 1->2->3->4 and 6->5->4 into 1->6->2->5->3->4
        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }

    public int[] sortArrayByParity(int[] A) {
        if (A.length <= 1) return A;
        int[] result = new int[A.length];
        int idx = -1;
        for (int a : A) {
            if (a % 2 == 0) {
                result[++idx] = a;
            }
        }
        for (int a : A) {
            if (a % 2 != 0) {
                result[++idx] = a;
            }
        }
        return result;
    }

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return mazeHelper(maze, start, destination, visited);
    }

    public boolean mazeHelper(int[][] maze, int[] start, int[] destination, boolean[][] visited) {
        if (visited[start[0]][start[1]])
            return false;
        if (start[0] == destination[0] && start[1] == destination[1])
            return true;
        visited[start[0]][start[1]] = true;
        int r = start[1] + 1, l = start[1] - 1, u = start[0] - 1, d = start[0] + 1;
        while (r < maze[0].length && maze[start[0]][r] == 0) // right
            r++;
        if (mazeHelper(maze, new int[]{start[0], r - 1}, destination, visited))
            return true;
        while (l >= 0 && maze[start[0]][l] == 0) //left
            l--;
        if (mazeHelper(maze, new int[]{start[0], l + 1}, destination, visited))
            return true;
        while (u >= 0 && maze[u][start[1]] == 0) //up
            u--;
        if (mazeHelper(maze, new int[]{u + 1, start[1]}, destination, visited))
            return true;
        while (d < maze.length && maze[d][start[1]] == 0) //down
            d++;
        if (mazeHelper(maze, new int[]{d - 1, start[1]}, destination, visited))
            return true;
        return false;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int successor(TreeNode root) {
        root = root.right;
        while (root.left != null) root = root.left;
        return root.val;
    }

    public int predecessor(TreeNode root) {
        root = root.left;
        while (root.right != null) root = root.right;
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key > root.val) root.right = deleteNode(root.right, key);
        else if (key < root.val) root.left = deleteNode(root.left, key);
        else {
            if (root.left == null && root.right == null) root = null;
            else if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            }
            else {
                root.val = predecessor(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    public int calculateSum(TreeNode node, boolean isLeft) {
        if (node == null) return 0;
        if (node.left == null && node.right == null && isLeft) return node.val;
        if (node.left == null && node.right == null) return 0;
        return calculateSum(node.left, true) + calculateSum(node.right, false);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return calculateSum(root, false);
    }

    public int mincostTickets(int[] days, int[] costs) {
        if (days.length == 0) return 0;

        int n = days[days.length - 1];

        int[] dp = new int[n + 1];
        boolean[] travel = new boolean[n + 1];

        for (int day : days) {
            travel[day] = true;
        }

        for (int i = 1; i < dp.length; i++) {
            if (!travel[i]) {
                dp[i] = dp[i - 1];
                continue;
            }

            int oneDayCost = dp[i - 1] + costs[0];
            int sevenDayCost = dp[Math.max(i - 7, 0)] + costs[1];
            int thirtyDayCost = dp[Math.max(i - 30, 0)] + costs[2];

            dp[i] = Math.min(oneDayCost, Math.min(sevenDayCost, thirtyDayCost));
        }

        return dp[n];

    }

    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; ++i) {
            // Find the successor of current element
            Integer s = set.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) return true;

            // Find the predecessor of current element
            Integer g = set.floor(nums[i]);
            if (g != null && nums[i] <= g + t) return true;

            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public boolean repeatedSubstringPattern(String s) {
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }

    public List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        for (int i = 0; i < S.length(); ++i)
            last[S.charAt(i) - 'a'] = i;

        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList();
        for (int i = 0; i < S.length(); ++i) {
            j = Math.max(j, last[S.charAt(i) - 'a']);
            if (i == j) {
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

    public List<Integer> inorder(TreeNode root, List<Integer> arr) {
        if (root == null) return arr;
        inorder(root.left, arr);
        arr.add(root.val);
        inorder(root.right, arr);
        return arr;
    }

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> output = new ArrayList<>();
        Stream.of(inorder(root1, new ArrayList<>()), inorder(root2, new ArrayList<>())).forEach(output::addAll);
        Collections.sort(output);
        return output;
    }

    public static void main(String[] args) {
        Aug20LCJava obj = new Aug20LCJava();
    }
}

class CombinationIterator {
    public Deque<String> combinations = new ArrayDeque<>();

    public CombinationIterator(String characters, int combinationLength) {
        int n = characters.length(), k = combinationLength;

        // init the first combination
        int[] nums = new int[k + 1];
        for (int i = 0; i < k; ++i) {
            nums[i] = i;
        }
        nums[k] = n;

        int j = 0;
        while (j < k) {
            // add current combination
            StringBuilder sb = new StringBuilder();
            for (int i = k - 1; i > -1; i--) {
                sb.append(characters.charAt(n - 1 - nums[i]));
            }
            combinations.push(sb.toString());
            // Generate next combination.
            // Find the first j such that nums[j] + 1 != nums[j + 1].
            // Increase nums[j] by one.
            j = 0;
            while ((j < k) && (nums[j + 1] == nums[j] + 1)) {
                nums[j] = j;
                j++;
            }
            nums[j]++;
        }
    }

    public String next() {
        return combinations.pop();
    }

    public boolean hasNext() {
        return (!combinations.isEmpty());
    }
}

class RandomPick {

    int[][] rects;
    List<Integer> psum = new ArrayList<>();
    int tot = 0;
    Random rand = new Random();

    public RandomPick(int[][] rects) {
        this.rects = rects;
        for (int[] x : rects) {
            tot += (x[2] - x[0] + 1) * (x[3] - x[1] + 1);
            psum.add(tot);
        }
    }

    public int[] pick() {
        int targ = rand.nextInt(tot);

        int lo = 0;
        int hi = rects.length - 1;
        while (lo != hi) {
            int mid = (lo + hi) / 2;
            if (targ >= psum.get(mid)) lo = mid + 1;
            else hi = mid;
        }

        int[] x = rects[lo];
        int width = x[2] - x[0] + 1;
        int height = x[3] - x[1] + 1;
        int base = psum.get(lo) - width * height;
        return new int[]{x[0] + (targ - base) % width, x[1] + (targ - base) / width};
    }
}


class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean word = false;
}

class StreamChecker {
    TrieNode trie = new TrieNode();
    Deque<Character> stream = new ArrayDeque<>();

    public StreamChecker(String[] words) {
        for (String word : words) {
            TrieNode node = trie;
            String reversedWord = new StringBuilder(word).reverse().toString();
            for (char ch : reversedWord.toCharArray()) {
                if (!node.children.containsKey(ch)) {
                    node.children.put(ch, new TrieNode());
                }
                node = node.children.get(ch);
            }
            node.word = true;
        }
    }

    public boolean query(char letter) {
        stream.addFirst(letter);

        TrieNode node = trie;
        for (char ch : stream) {
            if (node.word) {
                return true;
            }
            if (!node.children.containsKey(ch)) {
                return false;
            }
            node = node.children.get(ch);
        }
        return node.word;
    }

}

class RoomCleaner {
    // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    HashSet visited = new HashSet();
    Robot robot;

    public void goBack() {
        /*robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();*/
    }

    public void backtrack(int row, int col, int d) {
        visited.add(new Pair(row, col));
        // robot.clean();
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
        for (int i = 0; i < 4; ++i) {
            int newD = (d + i) % 4;
            int newRow = row + directions[newD][0];
            int newCol = col + directions[newD][1];

            if (!visited.contains(new Pair(newRow, newCol)) /*&& robot.move()*/) {
                backtrack(newRow, newCol, newD);
                goBack();
            }
            // turn the robot following chosen direction : clockwise
            // robot.turnRight();
        }
    }

    public void cleanRoom(Robot robot) {
        this.robot = robot;
        backtrack(0, 0, 0);
    }
}

class PancakeSort {
    /*
    Iterate over array and find the max num and remember its index
    Now filp [0, index]: this make sure the max goes to the very beginning
    Now flip [0, end]: this make sue the max goes to the end. Initially
    end is the length - 1.

    Now update end by doing end--, since we dont care the end anymore
    as it has the max value in sorted postion already.

    We keep shrinking the range by end-- each time we flip and when end == 0
    we are done.

    */
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> res = new ArrayList<>();
        int end = A.length - 1;

        while (end >= 0) {
            // find max from 0 to end
            int maxIndex = 0;
            int max = 0;

            for (int i = 0; i <= end; i++) {
                if (max < A[i]) {
                    max = A[i];
                    maxIndex = i;
                }
            }

            // first flip to make max at the front
            flip(A, maxIndex);
            res.add(maxIndex + 1);

            // second flip to make max at index end
            flip(A, end);
            res.add(end + 1);

            end--;
        }

        return res;
    }

    public void flip(int[] A, int k) {
        int l = 0, r = k;
        while (l < r) {
            int temp = A[l];
            A[l] = A[r];
            A[r] = temp;
            l++;
            r--;
        }
    }
}

class LargestComponentByFactor {
    public int largestComponentSize(int[] A) {

        int maxValue = Arrays.stream(A).reduce(A[0], (x, y) -> x > y ? x : y);
        DisjointSetUnion dsu = new DisjointSetUnion(maxValue);
        // attribute each element to all the groups that lead by its factors.
        for (int num : A) {
            for (int factor = 2; factor < (int) (Math.sqrt(num)) + 1; ++factor)
                if (num % factor == 0) {
                    dsu.union(num, factor);
                    dsu.union(num, num / factor);
                }
        }

        // count the size of group one by one
        int maxGroupSize = 0;
        HashMap<Integer, Integer> groupCount = new HashMap<>();
        for (int num : A) {
            Integer groupId = dsu.find(num);
            Integer count = groupCount.getOrDefault(groupId, 0);
            groupCount.put(groupId, count + 1);
            maxGroupSize = Math.max(maxGroupSize, count + 1);
        }

        return maxGroupSize;
    }
}

class DisjointSetUnion {
    private int[] parent;
    private int[] size;

    public DisjointSetUnion(int size) {
        this.parent = new int[size + 1];
        this.size = new int[size + 1];
        for (int i = 0; i < size + 1; ++i) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    /**
     * return the component id that the element x belongs to.
     */
    public int find(int x) {
        if (this.parent[x] != x)
            this.parent[x] = this.find(this.parent[x]);
        return this.parent[x];
    }

    /**
     * merge the two components that x, y belongs to respectively,
     * and return the merged component id as the result.
     */
    public int union(int x, int y) {
        int px = this.find(x);
        int py = this.find(y);

        // the two nodes share the same group
        if (px == py)
            return px;

        // otherwise, connect the two sets (components)
        if (this.size[px] > this.size[py]) {
            // add the node to the union with less members.
            // keeping px as the index of the smaller component
            int temp = px;
            px = py;
            py = temp;
        }

        // add the smaller component to the larger one
        this.parent[px] = py;
        this.size[py] += this.size[px];
        return py;
    }
}

class ImageOverlap {
    protected int shiftAndCount(int xShift, int yShift, int[][] M, int[][] R) {
        int count = 0;
        int rRow = 0;
        // count the cells of ones in the overlapping zone.
        for (int mRow = yShift; mRow < M.length; ++mRow) {
            int rCol = 0;
            for (int mCol = xShift; mCol < M.length; ++mCol) {
                if (M[mRow][mCol] == 1 && M[mRow][mCol] == R[rRow][rCol])
                    count += 1;
                rCol += 1;
            }
            rRow += 1;
        }
        return count;
    }

    public int largestOverlap(int[][] A, int[][] B) {
        int maxOverlaps = 0;

        for (int yShift = 0; yShift < A.length; ++yShift)
            for (int xShift = 0; xShift < A.length; ++xShift) {
                // move one of the matrice up and left and vice versa.
                // (equivalent to move the other matrix down and right)
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, A, B));
                maxOverlaps = Math.max(maxOverlaps, shiftAndCount(xShift, yShift, B, A));
            }

        return maxOverlaps;
    }
}