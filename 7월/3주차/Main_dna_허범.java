import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_dna_허범 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int max, count = 0;

        char dna[][] = new char[n][m];

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                dna[i][j] = str.charAt(j);
            }
        }
        for(int j = 0; j < m; j++){
            int  a = 0 , t = 0, g = 0, c = 0;
            for(int i = 0; i < n; i++){
                if(dna[i][j] == 'A'){
                    a++;
                    break;
                } else if (dna[i][j] == 'T') {
                    t++;
                    break;
                } else if (dna[i][j] == 'G') {
                    g++;
                    break;
                } else if (dna[i][j] == 'C') {
                    c++;
                    break;
                }
            }
            max = Math.max(Math.max(Math.max(a, t), g), c);
            count += (n-max);

            if(max == a)
                System.out.print('A');
            else if (max == t) {
                System.out.print('T');
            } else if (max == g) {
                System.out.print('G');
            }else System.out.print('C');
        }
        System.out.println("\n" + count);

    }
}
