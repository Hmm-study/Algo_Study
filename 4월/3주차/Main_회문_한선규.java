package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B17609_회문 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String s = br.readLine();
            sb.append(check(s, 0, s.length() - 1, 0)).append("\n");
        }

        System.out.println(sb);
    }

    private static int check(String s, int left, int right, int call) {
        if (call == 2)
            return call;

        int result = call;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                result = Math.min(check(s, left + 1, right, call + 1), check(s, left, right - 1, call + 1));
                break;
            }
        }

        return result;
    }
}
