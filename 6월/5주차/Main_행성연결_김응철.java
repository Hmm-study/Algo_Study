import java.util.*;
import java.io.*;

public class Main_행성연결_16398 {
    static int[] parents;
    static Planet[] planets;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];

        for(int i=1;i<N+1;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j< N+1;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // input

        parents = makeSet(N);
        // 간선 갯수 = (N*(N-1)) / 2

        planets = new Planet[(N*(N-1)) / 2];

        int pNum = 0;
        for (int i=1;i<N;i++){
            for(int j=i+1;j<N+1;j++) {
                planets[pNum] = new Planet(i, j, map[i][j]);
                pNum++;
            }
        }

        Arrays.sort(planets);

        int cnt=0;
        long result=0;
        for(Planet planet : planets){
            if(union(planet.start,planet.end)){
                result += planet.cost;
                if(++cnt == N-1) break;
            }
        }

        System.out.println(result);

    }

    private static int[] makeSet(int size) {
        int[] arr = new int[size+1];
        for(int i=1;i<arr.length;i++){
            arr[i] = i;
        }
        return arr;
    }

    private static int find(int a){
        if(parents[a] == a){
            return a;
        }
        else{
            return parents[a] = find(parents[a]);
        }
    }

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;
    }

    public static class Planet implements Comparable<Planet>{
        int start,end,cost;

        public Planet(int start, int end, int cost){
            super();
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Planet o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
