import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_폴리오미노_허범 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String map = br.readLine();

        map = map.replaceAll("XXXX", "AAAA");
        map = map.replaceAll("XX", "BB");

        if(map.contains("X"))
            System.out.println(-1);
        else
            System.out.println(map);
    }
}
