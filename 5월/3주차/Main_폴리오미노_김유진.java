import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_폴리오미노_김유진
 * author 	    : 김유진
 * date		    : 2022-05-14
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-14	        김유진  		        최초 생성
 */
public class Main_폴리오미노_김유진 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(simul(br.readLine()));
    }

    static String simul(String input) {
        String ans = "";

        Pattern p = Pattern.compile("(X+)|([.]+)");
        Matcher m = p.matcher(input);

        while (m.find()) {
            String group = m.group();
            if (group.contains("X")) { //
                int len = group.length();
                if (len % 2 != 0) return "-1";
                int idx = 0;
                while (len >= idx + 4) {
                    ans += "AAAA";
                    idx += 4;
                }
                if (len != idx) ans += "BB";

            } else ans += group; // .으로 이루어진 그룹은 그대로 가져오기
        }
        return ans;
    }

}