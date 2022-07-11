package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1774_우주신과의교감 {
    static int parent[];
    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Point> list = new ArrayList<Point>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()," ");
            double x = Integer.parseInt(st.nextToken());
            double y = Integer.parseInt(st.nextToken());
            list.add(new Point(x,y));
        }

        ArrayList<Edge> edge = new ArrayList<Edge>();
        for (int i = 0; i < N; i++) {
            double xx = list.get(i).x;
            double yy = list.get(i).y;
            for (int j = 0; j < N; j++) {
                if(i==j) continue;
                double xxx = list.get(j).x;
                double yyy = list.get(j).y;
//                double ww = Math.abs(xx-xxx)+Math.abs(yy-yyy); --> 이건 왜? 빡치네
                double ww = Math.sqrt(Math.pow(xx - xxx, 2) + Math.pow(yy - yyy, 2));
                edge.add(new Edge(i,j,ww));

            }
        }
        Collections.sort(edge);
        parent = new int[N];
        for (int i = 1; i < N; i++) {
            parent[i] =i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int X = Integer.parseInt(st.nextToken())-1;
            int Y = Integer.parseInt(st.nextToken())-1;
            union(X,Y);
        }


        double result = 0;

        for (int i = 0; i < edge.size(); i++) {
            Edge EE = edge.get(i);
            if(find(EE.start)!=find(EE.end)){

                result +=EE.weight;

                union(EE.start,EE.end);

            }

        }


        System.out.printf(String.format("%.2f",result));






    }

    private static int find(int x) {
        if(x==parent[x]){
         return x;
        }

        return parent[x]=find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x!=y){
            parent[y]=x;
        }
    }

    private static class Point {
        double x,y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double weight;

        public Edge(int start, int end, double weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            if(weight<o.weight){
                return -1;
            }
            return 1;
        }
    }
}
