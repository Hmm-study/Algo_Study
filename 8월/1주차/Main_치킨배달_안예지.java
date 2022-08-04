package mon8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15686_치킨배달 {
    static int N,M;
    static ArrayList<Point> chicken = new ArrayList<Point>();
    static ArrayList<Point> home = new ArrayList<Point>();
    static int result =Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        int map[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1)home.add(new Point(i,j));
                else if(map[i][j]==2) chicken.add(new Point(i,j));
            }
        }

        com(0,0);

        System.out.println(result);

    }

    static ArrayList<Point> temp = new ArrayList<>();
    static void com(int idx, int cnt){
        if(cnt==M){
            int sum = 0;
            for (int i = 0; i < home.size(); i++) {
                int HX = home.get(i).x;
                int HY = home.get(i).y;
                int len = Integer.MAX_VALUE;
                for (int j = 0; j < temp.size(); j++) {
                    int CX = temp.get(j).x;
                    int CY = temp.get(j).y;
                    int ccc = Math.abs(HX-CX)+ Math.abs(HY-CY);
                    if(len>ccc){
                        len = ccc;
                    }
                }

                sum+=len;
//                System.out.println(sum);
            }
            if(result>sum) result =sum;
            return;
        }

        for (int i = idx; i < chicken.size(); i++) {

            temp.add(chicken.get(i));
            com(i+1,cnt+1);
            temp.remove(cnt);
        }
    }

    public static  class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
