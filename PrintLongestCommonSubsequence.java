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
        System.out.println(tabulation(s1, s2));
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

    public static String tabulation(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        String dp[][] = new String[n+1][m+1];

        for(int i =0;i<=n; i++) {
            dp[i][0] = "";
        }

        for(int j =0; j<=m; j++) {
            dp[0][j] = "";
        }

        for(int i=1; i<=n ;i++) {
            for(int j=1; j<=m; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + s1.charAt(i-1);
                } else {
                    if(dp[i-1][j].length() > dp[i][j-1].length()) {
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = dp[i][j-1];
                    }
                }
            }
        }
        return dp[n][m];
    }
}
