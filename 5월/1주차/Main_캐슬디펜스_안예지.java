import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17135_캐슬디펜스2 {
    static int N,M,D; // 행, 열, 거리 제한
    static int map[][],copyMap[][], archery[];
    static int da[][]={{0,-1,0},{-1,0,1}}; // 좌 상 우
    static int result=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        N= Integer.parseInt(st.nextToken());
        M= Integer.parseInt(st.nextToken());
        D= Integer.parseInt(st.nextToken());

        map = new int[N+1][M];
        copyMap = new int[N+1][M];
        archery = new int[3];

        for (int i = 0; i < N; i++) {
            st= new StringTokenizer(br.readLine()," ");
            for (int j = 0; j < M; j++) {
                map[i][j]=Integer.parseInt(st.nextToken());
//                System.out.print(map[i][j]+" ");

            }
//            System.out.println();
        }

        Archer(0,0); // 궁수 자리 배치 메소드

        System.out.println(result);

    }

    private static void Archer(int cnt, int idx) {

        if(cnt==3) {
//            System.out.println(Arrays.toString(archery));
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    copyMap[i][j]= map[i][j];
                }
            }
            run();
            return;
        }

        for (int i = idx; i < M; i++) {
            archery[cnt]=i;

            Archer(cnt+1, i+1);


        }
    }

    private static void run() {

        Queue<Point> qu = new LinkedList<>();
        int cnt =0;
        int turn=0;
        boolean check[][]= new boolean[N][M];
        while (turn<=N-1){

            for (int t = 0; t < 3; t++) {
                int x= N-turn;
                int y = archery[t];

                int min = Integer.MAX_VALUE;
                int X =-1;
                int Y =-1;

                for (int i = 0; i <= x-1; i++) {
                    for (int j = 0; j < M; j++) {
                        if(copyMap[i][j]==1){
                            int dd= Math.abs(x-i)+ Math.abs(y-j);

                            if(dd<=D){
                                if(dd<min){
                                    min= dd;
                                    X=i;
                                    Y=j;
                                }else if(dd == min){
                                    if(Y>j){
                                        X=i;
                                        Y=j;
                                    }
                                }
                            }
                        }
                    }
                }
                if(X!=-1 && Y!=-1 && !check[X][Y]){
                    qu.add(new Point(X,Y));
                    check[X][Y]=true;
                }


            }

            while (!qu.isEmpty()){
                Point P= qu.poll();

                int xx= P.x;
                int yy= P.y;

                copyMap[xx][yy]=0;
                cnt++;
            }
//            System.out.println("------"+turn+"-------");
//            for (int i = 0; i <N ; i++) {
//                System.out.println(Arrays.toString(copyMap[i]));
//            }
//            System.out.println(cnt);
//            System.out.println(Arrays.toString(archery));
//            System.out.println();
            turn++;

        }
        result= Math.max(cnt,result);


    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}


