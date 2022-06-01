import java.util.*;
import java.io.*;

public class Main_폴리오미노_1343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        input = input.replaceAll("XXXX","AAAA");
        input = input.replaceAll("XX","BB");

        System.out.println(input.contains("X")? -1:input);

    }
}
