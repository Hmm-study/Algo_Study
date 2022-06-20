package mon6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1890_점프 {
    static int da[][] = {{1,0},{0,1}};
    static int map[][];
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map= new int[N][N];
        long dp[][]= new long[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st= new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

            }

        }

        dp[0][0]=1;
// 가는 경로마다 카운트 해주기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int len = map[i][j];
                if(dp[i][j]>=1 && len!=0){

                    // 아래도 가는 경로
                    if(i+len< N){
                        dp[i+len][j]+=dp[i][j];
                    }
                    //오른쪽으로 가는 경로
                    if(j+len <N){
                        dp[i][j+len]+=dp[i][j];
                    }
                }

            }
            System.out.println(Arrays.toString(dp[i]));
        }


        System.out.println(dp[N-1][N-1]);
    }


}
