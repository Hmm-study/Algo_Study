import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[][] input = new char[5][15];

        for(int i=0;i<5;i++){
            input[i] = br.readLine().toCharArray();
        }

        char[][] tmp = new char[5][15];

        for(int i=0;i<5;i++){
            Arrays.fill(tmp[i],'!');
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<input[i].length;j++){
                tmp[i][j] = input[i][j];
            }
        }

        for(int i=0;i<15;i++){
            for(int j=0;j<5;j++){
                if(tmp[j][i]!='!')
                    sb.append(tmp[j][i]);
            }
        }

        System.out.println(sb);

    }


}
