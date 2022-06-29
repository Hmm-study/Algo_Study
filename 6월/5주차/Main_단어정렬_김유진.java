import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: B1181_단어정렬
 * author 	    : 김유진
 * date		    : 2022-06-29
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-06-29	        김유진  		        최초 생성
 */
public class B1181_단어정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());

        Pattern pattern = Pattern.compile("^[a-z]+");
        StringBuilder sb = new StringBuilder();
        HashSet<String> set = new HashSet<>();
        PriorityQueue<String> pq = new PriorityQueue<>(
                (s1, s2) -> s1.length() == s2.length() ? s1.compareTo(s2) : s1.length() - s2.length()
        );


        for (int i = 0; i < cnt; i++) {
            String input = br.readLine();
            Matcher matcher = pattern.matcher(input);
            if (set.contains(input))
                continue;
            else
                set.add(input);

            if (matcher.matches())
                pq.add(input);

        }

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append("\n");
        }


        System.out.println(sb);

    }
}