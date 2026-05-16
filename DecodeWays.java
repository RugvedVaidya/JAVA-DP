import java.util.*;

public class DecodeWays {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(recursion(s, 0));

        int dp[] = new int[s.length()];
        Arrays.fill(dp, -1);
        System.out.println(memo(s, 0, dp));
        System.out.println(bottomUp(s));

        sc.close();
    }

    public static int recursion(String s, int idx) { //TLE

        if(idx== s.length()) return 1;
        if(s.charAt(idx) == '0') return 0;

        int one = recursion(s, idx+1);
        int two = 0;
        if(idx+1 < s.length() && s.substring(idx, idx+2).compareTo("26") <= 0){
            two = recursion(s, idx+2);
        }
        return one + two;
    }

    public static int memo(String s, int idx, int dp[]) {

        if(idx == s.length())return 1;
        if(s.charAt(idx) == '0') return 0;
        if(dp[idx] != -1) return dp[idx];

        int one = memo(s, idx+1, dp);
        int two = 0;

        if(idx+1 < s.length() && s.substring(idx, idx+2).compareTo("26") <= 0){
            two = memo(s, idx+2, dp);
        }
        return dp[idx] =one+two;
    }

    public static int bottomUp(String s){

        int dp[] = new int[s.length() +1];
        dp[0] = 1;

        for(int i = 1; i<= s.length(); i++) {
            if(s.charAt(i-1) != '0'){
                dp[i] =dp[i-1];
            }

            if(i >= 2){
                int twoDigit = Integer.parseInt(s.substring(i-2, i));
                if(twoDigit >= 10 && twoDigit <= 26) {
                    dp[i] += dp[i-2];
                }
            }
        }
        return dp[s.length()];
    }
}
