package problem.challenge;

import utils.TreeNode;

import java.util.*;

interface ArrayReader {
    int get(int index);
}

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date: 17/10/20
 * Time: 3:31 pm
 */
public class OctLCJava {

    public List<String> findRepeatedDnaSequences(String s) {
        int L = 10, n = s.length();
        HashSet<String> seen = new HashSet<>(), output = new HashSet<>();
        for (int start = 0; start < n - L + 1; ++start) {
            String tmp = s.substring(start, start + L);
            if (seen.contains(tmp)) output.add(tmp);
            seen.add(tmp);
        }
        return new ArrayList<>(output);
    }

    public String longestDupSubstring(String S) {
        int len = S.length();
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = (int) S.charAt(i) - (int) 'a';
        }
        int a = 26; // Base for rolling hash
        long modulus = (long) Math.pow(2, 32); // modulus to handled overflow

        // Binary search for max Length duplicate
        int left = 1, right = len;
        int L;
        while (left <= right) {
            L = left + (right - left) / 2;
            if (robinKarpRollingHash(L, a, modulus, len, nums) != -1) left = L + 1;
            else right = L - 1;
        }

        int start = robinKarpRollingHash(left - 1, a, modulus, len, nums);
        return S.substring(start, start + left - 1);
    }

    private int robinKarpRollingHash(int L, int a, long modulus, int n, int[] nums) {
        // compute the hash of string S[until L]
        long hash = 0;
        for (int i = 0; i < L; ++i) {
            hash = (hash * a + nums[i]) % modulus;
        }
        // already seen hashes of strings of length L
        HashSet<Long> seen = new HashSet<>();
        seen.add(hash);

        long aLargest = 1;
        for (int i = 1; i < L; ++i) aLargest = (aLargest * a) % modulus;
        aLargest = aLargest % modulus;

        for (int start = 1; start < n - L + 1; ++start) {
            // compute rolling hash in O(1) time
            long outHash = (nums[start - 1] * aLargest) % modulus; // hash being removed
            hash = (hash - outHash + modulus) % modulus; // Make sure number not going negative.
            hash = (hash * a) % modulus;  // Power increase for all remaining
            long inHash = nums[start + L - 1]; // new char hash
            hash = (hash + inHash) % modulus;
            if (seen.contains(hash)) return start;
            seen.add(hash);
        }
        return -1;
    }

    public int longestRepeatingSubstring(String S) {
        int n = S.length();
        // convert string to array of integers
        // to implement constant time slice
        int[] nums = new int[n];

        for (int i = 0; i < n; ++i) nums[i] = (int) S.charAt(i) - (int) 'a';
        // base value for the rolling hash function
        int a = 26;
        // modulus value for the rolling hash function to avoid overflow
        long modulus = (long) Math.pow(2, 24);

        // binary search, L = repeating string length
        int left = 1, right = n;
        int L;
        while (left <= right) {
            L = left + (right - left) / 2;
            if (robinKarpRollingHash(L, a, modulus, n, nums) != -1) left = L + 1;
            else right = L - 1;
        }

        return left - 1;
    }

    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        // solve special cases
        if (n <= 0 || k <= 0) {
            return 0;
        }

        if (2 * k > n) {
            int res = 0;
            for (int i = 1; i < n; i++) {
                res += Math.max(0, prices[i] - prices[i - 1]);
            }
            return res;
        }

        // dp[i][used_k][ishold] = balance
        // ishold: 0 nothold, 1 hold
        int[][][] dp = new int[n][k + 1][2];

        // initialize the array with -inf
        // we use -1e9 here to prevent overflow
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][0] = -1000000000;
                dp[i][j][1] = -1000000000;
            }
        }

        // set starting value
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];

        // fill the array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // transition equation
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                // you can't hold stock without any transaction
                if (j > 0) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
        }

        int res = 0;
        for (int j = 0; j <= k; j++) {
            res = Math.max(res, dp[n - 1][j][0]);
        }

        return res;
    }

    private int checkMinDominoRotations(int x, int[] A, int[] B, int n) {
        // how many rotations should be done
        // to have all elements in A equal to x
        // and to have all elements in B equal to x
        int rotations_a = 0, rotations_b = 0;
        for (int i = 0; i < n; i++) {
            // rotations coudn't be done
            if (A[i] != x && B[i] != x) return -1;
                // A[i] != x and B[i] == x
            else if (A[i] != x) rotations_a++;
                // A[i] == x and B[i] != x
            else if (B[i] != x) rotations_b++;
        }
        // min number of rotations to have all
        // elements equal to x in A or B
        return Math.min(rotations_a, rotations_b);
    }

    public int minDominoRotations(int[] A, int[] B) {
        int n = A.length;
        int rotations = checkMinDominoRotations(A[0], B, A, n);
        // If one could make all elements in A or B equal to A[0]
        if (rotations != -1 || A[0] == B[0]) return rotations;
            // If one could make all elements in A or B equal to B[0]
        else return checkMinDominoRotations(B[0], B, A, n);
    }

    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Node cloneGraphHelper(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node);
        Node newNode = new Node();
        map.put(node, newNode);
        newNode.val = node.val;
        if (node.neighbors == null) return newNode;
        for (int i = 0; i < node.neighbors.size(); i++) {
            newNode.neighbors.add(cloneGraphHelper(node.neighbors.get(i), map));
        }
        return newNode;
    }

    public Node cloneGraph(Node node) {
        Map<Node, Node> cloned = new HashMap<>();
        if (node == null) return null;
        return cloneGraphHelper(node, cloned);
    }

    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int ast : asteroids) {
            collision:
            {
                while (!stack.isEmpty() && ast < 0 && 0 < stack.peek()) {
                    if (stack.peek() < -ast) {
                        stack.pop();
                        continue;
                    } else if (stack.peek() == -ast) {
                        stack.pop();
                    }
                    break collision;
                }
                stack.push(ast);
            }
        }

        int[] ans = new int[stack.size()];
        for (int t = ans.length - 1; t >= 0; --t) {
            ans[t] = stack.pop();
        }
        return ans;
    }

    public int searchReader(ArrayReader reader, int target) {
        int start = 0, end = 10000;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (reader.get(mid) > 9999) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        int left = 0, right = end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int val = reader.get(mid);
            if (val == target) return mid;
            if (val > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.getLeft() == null) {
            return 1 + minDepth(root.getRight());
        } else if (root.getRight() == null) {
            return 1 + minDepth(root.getLeft());
        }
        return 1 + Math.min(minDepth(root.getLeft()), minDepth(root.getRight()));
    }
}






