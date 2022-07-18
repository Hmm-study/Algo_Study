import java.util.*;
import java.io.*;

public class Main_DNA_1969 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] DNA = new char[N][M];
        int hd = 0;

        for(int i=0;i<N;i++){
            DNA[i] = br.readLine().toCharArray();
        }

        for(int i=0;i<M;i++){
            int[] nCnt = new int[4]; // T G C A
            for(int j=0;j<N;j++){
                if(DNA[j][i] == 'T'){
                    nCnt[0]++;
                }else if(DNA[j][i] == 'G'){
                    nCnt[1]++;
                }else if(DNA[j][i] == 'C'){
                    nCnt[2]++;
                }else if(DNA[j][i] == 'A'){
                    nCnt[3]++;
                }
            }
            int max = -1;
            int maxIndex = 0;
            for(int j=0;j<4;j++){
                if(nCnt[j] >= max){
                    max = nCnt[j];
                    maxIndex = j;
                }
            }
            char maxN = 'X';
            if(maxIndex == 0){
                sb.append('T');
                maxN = 'T';
            }else if(maxIndex == 1){
                sb.append('G');
                maxN = 'G';
            }else if(maxIndex == 2){
                sb.append('C');
                maxN = 'C';
            }else {
                sb.append('A');
                maxN = 'A';
            }

            for(int j=0;j<N;j++){
                if(DNA[j][i] != maxN)
                    hd++;
            }

        }

        System.out.println(sb);
        System.out.println(hd);

    }
}
