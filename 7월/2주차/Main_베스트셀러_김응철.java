import java.util.*;
import java.io.*;

public class Main_베스트셀러_1302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        HashMap<String, Integer> bookList = new HashMap<>();
        for (int n = 0; n < N; n++) {
            String name = br.readLine();
            if (bookList.containsKey(name)) {
                int cnt = bookList.get(name);
                bookList.put(name, cnt + 1);
            } else
                bookList.put(name, 1);
        }
        // str1이 사전순으로 str2보다 앞에있으면 음수를 반환
        int max = 0;
        String result = "z";
        for (String key : bookList.keySet()) {

            if (bookList.get(key) > max || (bookList.get(key) == max && key.compareTo(result) < 0)) {
                max = bookList.get(key);
                result = key;
            }
        }

        System.out.println(result);
    }
}
