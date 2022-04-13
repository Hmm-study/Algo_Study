import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_회문_김유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            char[] tmp = br.readLine().toCharArray();
            System.out.println(chkPalindrome(tmp));
        }
    }

    static int chkPalindrome(char[] tmp) {
        int cnt = 0;
        int len = tmp.length;
        for (int i = 0; i < len; i++) {
            if (i < len && len - i - 1 >= 0 && tmp[i] != tmp[len - 1 - i]) {
                if (i + 1 < len && tmp[i + 1] == tmp[len - i]) {
                    char[] newTmp = new char[tmp.length - 1];
                    for (int idx = 0; idx < len; idx++) {

                        if (idx < len - i - 1)
                            newTmp[idx] = tmp[idx];
                        else {
                            if (len - i - 1 < len)
                                newTmp[idx] = tmp[idx + 1];
                        }
                    }
                    tmp = newTmp;

                } else if (len - 1 - i >= 0 && tmp[i] == tmp[len - 1 - i]) {
                    char[] newTmp = new char[tmp.length - 1];
                    for (int idx = 0; idx < len; idx++) {

                        if (idx < i)
                            newTmp[idx] = tmp[idx];
                        else {
                            if (idx + 1 < len)
                                newTmp[idx] = tmp[idx + 1];
                        }
                    }
                    tmp = newTmp;
                }
                cnt++;
            }
            if (cnt == 2)
                return 2;
        }
        if (cnt == 0)
            return 0;
        else
            return 1;
    }
}