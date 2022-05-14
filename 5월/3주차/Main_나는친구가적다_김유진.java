import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_나는친구가적다_김유진
 * author 	    : 김유진
 * date		    : 2022-05-14
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-14	        김유진  		        최초 생성
 */
public class Main_나는친구가적다_김유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().replaceAll("[0-9]", "");
        Pattern p = Pattern.compile(br.readLine());
        Matcher m = p.matcher(input);
//        System.out.println(input);
//        System.out.println(p);
        System.out.println(m.find()?"1":"0");

    }
}