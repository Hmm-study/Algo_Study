import java.util.*;
import java.io.*;
public class Main_나만안되는연애_14621 {
    static int N,M;
    static char[] sex;
    static Edge[] edges;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        sex = new char[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<N+1;i++){
            sex[i] = st.nextToken().charAt(0);
        }

        edges = new Edge[M];

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(edges);

        makeSet();

        int cnt = 0;
        int result = 0;
        for (Edge e : edges) {
            if (sex[e.start] != sex[e.end] && union(e.start, e.end)) {
                result += e.len;
                if (++cnt == N - 1)
                    break;
            }
        }

        if(cnt == N-1)
            System.out.println(result);
        else
            System.out.println(-1);

    }

    private static void makeSet() {
        for(int i=0;i<N;i++){
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if(parents[a] == a)
            return a;
        else
            return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;

    }




    public static class Edge implements Comparable<Edge>{
        int start,end,len;

        public Edge(int start, int end, int len){
            this.start = start;
            this.end = end;
            this.len = len;
        }

        @Override
        public int compareTo(Edge o) {
            return len - o.len;
        }
    }
}
