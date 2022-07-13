package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18312_시각 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        StringBuilder sb;
        int N = Integer.parseInt(st.nextToken());
        String K = st.nextToken();

        int ss = 0;
        int mm = 0;
        int hh = 0;

        int ans=0;
        while (true){
            sb = new StringBuilder();

            if(hh==N+1 && mm==0 && ss ==0) break;

            if(hh<10){
                sb.append("0");
                sb.append(hh);
            }else{
                sb.append(hh);
            }

            if(mm<10){
                sb.append("0");
                sb.append(mm);
            }else{
                sb.append(mm);
            }

            if(ss<10){
                sb.append("0");
                sb.append(ss);
            }else{
                sb.append(ss);
            }

            String string = sb.toString();
//            System.out.println(string);

            if(string.contains(K)) {

                ans++;

            }
            ss++;
            if(ss==60){
                mm++;
                ss=0;
            }
            if(mm==60){
                hh++;
                mm=0;
            }
        }
        System.out.println(ans);

    }
}
