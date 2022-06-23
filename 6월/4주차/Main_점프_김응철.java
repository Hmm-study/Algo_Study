import java.util.*;
import java.io.*;

public class Main_점프_1890 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[][] map;
        long[][] dp;

        map = new int[N][N];
        dp = new long[N][N];
        dp[0][0] = 1;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == 0) break;

                if(i + map[i][j] < N){
                    dp[i+map[i][j]][j]+=dp[i][j];
                }
                if(j + map[i][j] < N){
                    dp[i][j+map[i][j]]+=dp[i][j];
                }
            }
        }

        System.out.println(dp[N-1][N-1]);

    }
}
