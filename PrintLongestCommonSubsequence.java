import java.util.*;

public class PrintLongestCommonSubsequence {
    public static void main(String[] args) {
        
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        System.out.println(recursion(s1, s2, s1.length(), s2.length(), 0, 0));

        String[][] dp = new String[s1.length()][s2.length()];
        for(int i =0; i< s1.length(); i++) {
            Arrays.fill(dp[i], null);
        }

        System.out.println(memo(s1, s2, s1.length(), s2.length(), 0, 0, dp));
    }

    public static String recursion(String s1, String s2, int n, int m, int i, int j) {

        if(i == n || j == m) return "";

        if(s1.charAt(i) == s2.charAt(j)) {
            String ans = s1.charAt(i) + recursion(s1, s2, n, m, i+1, j+1);
            return ans;
        }

        String notTake1 = recursion(s1, s2, n, m, i+1, j);
        String notTake2 = recursion(s1, s2, n, m, i,j +1);

        return notTake1.length() > notTake2.length() ? notTake1 : notTake2;
    }

    public static String memo(String s1, String s2, int n, int m, int i, int j, String[][] dp) {

        if(n == i || m == j) return "";
        if(dp[i][j] != null) return dp[i][j];

        if(s1.charAt(i) == s2.charAt(j)) {
            String ans = s1.charAt(i) + memo(s1, s2, n, m, i+1, j+1, dp);
            dp[i][j] =ans;
            return  ans;
        }


        String notTake1 = memo(s1, s2, n, m, i+1, j, dp);
        String notTake2 = memo(s1, s2, n, m, i, j+1, dp);

        dp[i][j] = notTake1.length() > notTake2.length() ? notTake1 : notTake2;
        return dp[i][j];
    }
}
