import java.util.*;

public class CoinChange {
    public static void main(String[] args) {
        
        int coins[] = {1,2,5};
        int amt = 11;
        
        System.out.println(recursion( coins, 0, amt));
        int dp[][] = new int[coins.length][amt+1];

        for(int i=0; i<coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(memo(coins, 0, amt, dp));
        System.out.println(tabulation(coins, amt));
    }

    public static int recursion(int coins[], int idx, int amt) {
        if(amt == 0) return 0;
        if(idx == coins.length || amt < 0) return (int)1e9;

        int take = (int)1e9;
        if(amt >= coins[idx]) {
            take = 1 + recursion(coins, idx, amt - coins[idx]);
        }
        int notTake = recursion(coins, idx + 1, amt);

        return Math.min(take, notTake); 
    } 

    public static int memo(int coins[], int idx, int amt, int dp[][]) {

        if(amt == 0) return 0;
        if(idx == coins.length || amt < 0) return (int)1e9;
        if(dp[idx][amt] != -1) return dp[idx][amt];

        int take = (int)1e9;
        if(amt >= coins[idx]) {
            take = 1 + memo(coins, idx, amt - coins[idx], dp);
        }
        int notTake = memo(coins, idx + 1, amt, dp);

        dp[idx][amt] = Math.min(take, notTake);
        return dp[idx][amt];
    }


    public static int tabulation(int coins[], int amt) {
        
        int n = coins.length;

        int dp[][] = new int[n+1][amt+1];

        for(int i =0; i<=n; i++){
            Arrays.fill(dp[i], (int)1e9);
        }

        for(int i =0; i<=n; i++) {
            dp[i][0] = 0;
        }

        for(int i=1; i<=n ;i++) {
            for(int j = 1; j<=amt; j++) {
                int take = (int)1e9;
                if(j >= coins[i-1]) {
                    take = 1 + dp[i][j - coins[i-1]];
                }
                int notTake = dp[i-1][j];
                dp[i][j] = Math.min(take, notTake);
            }
        }

        if(dp[n][amt] == (int)1e9) return -1;
        return dp[n][amt];
    }
}
