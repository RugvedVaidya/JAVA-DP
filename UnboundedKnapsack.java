import java.util.*;

public class UnboundedKnapsack {
    public static void main(String[] args) {
        
        int val[] = {60, 100, 120};
        int wt[] = {10, 20 ,30};
        int W = 60;
        int n = val.length;

        System.out.println(recursion(val, wt, W, n, 0));

        int dp[][] = new int[n][W+ 1];
        for(int i =0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(memo(val, wt, W, n, 0, dp));
        System.out.println(tabulation(val, wt, W, n));
    }

    public static int recursion(int val[], int wt[], int W, int n, int idx) {

        if(idx == n || W <= 0) {
            return 0;
        }

        int take = 0;
        if(wt[idx] <= W) {
            take = val[idx] + recursion(val, wt, W- wt[idx], n, idx);
        }
        int notTake = recursion(val, wt, W, n, idx + 1);
        return Math.max(take, notTake);
    }

    public static int memo(int val[], int wt[], int W, int n, int idx, int dp[][]) {

        if(idx ==n || W <= 0) return 0;
        if(dp[idx][W] != -1) return dp[idx][W];

        int take = 0;
        if(wt[idx] <= W) {
            take = val[idx] + memo(val, wt, W- wt[idx], n, idx, dp);
        }
        int notTake = memo(val, wt, W, n, idx + 1, dp);
        
        return dp[idx][W] = Math.max(take, notTake);
    }

    public static int tabulation(int val[], int wt[], int W, int n) {

        int dp[][] = new int[n+1][W+1];
        
        for(int i =0;i <n; i++) {
            dp[i][0] = 0;
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=W; j++) {
                int take = 0;
                if(wt[i-1] <= j) {
                    take = val[i -1] + dp[i][j- wt[i-1]];
                }
                int notTake = dp[i-1][j];
                dp[i][j] = Math.max(take, notTake);
            }
        }
        return dp[n][W];
    }
}
