package mon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16171_나는친구가적다 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st1 = br.readLine();
        String st2 = br.readLine();
        int len = st2.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < st1.length(); i++) {
            if(st1.charAt(i)>='0' && st1.charAt(i)<='9' ) continue;

            sb.append(st1.charAt(i));
        }

        String ss = sb.toString();

        boolean plug = false;
        for (int i = 0; i <=ss.length()-len ; i++) {
            String sss="";
            for (int j = i; j < i+len; j++) {
                sss+=ss.charAt(j);
            }

            if(sss.equals(st2)){
                plug=true;
                break;
            }
        }

        if(plug){
            System.out.println("1");
        }else {
            System.out.println("0");
        }
    }
}
