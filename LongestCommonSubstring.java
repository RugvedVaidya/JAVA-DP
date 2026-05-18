import java.util.*;

public class LongestCommonSubstring {
    public static void main(String[] args) {
    
        String s1 = "bdefg";
        String s2 = "bfg";

        System.out.println(recursion(s1, s2, s1.length(), s2.length(), 0, 0, 0));

        int[][][] dp = new int[s1.length()][s2.length()][Math.min(s1.length(), s2.length()) + 1];
        for (int[][] matrix : dp) {
            for (int[] row : matrix) {
                Arrays.fill(row, -1);
            }
        }

        System.out.println(memo(s1, s2, s1.length(), s2.length(), 0, 0, 0, dp));
        System.out.println(tabulation(s1, s2));
    }

    public static int recursion(String s1, String s2, int n, int m ,int i, int j, int count) {
        
        if(i == n || j == m) return count;

        int take = count;
        if(s1.charAt(i) == s2.charAt(j)) {
            take = recursion(s1, s2, n, m,i+1, j+1, count + 1);
        }

        int skip1 = recursion(s1, s2, n,m, i+1, j, 0);
        int skip2 = recursion(s1, s2, n, m, i, j+1, 0);

        return Math.max(take, Math.max(skip1, skip2));
    }

    public static int memo(String s1, String s2, int n, int m, int i , int j, int count , int dp[][][]) {

        if(i == n || j == m) return count;
        if(dp[i][j][count] != -1) return dp[i][j][count];

        int take = count;
        if(s1.charAt(i) == s2.charAt(j)) {
            take = memo(s1, s2, n, m, i+1, j+1, count + 1, dp);
        }

        int skip1 = memo(s1, s2, n,m, i+1, j, 0, dp);
        int skip2 = memo(s1, s2, n, m, i, j+1, 0, dp);

        return dp[i][j][count] = Math.max(take, Math.max(skip1, skip2));
    }

    public static int tabulation(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();

        int dp[][] = new int[n+1][m+1];

        for(int i =0; i<=n; i++) {
            dp[i][0] = 0;
        }

        for(int j =0; j<=m; j++) {
            dp[0][j] = 0;
        }

        int max = 0;
        for(int i =1; i<=n; i++) {
            for(int j=1; j<=m; j++) {

                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                    max = Math.max(max, dp[i][j]);
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }
}
