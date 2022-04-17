package baekjoon;

import java.io.*;
import java.util.*;

public class Main_파일탐색기_한선규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        String[] files = new String[N];

        for (int n = 0; n < N; n++) { // input data
            files[n] = br.readLine();
        }

        Arrays.sort(files, new NaturalSortComparator());

        for (String file : files) {
            sb.append(file).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}

class NaturalSortComparator implements Comparator<String> {

    @Override
    public int compare(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        int i = 0, j = 0;

        for (; i < len1 && j < len2; i++, j++) { // 각 파일명 맨 앞의 문자부터 순차적으로 비교
            char c1 = s1.charAt(i); // s1 의 i 번째 문자
            char c2 = s2.charAt(j); // s2 의 j 번째 문자

            boolean isNum1 = isNum(c1); // c1 이 숫자열인지 판별된 boolean 변수(true : 숫자, false : 문자)
            boolean isNum2 = isNum(c2); // c2 가 숫자열인지 판별된 boolean 변수(true : 숫자, false : 문자)

            // 두 문자모두 숫자인 경우
            if (isNum1 && isNum2) {
                int zeroCnt1 = 0, zeroCnt2 = 0;

                while (i < len1 && s1.charAt(i) == '0') { // s1 의 숫자열 단위에서 앞에 0이 몇개인지 개수 카운팅
                    zeroCnt1++;
                    i++;
                }
                while (j < len2 && s2.charAt(j) == '0') { // s2 의 숫자열 단위에서 앞에 0이 몇개인지 개수 카운팅
                    zeroCnt2++;
                    j++;
                }

                // 0을 제외한 숫자열 단위의 크기를 비교
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();

                while (i < len1 && '0' <= s1.charAt(i) && s1.charAt(i) <= '9') {
                    sb1.append(s1.charAt(i));
                    i++;
                }

                while (j < len2 && '0' <= s2.charAt(j) && s2.charAt(j) <= '9') {
                    sb2.append(s2.charAt(j));
                    j++;
                }

                i--;
                j--;

                // 0을 제외했으므로 길이가 더 큰 숫자열을 포함한 파일명이 뒤로 정렬되어야 함
                if (sb1.length() > sb2.length())
                    return 1;
                if (sb1.length() < sb2.length())
                    return -1;

                // 0을 제외한 숫자의 길이가 같을 경우
                String n1 = sb1.toString();
                String n2 = sb2.toString();
                int nLen1 = n1.length();
                int nLen2 = n2.length();

                for (int k = 0, l = 0; k < nLen1 && l < nLen2; k++, l++) {
                    if (n1.charAt(k) > n2.charAt(l))
                        return 1;
                    if (n1.charAt(k) < n2.charAt(l))
                        return -1;
                }

                // 0을 제외한 숫자가 동일할 경우
                // 0의 개수로 크기를 판별
                if (zeroCnt1 != zeroCnt2)
                    return zeroCnt1 - zeroCnt2;
            }

            // 두 문자모두 알파벳 대소문자일 경우
            else if (!isNum1 && !isNum2) {
                c1 = s1.charAt(i);
                c2 = s2.charAt(j);

                if (c1 == c2)
                    continue;

                boolean isUpper1 = c1 - 'a' < 0;
                boolean isUpper2 = c2 - 'a' < 0;

                // 둘다 대문자 또는 소문자일 경우
                if (isUpper1 == isUpper2)
                    return c1 - c2;

                // c1 소문자, c2 대문자
                else if (!isUpper1 && isUpper2) {
                    if (c1 - 'a' == c2 - 'A') // 동일한 알파벳일 경우 대문자인 c2 가 먼저나와야 함
                        return 1;
                    return (c1 - 'a') - (c2 - 'A');
                }

                // c1 대문자, c2 소문자
                else {
                    if (c1 - 'A' == c2 - 'a') // 동일한 알파벳일 경우 대문자인 c1 이 먼저나와야 함
                        return -1;
                    return (c1 - 'A') - (c2 - 'a');
                }
            }

            // c1 문자, c2 숫자
            else if (!isNum1 && isNum2)
                return 1;

            // c1 숫자, c2 문자
            else
                return -1;
        }

        // 이 2가지 경우때문에 자꾸 런타임에러... 정렬조건 놓친경우에 대해서 sort 메서드 사용하면 런타임에러뜨는거... 이번에 처음 알았다...
        // 같은 문자인데 뒤에 다른 문자열이 붙는 경우에는 더 긴문자열이 뒤로 간다....
        if (len1 != i)
            return 1;
        if (len2 != j)
            return -1;

        return 0;
    }

    public boolean isNum(char c) {
        return '0' <= c && c <= '9';
    }
}
