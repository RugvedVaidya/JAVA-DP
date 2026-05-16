import java.util.*;

public class CountPartitionsWithGivenDifference { // s1 - s2 = diff
    public static void main(String[] args) {
        
        int arr[]  = {1, 1, 2, 3};
        int diff = 1;

        // s1 - s2 = diff
        // s1 + s2 = sum(arr)
        // 2s1 = diff + sum(arr)
        // s1 = (diff + sum(arr)) / 2

        int sum = 0;
        for(int i=0; i<arr.length; i++) {
            sum += arr[i];
        }

        int s1 = (diff + sum) / 2;

        System.out.println(recursion(arr, 0, s1));

        int dp[][] = new int[arr.length][s1+1];
        for(int i=0; i<arr.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(memo(arr, dp, 0, s1));
        System.out.println(tabulation(arr, s1));
    }

    public static int recursion(int arr[], int idx, int target) {

        if(arr.length == idx) {
            if(target == 0) return 1;
            return 0;
        }

        int take = recursion(arr, idx + 1, target - arr[idx]);
        int notTake = recursion(arr, idx + 1, target);

        return take + notTake;
    }

    public static int memo(int arr[], int dp[][], int idx, int target) {

        if(arr.length == idx) {
            if(target == 0) return 1;
            return 0;
        }

        if(dp[idx][target] != -1) return dp[idx][target];

        int take = memo(arr, dp, idx + 1, target - arr[idx]);
        int notTake = memo(arr, dp, idx + 1, target);

        return dp[idx][target] = take + notTake;
    }

    public static int tabulation(int arr[], int target) {

        int n = arr.length;

        int dp[][] = new int[n+1][target+1];
        for(int i=0; i<=n; i++) {
            dp[i][0] = 1;
        }

        for(int i=1; i<=n; i++) {
            for(int j=0; j<=target; j++) {
                int take = 0;
                if(j - arr[i-1] >= 0){
                    take = dp[i-1][j - arr[i-1]];
                }
                int notTake = dp[i-1][j];
                dp[i][j] = take + notTake;
            }
        }
        return dp[n][target];
    }
}
