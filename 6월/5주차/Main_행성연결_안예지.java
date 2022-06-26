package mon6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.util.Arrays.stream;

public class Main_16398_행성연결 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int minv[]= new int[N];

        int map[][]= new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.fill(minv,Integer.MAX_VALUE);

        boolean check[] = new boolean[N];

        minv[0]=0;

        int start =0;


        for (int i = 0; i < N; i++) {
            int dd= Integer.MAX_VALUE;
            int min =Integer.MAX_VALUE;
            check[start]=true;
            for (int j = 0; j < N; j++) {
                if(!check[j]) {
                    minv[j] = Math.min(map[start][j], minv[j]);
                    if(min>minv[j]){
                        min = minv[j];

                        dd=j;
                    }
                }

            }
            start=dd;

        }

        long result=0;
        for (int i = 0; i < N; i++) {
            result+=minv[i];
        }


        System.out.println(result);


    }
}
