package may.oneweeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_좌표정렬하기2_한선규 {

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return this.x + " " + this.y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.y == o.y)
                return this.x - o.x;
            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pq.add(new Point(x, y));
        }

//        while (!pq.isEmpty()) {
//            System.out.println(pq.poll());
//        }

//        pq.forEach(System.out::println); // 알아본 결과 PQ는 stream 또는 iteratore 를 사용해서 출력하면 우선순위가 아닌 값을 넣은 순서대로 값이 나온다.
        // 해결방안 #1. 스트림에서 Queue 의 poll() 메서드를 사용하도록 수정
//        Stream.generate(pq::poll)
//                .limit(pq.size())
//                .forEach(System.out::println);
        // 해결방안 #2. 스트림에서 다시 정렬 (방안1 보다는 시간이 조금 더 걸림. 대부분 큰 차이는 없음)
        pq.stream().sorted()
                .forEach(System.out::println);
    }
}
