package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 듣보잡_1764 {

    static String[] a;
    static String[] b;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        a = new String[n];
        b = new String[m];

        for (int i = 0; i < n; i++)
            a[i] = br.readLine();
        for (int i = 0; i < m; i++)
            b[i] = br.readLine();

        Arrays.sort(a);

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(a[i].equals(b[j]))
                    sb.append(a[i] + "\n");

            }
        }
        System.out.println(sb);
    }
}
