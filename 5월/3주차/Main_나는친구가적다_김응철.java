import java.util.*;
import java.io.*;

public class Main_나는친구가적다Small_16171 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = str1.replaceAll("[0-9]", "");

//        boolean result = str3.contains(str2);

        boolean result = false;
        for(int i=0;i<=str3.length() - str2.length();i++){
            if(str3.substring(i,i+str2.length()).equals(str2))
                result = true;
        }

        System.out.println(result ? 1 : 0);

    }
}
