import java.util.*;

public class PrimeFactorization {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> ans = new ArrayList<>();
        solution(n, ans);
        System.out.println(ans);
    }

    public static void solution(int n, ArrayList<Integer> ans) {

        for(int i=2; i*i<=n; i++) {
            while(n%i == 0) {
                ans.add(i);
                n = n /i;
            }  
        }
        if(n > 1) {
            ans.add(n);
        }
    }
}
