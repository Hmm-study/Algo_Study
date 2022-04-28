package 프로그래머스;

public class 문자열압축 {

    class Solution {
        public int solution(String s) {
            int answer = s.length();

            for(int i = 1; i <= s.length() / 2; i++) {
                String str = s.substring(0, i);
                String cur = "";
                int cnt = 1;
                StringBuilder sb = new StringBuilder();

                for(int j = i; j <= s.length(); j += i) {
                    if(j + i >= s.length()) {
                        cur = s.substring(j, s.length());
                    }
                    else {
                        cur = s.substring(j, j + i);
                    }

                    if(cur.equals(str)) {
                        cnt++;
                    }
                    else if(cnt == 1){
                        sb.append(str);
                        str = cur;
                    }
                    else {
                        sb.append(cnt).append(str);
                        str = cur;
                        cnt = 1;
                    }
                }
                if(i != str.length()) sb.append(str);

                answer = Math.min(answer, sb.toString().length());
            }

            return answer;
        }
    }
}