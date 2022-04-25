package april.fiveweeks;

public class Solution_문자열압축_한선규 {

    public int solution(String s) {
        int answer = s.length(); // 압축에 실패한 문자열의 길이는 처음 문자열의 길이와 동일함 (최소 길이를 찾는 것이므로 이렇게 초기화 하고 시작해도 됨)

        // 단위 개수 Loop
        for (int i = 1; i <= s.length() / 2; i++) { // unit의 크기가 문자열의 길이의 절반을 넘어선다면 더이상 탐색해볼 필요가 없음
            int pos = 0; // unit 의 처음 시작 위치
            int len = s.length(); // 압축하려는 문자열의 길이

            while (pos + i <= s.length()) {
                String unit = s.substring(pos, pos + i); // 현재 문자열로부터 길이를 하나씩 늘려가면서 unit 을 생성
                pos += i;

                int cnt = 0; // 반복되는 unit의 개수
                while (pos + i <= s.length()) { // unit 의 범위 만큼 반복
                    if (unit.equals(s.substring(pos, pos + i))) { // 이전에 자른 unit 과 현재 substring 한 문자열이 같다면 카운팅
                        cnt++;
                        pos += i;
                    } else { // 동일한 unit에 대해서 반복되지 않는다면 해당 unit 으로는 더이상 탐색해볼 필요가 없으므로 빠져나온다.
                        break;
                    }
                }

                if (cnt > 0) { // unit 에 카운팅된게 존재한다면 문자열을 압축한다.
                    len -= i * cnt; // 크기가 i인 unit 이 cnt 만큼 압축되므로 len 에서 빼준다.

                    // 문제의 조건에 따라 자리수에 맞게 다시 길이를 추가해준다.
                    if (cnt < 9) len += 1;
                    else if (cnt < 99) len += 2;
                    else if (cnt < 999) len += 3;
                    else len += 4;
                }
            }

            answer = Math.min(answer, len);
        }

        return answer;
    }
}
