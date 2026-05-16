import java.util.*;

public class TargetSum {
    public static void main(String[] args) {
        
        int arr[] = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(recursion(arr, 0, target));

        int sum =0;
        for(int i=0; i<arr.length; i++) {
            sum += arr[i];
        }
        
        int dp[][] = new int[arr.length][2*sum + 1];
        for(int i = 0; i < arr.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(memo(arr, 0, target, dp, sum));
        System.out.println(tabulation(arr, target));
    }

    public static int recursion(int arr[], int idx, int target) {

        if(idx == arr.length) {
            if(target ==0) return 1;
            return 0;
        }

        int pos = recursion(arr, idx + 1, target + arr[idx]);
        int neg = recursion(arr, idx + 1, target - arr[idx]);

        return pos + neg;
    }

    public static int memo(int arr[], int idx, int target, int dp[][], int sum) {
        
        if(idx == arr.length) {
            if(target == 0) return 1;
            return 0;
        }

        if(target < -sum || target > sum) {
            return 0;
        }

        int shiftedTarget = target + sum;

        if(dp[idx][shiftedTarget] != -1) {
            return dp[idx][shiftedTarget];
        }

        int pos = memo(arr, idx + 1, target + arr[idx], dp, sum);
        int neg = memo(arr, idx + 1, target - arr[idx], dp, sum);

        return dp[idx][shiftedTarget] = pos + neg;
    }

    public static int tabulation(int arr[], int target) {
        
        int n = arr.length;
        int sum =0;
        for(int i : arr) {
            sum += i;
        }

        int dp[][] = new int[n+1][2*sum + 1];
        for(int i =0; i<=n; i++) {
            Arrays.fill(dp[i], 0);
        }

        dp[0][sum] = 1;

        for(int i=1; i<=n; i++) {
            for(int j=0; j<=2*sum; j++) {
                int pos = 0;
                if(j - arr[i-1] >= 0) {
                    pos = dp[i-1][j - arr[i-1]];
                }
                int neg = 0;
                if(j + arr[i-1] <= 2* sum) {
                    neg = dp[i-1][j + arr[i-1]];
                }
                dp[i][j] = pos + neg;
            }
        }
        return dp[n][target + sum];
    }
}
