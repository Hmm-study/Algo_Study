package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 회문_17609 {

    static int TC;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TC = Integer.parseInt(br.readLine());


        for(int tc = 0; tc < TC; tc++){
            str = br.readLine();

            if(check(str))
                System.out.println("0");
            else if(check2(str)){
                System.out.println("1");
            }
            else
                System.out.println("2");
        }
    }

    static boolean check(String str){
        String reserve = "";
        for(int i = str.length()-1; i >= 0; i--){
            reserve += str.charAt(i);
        }
        if(str.equals(reserve))
            return true;
        else
            return false;
    }
    static boolean check2(String str){
        int a = 0;
        for(int i = 0; i < str.length(); i++){
            StringBuffer tmp = new StringBuffer(str);
            tmp.deleteCharAt(i);
            if(check(tmp.toString())) {
                a++;
                break;
            }
        }
        if(a>0)
            return true;
        else
            return false;
    }
}
