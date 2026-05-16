import java.util.*;

public class CountSubsetsWithSumK {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3};
        int k = 6;
        
        System.out.println(recursion(arr, 0, k));

        int dp[][] = new int[arr.length][k+1];
        for(int i=0; i<arr.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(memoization(arr, dp, 0, k));
        System.out.println(tabulation(arr, k));
    }

    public static int recursion(int[] arr, int idx, int k) {

        if(idx == arr.length) {
            if(k == 0) return 1;
            return 0;
        }

        int take = recursion(arr, idx + 1, k - arr[idx]);
        int notTake = recursion(arr, idx + 1, k);
        
        return take + notTake;
    }

    public static int memoization(int arr[], int dp[][], int idx, int k) {
        
        if(idx == arr.length) {
            if(k == 0) return 1;
            return 0;
        }

        if(dp[idx][k] != -1) return dp[idx][k];

        int take = memoization(arr, dp, idx + 1, k - arr[idx]);
        int notTake = memoization(arr, dp, idx + 1, k);

        return dp[idx][k] = take + notTake;
    }
    
    public static int tabulation(int arr[], int k) {

        int n = arr.length;

        int dp[][] = new int[n+1][k+1];

        for(int i=0; i<=n; i++) {
            dp[i][0] = 1;
        } 

        for(int i=1; i<=n; i++) {
            for(int j=0; j<=k; j++) {
                int take = 0;
                if(j - arr[i-1] >= 0) {
                    take = dp[i-1][j-arr[i-1]];
                }
                int notTake = dp[i-1][j];
                dp[i][j] = take + notTake;
            }
        }
        return dp[n][k];
    }
}