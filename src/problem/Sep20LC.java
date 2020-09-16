package problem;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: randheercode
 * Date:
 * Time:
 */
public class Sep20LC {
    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        s = s.trim();
        int idx = s.lastIndexOf(" ");
        return s.length() - 1 - idx;
    }

    public int findMaximumXOR(int[] nums) {
        int maxNum = nums[0];
        for (int num : nums) maxNum = Math.max(maxNum, num);
        int L = (Integer.toBinaryString(maxNum)).length();

        int maxXor = 0, currXor;
        Set<Integer> prefixes = new HashSet<>();
        for (int i = L - 1; i > -1; --i) {
            maxXor <<= 1;
            currXor = maxXor | 1;
            prefixes.clear();
            for (int num : nums) prefixes.add(num >> i);
            for (int p : prefixes) {
                if (prefixes.contains(currXor ^ p)) {
                    maxXor = currXor;
                    break;
                }
            }
        }
        return maxXor;
    }
}
