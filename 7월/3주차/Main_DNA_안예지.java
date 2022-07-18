package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1969_DNA2 {
    static char ch[]= {'A','C','G','T'};
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
//        int result = 0;
        String arr[] = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        int sum =0;

        for (int i = 0; i < M; i++) {
            int cnt[] = new int[4];
            for (int j = 0; j < N; j++) {
                if(arr[j].charAt(i)=='A') {
                    cnt[0]++;
                }else if(arr[j].charAt(i)=='C') {
                    cnt[1]++;
                }else if(arr[j].charAt(i)=='G') {
                    cnt[2]++;
                }else if(arr[j].charAt(i)=='T') {
                    cnt[3]++;
                }

            }

            int max =0;
            int index =0;
            for (int j = 0; j < 4; j++) {
                if(max<cnt[j]) {
                    max = cnt[j];
                    index = j;
                }
            }

           sb.append(ch[index]);

        }
        String ss = sb.toString();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if(ss.charAt(i)!=arr[j].charAt(i)){
                    sum++;
                }
            }
        }


        System.out.println(ss);
        System.out.print(sum);

    }


}
