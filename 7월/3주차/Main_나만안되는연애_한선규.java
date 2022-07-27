package baekjoon;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_나만안되는연애_한선규 {

    private static int N, M, res; // N: 학교의 수, M: 도로의 개수, res: 출력할 사심 경로
    private static char[] universities; // 남초/여초 대학교 종류 저장할 배열
    private static PriorityQueue<SelflessPath> paths; // 사심 경로 간선 목록 저장할 pq
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0; // 경로를 다이었는지 확인하기 위한 카운팅 변수

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        universities = new char[N + 1]; // indexing start -> 1

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            universities[i] = st.nextToken().charAt(0);
        }

        paths = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist)); // 거리 기준 오름차순 정렬조건을 가진 pq 생성

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            paths.add(new SelflessPath(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())
            ));
        }

        init(); // make parents

        while (!paths.isEmpty()) {
            SelflessPath path = paths.poll();

            if (find(path.src) != find(path.dest)) {
                if (universities[path.src] != universities[path.dest]) {
                    cnt++;
                    res += path.dist;
                    union(path.src, path.dest);
                }
            }
        }

        System.out.println(cnt == N - 1 ? res : -1);
        br.close();
    }

    private static void union(int src, int dest) {
        int x = parents[src];
        int y = parents[dest];

        parents[y] = x;
    }

    private static int find(int src) {
        if (src == parents[src]) {
            return src;
        }
        return parents[src] = find(parents[src]);
    }

    private static void init() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    static class SelflessPath { // 사심 경로 간선 정보를 가지는 객체 클래스
        int src;
        int dest;
        int dist;

        public SelflessPath(int u, int v, int d) {
            this.src = u;
            this.dest = v;
            this.dist = d;
        }
    }
}
