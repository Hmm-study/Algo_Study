import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_복호화_김유진 {
    static int[] alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        for (int i = 0; i < num; i++) {
            alpha = new int[26];
            char[] line = br.readLine().toCharArray();
            for (char ch : line) {
                if(ch != ' ')
                    alpha[ch - 'a']++;
            }
            System.out.println(chkMax());
        }
    }

    static public Character chkMax() {
        int max = 0;
        int idx = 0;
        for (int i = 0; i < 26; i++) {
            if (max < alpha[i]) {
                max = alpha[i];
                idx = i;
            }

        }

        int cnt = 0;
        for (int tmp : alpha) {
            if (tmp == max)
                cnt++;
        }
        if (cnt == 1)
            return (char)('a' + idx);
        else
            return '?';
    }
}
