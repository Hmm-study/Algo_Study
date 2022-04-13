package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B9046_복호화 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            sb.append(solution(str.replace(" ", ""))).append("\n");
        }

        System.out.println(sb);

        br.close();
    }

    private static String solution(String s) {
        int len = s.length();
        Map<Character, Integer> hm = new HashMap<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (!hm.containsKey(c)) {
                hm.put(c, 1);
            } else {
                hm.put(c, hm.get(c) + 1);
            }
        }

        List<Character> keySet = new ArrayList<>(hm.keySet());
        if (keySet.size() == 1)
            return keySet.get(0)+"";
        keySet.sort((key1, key2) -> hm.get(key2).compareTo(hm.get(key1)));

        int firstChar = hm.get(keySet.get(0));
        int secondChar = hm.get(keySet.get(1));

        return (firstChar == secondChar) ? "?" : keySet.get(0)+"";
    }
}
