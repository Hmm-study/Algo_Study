package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 크루스칼로 풀기

public class Main_1647_도시분할계획2 {
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<edge> list = new ArrayList<edge>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) ;
            int y = Integer.parseInt(st.nextToken()) ;
            int C = Integer.parseInt(st.nextToken());
            list.add(new edge(x, y, C));


        }
        // 오름 차순으로 정렬
        Collections.sort(list);

        parent = new int[N+1];

        for (int i = 1; i <= N ; i++) {
            parent[i] = i;
        }

        int result = 0;
        int big = 0;
        for (int i = 0; i < list.size(); i++) {
            edge EE = list.get(i);
            if(find(EE.x)!= find(EE.y)){

                result+= EE.c;

                union(EE.x, EE.y);

                big = EE.c;
            }
        }

        System.out.println(result-big);

    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x != y) parent[y] =x;
    }

    private static int find(int x) {
        if(x == parent[x]){
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static class edge implements Comparable<edge>{
        int x;
        int y;
        int c;

        public edge(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        // 비용기준으로 오름차순!
        @Override
        public int compareTo(edge o) {
            return c-o.c;
        }
    }
}
