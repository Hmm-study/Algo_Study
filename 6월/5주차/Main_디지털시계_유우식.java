import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1942 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX_HOUR = 24;
        final int MAX_MIN = 60;
        final int MAX_SEC = 60;
        for(int i=0; i<3; i++) {
            String[] time;
            time = br.readLine().split(" ");
            String[] begin, end;
            int cnt = 0;
            begin = time[0].split(":");
            int sh = Integer.parseInt(begin[0]);
            int sm = Integer.parseInt(begin[1]);
            int ss = Integer.parseInt(begin[2]);
            end = time[1].split(":");
            int eh = Integer.parseInt(end[0]);
            int em = Integer.parseInt(end[1]);
            int es = Integer.parseInt(end[2]);

            while(true){
                int startnum = sh * 10000 + sm * 100 + ss;
                if(startnum%3==0){
                    cnt++;
                }
                if(sh == eh && sm == em && ss == es){
                    break;
                }
                ss++;
                if(ss == MAX_SEC){
                    ss = 0;
                    sm++;
                }
                if(sm == MAX_MIN){
                    sm = 0;
                    sh++;
                }
                if(sh == MAX_HOUR){
                    sh = 0;
                }
                startnum = sh * 10000 + sm * 100 + ss;

            }
            System.out.println(cnt);
        }

    }
}
