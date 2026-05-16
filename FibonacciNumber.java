import java.util.*;

public class FibonacciNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);

        System.out.println(recursion(n));
        System.out.println(memo(n, dp));
        System.out.println(bottomUp(n));
        System.out.println(spaceOptimized(n));

        sc.close();
    }

    public static int recursion(int n){
        if(n <= 1) return n;
        return recursion(n-1) + recursion(n-2);
    }

    public static int memo(int n, int dp[]) {
        if(n <= 1) return n;
        if(dp[n] != -1) return dp[n];

        return dp[n] = memo(n-1, dp) + memo(n-2, dp);
    }

    public static int bottomUp(int n) {
        int dp[] = new int[n+1];
        dp[0] = 0;

        if(n >= 1) dp[1] = 1;
        for(int i =2; i<=n ;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    public static int spaceOptimized(int n) {
        if(n <= 1) return n;
        int a = 0;
        int b = 1;

        for(int i=2; i<=n; i++) {
            int c = a+b;
            a =b;
            b =c;
        }
        return b;
    }
}
