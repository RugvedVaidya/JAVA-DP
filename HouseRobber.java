import java.util.*;

public class HouseRobber {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();


        int arr[] = new int[n];
        for(int i=0; i<n; i++){
            arr[i] =sc.nextInt();
        }

        System.out.println(recursion(arr, n-1));

        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        System.out.println(memo(arr, n-1, dp));
        System.out.println(tabulation(arr));
        System.out.println(spaceOptimized(arr));

        sc.close();
    }

    public static int recursion(int arr[], int idx) {
        
        if(idx == 0) return arr[0];
        if(idx < 0) return 0;

        int pick = arr[idx] + recursion(arr, idx-2);
        int notpick = recursion(arr, idx-1);

        return Math.max(pick, notpick);
    }

    public static int memo(int arr[], int idx, int dp[]){

        if(idx == 0) return arr[0];
        if(idx < 0) return 0;

        if(dp[idx] != -1) return dp[idx];

        int pick = arr[idx] + memo(arr, idx-2, dp);
        int notpick = memo(arr, idx-1, dp);

        return dp[idx] = Math.max(pick, notpick);
    }

    public static int tabulation(int arr[]) {

        int dp[] = new int[arr.length];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);

        for(int i=2; i<arr.length; i++) {
            int pick = arr[i] + dp[i-2];
            int notpick = dp[i-1];
            dp[i] = Math.max(pick, notpick);
        }
        return dp[arr.length-1];
    }

    public static int spaceOptimized(int arr[]) {
        int prev = 0;
        int prev2 = 0;

        for(int i =0; i<arr.length; i++){
            int pick = arr[i]  +prev2;
            int notpick = prev;

            int curr= Math.max(pick , notpick);
            prev2 = prev;
            prev =curr;
        }
        return prev;
    }
}
