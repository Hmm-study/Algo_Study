package mon7;

import java.util.Arrays;
import java.util.Scanner;

public class Main_10798_세로읽기 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        char arr[][] = new char[5][15];

        for(int i=0;i<5;i++){
            Arrays.fill(arr[i],'.');
        }

        for (int i = 0; i < 5; i++) {
            String ss = sc.next();
            for (int j = 0; j < ss.length(); j++) {
                arr[i][j] = ss.charAt(j);
            }
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 5; j++) {
                if(arr[j][i]!='.'){
                    sb.append(arr[j][i]);
                }
            }
        }

        System.out.println(sb);
    }
}
