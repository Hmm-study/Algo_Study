import java.util.*;
import java.io.*;

public class Main_시각_18312 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String K = Integer.toString(sc.nextInt());

//        int time =0;
        int cnt =0;
        String tmp = "0";
        for(int h=0;h<=N;h++){
            tmp = Integer.toString(h);
            String hour = "0";
            if(h<10)
                hour = hour.concat(tmp);
            else
                hour = tmp;
            for(int m=0;m<60;m++){
                tmp = Integer.toString(m);
                String minute = "0";
                if(m<10)
                    minute = minute.concat(tmp);
                else
                    minute = tmp;
                for(int s=0;s<60;s++){
//                    String t = Integer.toString(time);
                    tmp = Integer.toString(s);
                    String second = "0";

                    if(s<10)
                        second = second.concat(tmp);
                    else
                        second = tmp;

                    if(second.contains(K) || minute.contains(K) || hour.contains(K)){
                        cnt++;
                    }

                    System.out.println(hour+":"+minute+":"+second);
//                    time++;
                }
//                time +=40;
            }
//            time+=4000;
        }

        System.out.println(cnt);

    }
}
