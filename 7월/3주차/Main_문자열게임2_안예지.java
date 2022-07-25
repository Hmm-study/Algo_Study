package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_20437_문자열게임2 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for(int tc =0; tc<TC ; tc++){
            int alpa[] = new int[26];
            String W = br.readLine();
            int K  = Integer.parseInt(br.readLine());
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int i = 0; i <W.length() ; i++) {
                alpa[W.charAt(i)-'a']++;
            }

            if(K==1){
                System.out.println("1 1");
                continue;
            }
            for (int i = 0; i < W.length(); i++) {
                int len = 1;
                int cnt = 1;
                if(alpa[W.charAt(i)-'a']>=K){
                    for (int j = i+1; j < W.length(); j++) {
                        if(W.charAt(i)==W.charAt(j)) cnt++;
                            len++;
//                        System.out.println(W.charAt(j));
                        if(cnt == K) {
//                            System.out.println(cnt+" "+len);
                            if(min>len) min=len;
                            if(max<len) max =len;
                            break;
                        }
                    }
                }

            }

            if (min==Integer.MAX_VALUE||max==Integer.MIN_VALUE) System.out.println("-1");
            else System.out.println(min+" "+max);

        }
    }
}
