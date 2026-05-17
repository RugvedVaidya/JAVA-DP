import java.util.*;

public class RodCutting {
    public static void main(String[] args) {
        
        int price[] = {1, 5, 8, 9};
        int N = price.length;

        System.out.println(recursion(price, N, 0));

        int dp[][] = new int[N][N + 1];
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(memo(price, N, 0, dp));
        System.out.println(tabulation(price, N));
    }

    public static int recursion(int price[], int N, int idx) {

        if(N <= 0 || idx >= price.length) return 0;

        int take = 0;
        if(N >= idx + 1) {
            take = price[idx] + recursion(price, N - idx - 1, idx);
        }
        int notTake = recursion(price, N, idx + 1);
        return Math.max(take, notTake); 
    }

    public static int memo(int price[], int N, int idx, int dp[][]) {

        if(N <= 0 || idx >= price.length) return 0;
        if(dp[idx][N] != -1) return dp[idx][N];

        int take = 0;
        if(N >= idx + 1) {
            take = price[idx] + memo(price, N - idx - 1, idx, dp);
        }

        int notTake = memo(price, N, idx + 1, dp);
        return dp[idx][N] = Math.max(take, notTake);
    }

    public static int tabulation(int price[], int N) {
        
        int dp[][] = new int[price.length + 1][N + 1];

        for(int i =0; i<= price.length; i++) {
            dp[i][0] = 0;
        }

        for(int i =1; i<= price.length; i++) {
            for(int j=1; j<=N; j++) {
                int take = 0;
                if(j >= i) {
                    take = price[i-1] + dp[i][j-i];
                }
                dp[i][j] = Math.max(take, dp[i-1][j]);
            }
        }
        return dp[price.length][N];
    }
}
