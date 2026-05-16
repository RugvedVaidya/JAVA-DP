import java.util.*;

public class PerfectSquares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(recursion(n));
        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);
        System.out.println(memo(n, dp));
        System.out.println(tabulation(n));
        sc.close();
    }

    public static int recursion(int n){

        if(n == 0) return 0;
        if(n < 0) return Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;

        for(int i =1; i*i<=n ;i++) {

            int curr = recursion(n - i*i);

            if(curr != Integer.MAX_VALUE){
                min = Math.min(min, curr + 1);
            }
        }
        return min;
    }

    public static int memo(int n, int dp[]) {

        if(n == 0) return 0;
        if(n < 0) return Integer.MAX_VALUE;

        if(dp[n] != -1) return dp[n];

        int min = Integer.MAX_VALUE;

        for(int i =1; i*i<=n ;i++) {

            int curr = memo(n - i*i, dp);

            if(curr != Integer.MAX_VALUE){
                min = Math.min(min, curr + 1);
            }
        }
        return dp[n] = min;
    }

    public static int tabulation(int n) {
        
        int dp[] = new int[n+1];
        dp[0] = 0;
        
        for(int i=1; i<=n ;i++) {
            int min = Integer.MAX_VALUE;

            for(int j=1 ;j*j<=i ;j++) {
                int curr = dp[i - j*j];

                if(curr != Integer.MAX_VALUE){
                    min = Math.min(min, curr + 1);
                }
            }
            dp[i] = min;
        }
        return dp[n];
    }
}
