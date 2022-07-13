import java.util.*;
import java.io.*;

public class Main_우주신과의교감크루스칼_1774 {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        double[][] map = new double[N][N];
        double minV[] = new double[N];
        Arrays.fill(minV, Double.MAX_VALUE);

        double result = 0.0;

        int[][] linkedEdges = new int[M][2]; /// 연결된 간선정보 받아오는 배열
        Point[] points = new Point[N]; // 좌표 정보 받아오는 배열

        Edge[] edges = new Edge[(N * (N - 1)) / 2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            linkedEdges[i][0] = Integer.parseInt(st.nextToken()) - 1;
            linkedEdges[i][1] = Integer.parseInt(st.nextToken()) - 1;

        } // input

        parents = makeSet(N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = Math.sqrt(Math.pow(points[i].x - points[j].x, 2) + Math.pow(points[i].y - points[j].y, 2));
            }
        } // 배열에 전체 노드와 간선 정보 입력

        int num = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                edges[num] = new Edge(i, j, map[i][j]);
                num++;
            }
        } // 배열에 전체 노드와 간선 정보 입력

        Arrays.sort(edges);

//        for(Edge e : edges){
//            System.out.println(e.start + ", " + e.end + ", " + e.cost);
//        }

        for(int i=0;i<M;i++){
            union(linkedEdges[i][0],linkedEdges[i][1]);
        }

        int cnt = 0;
        for (Edge e : edges) {
            if (union(e.start, e.end)) {
                result += e.cost;
                if (++cnt == N - 1 - M)
                    break;
            }
        }

        System.out.println(String.format("%.2f", result));
    }

    public static int[] makeSet(int N) {
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i;
        }
        return arr;
    }

    public static int find(int a) {
        if (parents[a] == a)
            return a;
        else
            return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }

    public static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int start, end;
        double cost;

        public Edge(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

}
