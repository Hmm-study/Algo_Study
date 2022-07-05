import java.util.*;
import java.io.*;

public class Main_도시건설_21924 {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = make(N);
        Node[] nodes = new Node[M];

        long allCost=0;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
            allCost+= nodes[i].edge;
        }

        Arrays.sort(nodes);

        int cnt = 0;
        long result = 0;
        for(Node n : nodes){
            if(union(parents[n.start],parents[n.end])){
                result += n.edge;
                cnt++;
                if(cnt == N-1)
                    break;
            }
        }
        if(cnt != N-1){
            System.out.println(-1);
        }else{
            System.out.println(allCost - result);
        }

    }

    public static int[] make(int N) {
        int[] tmp = new int[N];

        for(int i=0;i<N;i++){
            tmp[i] = i;
        }
        return tmp;
    }

    public static int find(int a){
        if(parents[a] ==a)
            return a;
        else
            return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)
            return false;

        parents[bRoot] = aRoot;
        return true;

    }


    public static class Node implements Comparable<Node>{
        int start,end,edge;
        public Node(int start, int end, int edge){
            this.start = start;
            this.end = end;
            this.edge = edge;
        }

        @Override
        public int compareTo(Node o) {
            return this.edge - o.edge;
        }
    }

}
