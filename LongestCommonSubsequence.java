import java.util.*;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        
        String s1 = "bdefg";
        String s2 = "bfg";

        System.out.println(recursion(s1, s2, s1.length(), s2.length(), 0, 0));

        int dp[][] = new int[s1.length()][s2.length()];
        for(int i=0; i<s1.length(); i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(memo(s1, s2, s1.length(), s2.length(), 0, 0, dp));

        System.out.println(tabulation(s1, s2, s1.length(), s2.length()));
    }

    public static int recursion(String s1, String s2, int n, int m, int i, int j) {
        
        if(i == n || j == m) return 0;

        int take = 0;
        if(s1.charAt(i) == s2.charAt(j)) {
            take =1 + recursion(s1, s2, n, m, i+1, j +1);
        }

        int notTake = Math.max(recursion(s1, s2, n, m, i+1, j), recursion(s1, s2, n, m, i, j+1));

        return Math.max(take, notTake);
    }

    public static int memo(String s1, String s2, int n, int m, int i, int j, int[][] dp) {

        if(i == n || j == m) return 0;
        if(dp[i][j] != -1) return dp[i][j];

        int take = 0;
        if(s1.charAt(i) == s2.charAt(j)) {
            take = 1 + memo(s1, s2, n, m, i+1, j+1, dp);
        }

        int notTake = Math.max(memo(s1, s2, n, m, i+1, j, dp), memo(s1, s2, n, m, i, j+1, dp));
        dp[i][j] = Math.max(take, notTake);

        return dp[i][j];
    }

    public static int tabulation(String s1, String s2, int n, int m) {

        int dp[][] = new int[n+1][m+1];

        for(int i=0; i<=n; i++) {
            dp[i][0] = 0;
        }

        for(int j =0; j<= m; j++) {
            dp[0][j] = 0;
        }

        for(int i=1; i<=n ;i++) {
            for(int j=1; j<=m; j++) {
                int take = 0;
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    take = 1 + dp[i-1][j-1];
                }

                int notTake = Math.max(dp[i-1][j], dp[i][j-1]);

                dp[i][j] = Math.max(take, notTake);
            }
        }
        return dp[n][m];
    }
}
