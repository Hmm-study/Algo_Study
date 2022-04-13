package programmers;

import java.util.*;
import java.util.stream.IntStream;

public class P92334_신고결과받기 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"muzi", "frodo", "apeach", "neo"}, new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"}, 2))); // [2, 1, 1, 0]
        System.out.println(Arrays.toString(solution(new String[]{"con", "ryan"}, new String[]{"ryan con", "ryan con", "ryan con", "ryan con"}, 3))); // [0, 0]
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, HashSet<String>> map = new HashMap<>();

        for (String name : id_list) { // map init
            map.put(name, new HashSet<>());
        }

        // 한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다. -> Set 으로 중복제거
        for (String s : report) { // 신고결과 기록
            String[] arr = s.split(" ");
            map.get(arr[1]).add(arr[0]);
        }

        for (String name : id_list) {
            HashSet<String> reports = map.get(name); // name 을 신고한 reports 목록 get
            if (reports.size() >= k) {
                for (String s : reports) {
                    IntStream.range(0, id_list.length)
                            .filter(i -> id_list[i].equals(s))
                            .forEach(i -> answer[i]++);
                }
            }
        }

        return answer;
    }
}
