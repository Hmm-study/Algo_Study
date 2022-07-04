import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        /*
         * 1. 모음(a,e,i,o,u)하나 반드시 포함
         * 2. 모음이 3개 혹은 자음이 3개 연속으로 ㄴㄴ
         * 3. 같은 글자 연속으로 두번 ㄴㄴ (ee,oo는 가능)
         * */

        while (true) {
            String input = br.readLine(); // 입력
            if (input.equals("end")) {
                break;
            } // 종료 조건

            // 1
            int result = 0;
            if (input.contains("a") || input.contains("e") || input.contains("i") || input.contains("o") || input.contains("u")) {
                result++;
            }

            // 2
            char[] inputChar = input.toCharArray();
            if (inputChar.length > 2) {
                for (int i = 0; i < inputChar.length - 2; i++) {
                    int moCnt = 0, jaCnt = 0;
                    if ((inputChar[i] == 'a' || inputChar[i] == 'e' || inputChar[i] == 'i' || inputChar[i] == 'o' || inputChar[i] == 'u'))
                        moCnt++;
                    else
                        jaCnt++;

                    if ((inputChar[i + 1] == 'a' || inputChar[i + 1] == 'e' || inputChar[i + 1] == 'i' || inputChar[i + 1] == 'o' || inputChar[i + 1] == 'u'))
                        moCnt++;
                    else
                        jaCnt++;

                    if ((inputChar[i + 2] == 'a' || inputChar[i + 2] == 'e' || inputChar[i + 2] == 'i' || inputChar[i + 2] == 'o' || inputChar[i + 2] == 'u'))
                        moCnt++;
                    else
                        jaCnt++;
                    if (moCnt == 3 || jaCnt == 3) {
                        result += 100;
                    }
                }
            }
            result++;


            // 3
            boolean xxCheck = false;
            for (int i = 0; i < inputChar.length - 1; i++) {
                if (inputChar[i] == inputChar[i + 1] && inputChar[i] != 'e' && inputChar[i] != 'o') {
                    xxCheck = true;
                    break;
                }
            }
            if (!xxCheck)
                result++;

            String output;
            if (result == 3) {
                output = "is acceptable.";
            } else {
                output = "is not acceptable.";
            }
            System.out.println("<" + input + "> " + output);
        }
    }
}
