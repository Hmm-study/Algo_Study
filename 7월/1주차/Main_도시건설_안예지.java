package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_21924_도시건설 {
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<edge> list = new ArrayList<edge>();

        parent= new int[N+1];
        long sum =0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()," ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                list.add(new edge(x,y,c));
                sum+=c;

        }
        Collections.sort(list);

        for (int i = 1; i <= N; i++) {
            parent[i] =i;
        }

        long result =0;
        for (int i = 0; i < list.size(); i++) {

            edge EE = list.get(i);

            if(find(EE.x)!= find(EE.y)){
                result+= EE.c;
                uinon(EE.x,EE.y);


            }
        }

        boolean check = false;
        for (int i = 1; i <N ; i++) {
            if(find(i)!=find(i+1)){
                check =true;
            }
        }

        if(check){
            System.out.println("-1");
        }else{
            System.out.println(sum-result);
        }
    }

    private static void uinon(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y) parent[y]=x;

    }

    static int find(int x){
        if(x == parent[x]){
            return x;
        }

        return parent[x]= find(parent[x]);
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


        @Override
        public int compareTo(edge o) {
            return c-o.c;
        }
    }
}
