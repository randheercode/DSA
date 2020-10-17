package problem.challenge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

}


