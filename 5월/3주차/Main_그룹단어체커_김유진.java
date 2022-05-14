import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_그룹단어체커_김유진
 * author 	    : 김유진
 * date		    : 2022-05-14
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-14	        김유진  		        최초 생성
 */
public class Main_그룹단어체커_김유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        int N = Integer.parseInt(br.readLine());
        Pattern p = Pattern.compile("(a+)|(b+)|(c+)|(d+)|(e+)|(f+)|(g+)|(h+)|(i+)|(j+)|(k+)|(l+)|(m+)|(n+)|(o+)|(p+)|(q+)|(r+)|(s+)|(t+)|(u+)|(v+)|(w+)|(x+)|(y+)|(z+)");

        for (int n = 0; n < N; n++) {
            Matcher m = p.matcher(br.readLine());
            HashSet<String> set = new HashSet<>();
            boolean flag = true;
            while (m.find()) {
                String next = m.group().substring(0,1);

                if(set.contains(next)) {
                    flag = false;
                    break;
                }
                set.add(next);
            }
            if(flag) cnt++;
        }
        System.out.println(cnt);
    }
}