import java.util.*;

public class LongestPalindromeSubsequence{

    public static void main(String[] args) {
        String s = "bbbab";
        System.out.println(recursion(s, 0, s.length()-1));

        int dp[][] = new int[s.length()][s.length()];
        for(int i=0; i<s.length(); i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(memo(s, 0, s.length()-1, dp));
        System.out.println(tabulation(s));
    }

    public static int recursion(String s, int i, int j) {
        if(i > j) return 0;
        if(i == j) return 1;

        if(s.charAt(i) == s.charAt(j)) {
            return 2 + recursion(s, i+1, j-1);
        }

        int left = recursion(s, i+1, j);
        int right = recursion(s, i, j-1);

        return Math.max(left, right);
    }

    public static int memo(String s, int i, int j, int dp[][]) {
        if(i > j) return 0;
        if(i == j) return 1;

        if(dp[i][j] != -1) return dp[i][j];

        if(s.charAt(i) == s.charAt(j)) {
            dp[i][j] = 2 + memo(s, i+1, j-1, dp);
            return dp[i][j];
        }

        int left = memo
        (s, i+1, j, dp);
        int right = memo(s, i, j-1, dp);

        dp[i][j] = Math.max(left, right);
        return dp[i][j];
    }

    public static int tabulation(String s) {

        int n = s.length();
        int dp[][] = new int[n][n];
        for(int i=0; i<n; i++) {
            dp[i][i] =1;
        }

        for(int i=n-1; i>=0; i--) {
            for(int j=i+1; j<n; j++) {
                if(s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}