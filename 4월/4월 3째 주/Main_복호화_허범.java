package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 복호화_9046 {

    static int TC;
    static char[] alpha = new char[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < alpha.length; i++){
            alpha[i] = (char)(97 + i);
        }

        TC = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < TC; tc++){
            int max = 0;
            int answer = 0;
            String str = br.readLine();
            char[] arr = str.replace(" ","").toCharArray();
            int[] count = new int[26];
            Arrays.fill(count, 0);

            for(int i = 0; i < arr.length; i++){
                for(int j = 0; j < alpha.length; j++){
                    if(arr[i]==alpha[j]){
                        count[j]++;
                    }
                }
            }

            for(int i = 0; i < count.length; i++){
                if(max<count[i]) {
                    max = count[i];
                    answer = i;
                }
            }
            int check = 0;
            for(int i = 0; i < count.length; i++){
                if(max==count[i])
                    check++;
            }
            if(check>1)
                System.out.println("?");
            else
                System.out.println(alpha[answer]);
        }
    }
}
