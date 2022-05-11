package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 아기상어_16236 {

    static int N;
    static int[][] map;
    static int sharkSize = 2;
    static int turn = 0;
    static int eat_num = 0;

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        int startx = 0, starty = 0;

        for (int i=0; i<N; i++) {
            String line = bf.readLine();
            for (int j=0; j<N; j++) {
                map[i][j] = line.charAt(j*2) - 48;
                if (map[i][j] == 9) {
                    startx = j;
                    starty = i;
                    map[i][j] = 0;
                }

            }
        } 

        boolean endflag = true;

        while (true) {
            bfs(startx, starty);

            for (int i=0; i<N; i++) {
                for (int j=0; j<N; j++) {
                    if (map[i][j] < 0) {
                        if (endflag == true) {
                            eat_num++;
                            map[i][j] = 0;
                            endflag = false;
                            startx = j;
                            starty = i;
                        }
                        else {
                            map[i][j] *= -1;
                        }
                    }
                }
            }

            if (eat_num == sharkSize) {
                sharkSize++;
                eat_num = 0;
            }

            if (endflag)
                break;
            else
                endflag = true;
        }

        System.out.println(turn);

    }

    private static void bfs(int startx, int starty) {
        boolean[][] visited = new boolean[N][N];
        Queue <int[]> q = new LinkedList<int[]>();
        q.offer(new int[] {startx, starty, 0});

        boolean stopflag = false;
        int stopdist = Integer.MAX_VALUE;

        while(!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];
            int dist = p[2];

            if (dist > stopdist) {
                break;
            }

            if (map[y][x] != 0 && map[y][x] < sharkSize) {
                map[y][x] *= -1;
                stopdist = dist;
                stopflag = true;
            }

            else if ((dist <= stopdist) && !stopflag){
                for (int d=0; d<4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[ny][nx]) {
                        if (map[ny][nx] <= sharkSize) {
                            q.offer(new int[] {nx, ny, dist+1});
                            visited[ny][nx] = true;
                        }
                    }
                }
            }
        }

        if (stopflag)
            turn += stopdist;

    }
}