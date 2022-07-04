import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.*;
public class Main_도시분할계획_1647 {
    static int[] parents;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        House[] village = new House[M];
        parents = makeSet(N);
        long result = 0;

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            village[i] = new House(Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(village);

//        for(House house : village){
//            System.out.println(house.start + "--"+house.end +"--"+house.road);
//        } // 입력 정렬 확인

        int cnt = 0;
        for(House h: village){
            if(union(h.start,h.end)){
                result+=h.road;
                cnt++;
                if(cnt == N-2)
                    break;
            }
        }

        System.out.println(result);
    }

    public static int[] makeSet(int N){
        int[] tmp = new int[N];

        for(int i=0;i<N;i++){
            tmp[i] = i;
        }

        return tmp;
    }

    public static int find(int a){
        if(parents[a] == a)
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


    public static class House implements Comparable<House>{
        int start,end,road;
        public House(int start,int end, int road){
            this.start = start;
            this.end = end;
            this.road = road;
        }

        @Override
        public int compareTo(House o) {
            return this.road - o.road;
        }
    }
}
