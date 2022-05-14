import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_거스름돈_김유진
 * author 	    : 김유진
 * date		    : 2022-05-14
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-14	        김유진  		        최초 생성
 */
public class Main_거스름돈_김유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        if(N == 1){

        }else if(N<5){
            dp[2] = 1;
        }else {
            dp[2] = 1;
            dp[5] = 1;
        }
        for (int i = 0; i <= N; i++) {
            if (dp[i] != Integer.MAX_VALUE) {
                if (i + 2 <= N)
                    dp[i + 2] = Math.min(dp[i] + 1, dp[i + 2]);
                if (i + 5 <= N)
                    dp[i + 5] = Math.min(dp[i] + 1, dp[i + 5]);
            }
        }

//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);

    }
}