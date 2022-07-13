package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시각_18312 {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int cnt = 0;

        for(int h = 0; h <= N; h++){
            if(h/10 == K || h%10 == K)
                cnt++;
            for(int m = 0; m <= 59; m++){
                if(m/10 == K || m%10 == K)
                    cnt++;
                for(int s = 0; s <= 59; s++){
                    if(s/10 == K || s%10 == K)
                        cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
