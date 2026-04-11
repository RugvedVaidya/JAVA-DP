import java.util.*;

public class ClimbingStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);

        System.out.println(recursion(n, 0));
        System.out.println(memo(n, 0, dp));
        System.out.println(bottomUp(n));
        System.out.println(spaceOptimized(n));
    }

    public static int recursion(int n, int idx) { //TLE

        if(idx == n) return 1;
        if(idx > n) return 0;

        return recursion(n, idx+1) + recursion(n, idx+2);
    }

    public static int memo(int n, int idx, int dp[]) {

        if(idx == n) return 1;
        if(idx > n) return 0;
        if(dp[idx] != -1) return dp[idx];

        return dp[idx] = memo(n, idx+1, dp) + memo(n, idx+2, dp);
    }

    public static int bottomUp(int n) {

        int dp[] = new int[n+1];
        dp[0] = 1;

        for(int i=1; i<=n; i++) {
            dp[i] = dp[i-1];
            if(i-2 >= 0) dp[i] += dp[i-2];
        }
        return dp[n];
    }

    public static int spaceOptimized(int n) {
        int a = 1, b = 1;

        for(int i=2; i<=n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
