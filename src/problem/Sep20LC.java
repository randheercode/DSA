package problem;

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
}
