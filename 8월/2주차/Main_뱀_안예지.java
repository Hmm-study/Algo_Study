package mon8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 1이면 사과 2이면
public class Main_3190_뱀2 {
    static int N,NT;
    static int map[][];
    static int dir[][]= {{0,1},{1,0},{0,-1},{-1,0}}; //하 상  //우 좌
    static boolean check;
    static Deque<snake> deque = new ArrayDeque<>();
    static int result =0;
    static int dd;
    static Queue<ddir> qu = new LinkedList<>();
    static String ND;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N  = Integer.parseInt(br.readLine());

        map = new int[N][N];

        int K  = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1]=1;
        }

        int L = Integer.parseInt(br.readLine());
        //머리와 꼬리 위치 초기화
        deque.add(new snake(0,0));
        dd = 0;
        map[0][0] = 2;

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String C = st.nextToken();

            if(i==0) {
                NT = time;
                ND = C;
            }else{
                qu.add(new ddir(time, C));
            }
        }
            while(true) {
                if(NT == result){
                    if(ND.equals("L")){
                        dd -= 1;
                        if(dd==-1) dd=3;
                    }else{
                        dd+=1;
                        if(dd==4) dd=0;
                    }

                    if(!qu.isEmpty()){
                        ddir fj = qu.poll();
                        NT = fj.T;
                        ND = fj.ddd;
                    }
                }

                int X = deque.peekLast().x+ dir[dd][0];
                int Y = deque.peekLast().y + dir[dd][1];

                result++;
//                System.out.println(X+" "+Y+" NT: "+NT+" ND: "+ND+ " "+dd +" "+result);
                // 다음 위치가 벽이거나 자기 자신 부딪힘
                if( X < 0 || Y < 0 || X >= N || Y >= N || map[X][Y]==2){
                    check =true;
                    break;
                }else{
                    //사과가 있으면
                    if(map[X][Y]==1){
                        deque.add(new snake(X,Y));
                        map[X][Y]=2;

                    }else{
                        snake se = deque.pollFirst();
                        map[se.x][se.y]=0;
                        map[X][Y] =2;
                        deque.add(new snake(X,Y));

                    }
                }


            }
        System.out.println(result);



    }

    static class ddir {
        int T;
        String ddd;

        public ddir(int t, String ddd) {
            T = t;
            this.ddd = ddd;
        }
    }


    static class snake{
        int x;
        int y;

        public snake(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
