package problem;

import java.util.*;

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