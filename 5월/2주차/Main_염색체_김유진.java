import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_염색체_김유진
 * author 	    : 김유진
 * date		    : 2022-05-05
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-05	        김유진  		        최초 생성
 */
public class Main_염색체_김유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile("[A-F]{0,1}[A]+[F]+[C]+[A-F]{0,1}");
        Matcher m = null;
        for(int n = 0; n<N;n++){
            String line = br.readLine();
            m = p.matcher(line);
            if(m.matches())
                sb.append("Infected!");
            else
                sb.append("Good");
            sb.append("\n");
        }

        System.out.println(sb);

    }
}