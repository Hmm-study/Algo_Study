import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_나는친구가적다_허범 {

    static String S, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        K = br.readLine();

        S = S.replaceAll("[0-9]", "");


        System.out.println(S);
        System.out.println(K);
        if(S.contains(K)) {
            System.out.println("1");
        }
        else
            System.out.println("0");

    }
}
