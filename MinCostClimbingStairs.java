import java.util.*;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cost[] = new int[n];
        for(int i=0; i<n; i++) {
            cost[i] = sc.nextInt();
        }
        
        int dp[] = new int[n];
        Arrays.fill(dp, -1);

        System.out.println(Math.min(recursion(cost, 0), recursion(cost, 1)));
        System.out.println(Math.min(memo(cost, 0, dp), memo(cost, 1, dp)));
        System.out.println(bottomUp(cost));
        System.out.println(spaceOptimized(cost));

        sc.close();
    }

    public static int recursion(int cost[], int idx) { //TLE

        if(idx >= cost.length) return 0;
    
        int one = recursion(cost, idx +1);
        int two = recursion(cost, idx +2);

        return cost[idx] + Math.min(one, two);
    }

    public static int memo(int cost[], int idx, int dp[]) {

        if(idx >= cost.length) return 0;
        if(dp[idx] != -1) return dp[idx];

        int one = memo(cost, idx+1, dp);
        int two = memo(cost, idx+2, dp);

        return dp[idx] = cost[idx] + Math.min(one, two);
    }

    public static int bottomUp(int cost[]){

        int n = cost.length;
        int dp[] = new int[n];

        dp[0] = cost[0];
        dp[1] = cost[1];

        for(int i=2 ; i<n;i++) {
            dp[i] = cost[i] + Math.min(dp[i-1], dp[i-2]);
        }

        return Math.min(dp[n-1], dp[n-2]);
    }

    public static int spaceOptimized(int cost[]) {

        int n = cost.length;
        int a = cost[0];
        int b = cost[1];

        for(int i=2 ;i<n; i++) {
            int c = cost[i] + Math.min(a, b);
            a= b;
            b = c;
        }
        return Math.min(a, b);
    }
}
