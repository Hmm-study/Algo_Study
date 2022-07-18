package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_14621_나만안되는연애 {
    static int parent[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        String sung [] = new String[N+1];
        parent = new int[N+1];
        ArrayList<Point> map = new ArrayList<Point>();

        st = new StringTokenizer(br.readLine()," ");
        for (int i = 1; i < N+1; i++) {
            sung[i] = st.nextToken();
            parent[i]=i;
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()," ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map.add(new Point(a,b,c));

        }

        Collections.sort(map);
//        for (int i = 0; i <map.size() ; i++) {
//            System.out.println(map.get(i).a+" "+map.get(i).b+" "+map.get(i).c);
//        }

        int result = 0;
        for (int i = 0; i < map.size(); i++) {

            Point PP = map.get(i);
            if(!sung[PP.a].equals(sung[PP.b])) {
                if (find(PP.a) != find(PP.b)) {

                    result+=PP.c;
                    union(PP.a,PP.b);
                }
            }
        }

        boolean check = false;
        for (int i = 1; i < N; i++) {
            if(find(i)!=find(i+1)) check = true;
        }

        if(check){
            System.out.println("-1");
        }else{
            System.out.println(result);
        }


        
    }
    static int find(int x){

        if(x==parent[x]){
            return x;
        }

        return parent[x]=find(parent[x]);
    }

    static void union(int a, int b){

        a = find(a);
        b = find(b);
        if(a!=b){

            parent[b]=a;

        }
    }

    static class Point implements Comparable<Point>{
        int a, b, c;

        public Point(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }


        @Override
        public int compareTo(Point o) {
            return c-o.c;
        }
    }
}
