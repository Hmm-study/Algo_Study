import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_나만안되는연애_허범 {

    static int parent[];
    static ArrayList<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char uni[] = new char[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            uni[i] = st.nextToken().charAt(0);
        }
        
        edgeList = new ArrayList<>();
        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            edgeList.add(new Edge(u, v, d));
        }

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++)
            parent[i] = i;

        Collections.sort(edgeList, (e1, e2) -> e1.cost - e2.cost);

        int count = 0, ans = 0;

        for(int i = 0; i < edgeList.size(); i++){
            Edge edge = edgeList.get(i);

            if(find(edge.start) != find(edge.end)){
                if(uni[edge.start] != uni[edge.end]){
                    count++;
                    ans += edge.cost;

                    union(edge.start, edge.end);
                }
            }
        }
        if(count == N-1)
            System.out.println(ans);
        else
            System.out.println(-1);
    }

    public static int find(int x){
        if(x == parent[x])
            return x;

        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y){
        x = find(x);
        y = find(y);

        if( x != y){
            parent[y] = x;
        }
    }
}
class Edge{
    int start;
    int end;
    int cost;

    Edge(int start, int end, int cost){
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}

