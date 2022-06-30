import java.io.*;
import java.util.*;

public class Main_디지털시계_1942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int n=0;n<3;n++){
            st = new StringTokenizer(br.readLine());
            String start = st.nextToken();
            String end = st.nextToken();

            start = start.replaceAll(":","");
            end = end.replaceAll(":","");

            int startTime = Integer.parseInt(start);
            int endTime = Integer.parseInt(end);

            int sh = startTime/10000;
            int sm = startTime/100 - sh*100;
            int ss = startTime%100;

            int cnt=0;
            while(true){

                if(ss>=60){
                    ss = 0;
                    sm++;
                }
                if(sm>=60){
                    sm = 0;
                    sh++;
                }
                if(sh>=24){
                    sh = 0;
                    sm = 0;
                    ss = 0;
                }

                startTime = sh*10000 + sm*100 + ss;

                if(startTime%3==0){
                    cnt++;
                }

                if(startTime == endTime)
                    break;
                ss++;
            }

            System.out.println(cnt);
        }
    }
}
