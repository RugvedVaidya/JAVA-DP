import java.util.*;

public class CoinChange2 {
    public static void main(String[] args) {
        int coins[] = {1, 2, 5};
        int amount = 5;

        System.out.println(recursion(coins, 0, amount));
        int dp[][] = new int[coins.length][amount+1];
        for(int i =0; i<coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println(memo(coins, 0, amount, dp));

        System.out.println(tabulation(coins, amount));
    }

    public static int recursion(int coins[], int idx, int amount) {

        if(amount == 0) return 1;
        if(idx == coins.length || amount < 0) return 0;

        int take = 0;
        if(amount >= coins[idx]) {
            take = recursion(coins, idx, amount - coins[idx]);
        }
        
        int nottake = recursion(coins, idx+1, amount);
        return take + nottake;
    }

    public static int memo(int coins[], int idx, int amount, int dp[][]) {

        if(amount == 0) return 1;
        if(coins.length == idx || amount < 0) return 0;
        if(dp[idx][amount] != -1) return dp[idx][amount];

        int take = 0;
        if(amount >= coins[idx]){
            take = memo(coins, idx, amount - coins[idx], dp);
        }
        int nottake = memo(coins, idx+1, amount, dp);
        dp[idx][amount] = take + nottake;
        return dp[idx][amount];
    }

    public static int tabulation(int coins[], int amount) {

        int n = coins.length;
        int dp[][] = new int[n+1][amount + 1];

        for(int i=0; i<=n; i++) {
            dp[i][0] = 1; // 1 because there is one way to make the amount 0, taking no coins
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<= amount; j++) {
                int take =0;
                if(j >= coins[i-1]){
                    take = dp[i][j- coins[i-1]]; // dp[i] because we can take the same coin again
                }
                int nottake = dp[i-1][j];
                dp[i][j] = take + nottake;
            }
        }
        return dp[n][amount];
    }
}
