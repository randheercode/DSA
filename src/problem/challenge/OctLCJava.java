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

}


