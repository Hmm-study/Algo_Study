package mon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5212_지구온난화 {
    static char map[][];
    static int da[][]={{-1,1,0,0},{0,0,1,-1}};
    static Queue<Point> qu = new LinkedList<>();
    static Queue<Point>  melt= new LinkedList<>();
    static int R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine()," ");

        R= Integer.parseInt(st.nextToken());
        C= Integer.parseInt(st.nextToken());

        map= new char[R][C];
        for (int i = 0; i < R; i++) {
            String ss= br.readLine();
            map[i]=ss.toCharArray();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
               if(map[i][j]=='X')
                qu.add(new Point(i,j));
            }
        }

        while (!qu.isEmpty()){
            Point P = qu.poll();

            int x= P.x;
            int y= P.y;
            int cnt=0;
            for (int i = 0; i < 4; i++) {
                int X= x+da[0][i];
                int Y= y+da[1][i];
                // 주변이 바다면 카운트 해줌
                //와,, 알고보니 범위 밖에도 바다로 둘러 싸여 있는거여따.. 힝
                if( X>=0 && Y>=0 && X<R && Y<C && map[X][Y]=='.'){
                    cnt++;
                } else if ( X<0 || Y<0 || X>=R || Y>=C) {
                    cnt++;
                }
            }

            if(cnt>=3){
                melt.add(new Point(x,y));
            }
        }


        while (!melt.isEmpty()){
            Point P = melt.poll();

            int x= P.x;
            int y= P.y;

            map[x][y]='.';
        }

//        for(int i=0 ; i<R; i++){
//            System.out.println(Arrays.toString(map[i]));
//        }

        int x1=R,x2=0,y1=C,y2=0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j]=='X'){
                    if (x1>i) x1=i;
                    if (x2<i) x2=i;
                    if(y1>j) y1=j;
                    if(y2<j) y2=j;
                }
            }
        }

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
