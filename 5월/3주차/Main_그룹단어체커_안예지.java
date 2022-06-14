package mon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1316_그룹단어체커 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int cnt =0;
        for (int i = 0; i < N; i++) {
            int arr[] = new int[26];
            String st = br.readLine();

            char cc= ' ';
            boolean plug = false;
            for (int j = 0; j < st.length(); j++) {
                char ch= st.charAt(j);
                int num = ch-'a';
                if(cc!=ch) {

                    if (arr[num] != 0) {// 이미 들어갔던적이 있음
                        plug =true;
                        break;

                    }else{
                        cc=ch;
                        arr[num]++;
                    }

                }else{
                    arr[num]++;
                }

            }
            if (!plug) cnt++;
//            System.out.println(plug);
//            System.out.println(Arrays.toString(arr));
        }
        System.out.println(cnt);
    }
}
