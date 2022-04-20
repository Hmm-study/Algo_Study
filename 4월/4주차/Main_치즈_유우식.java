import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


class Loc {
    int n;
    int m;

    Loc(int n, int m) {
        this.n = n;
        this.m = m;
    }
}

public class BOJ_2636 {
    static int count = 0;
    static int cheese = 0;
    static int time = 0;
    static int[][] map;
    static int N, M;
    static boolean[][] visited;
    static int[] di = { -1, 1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1)
                    cheese++;
            }
        }
        while (cheese != 0) {
            time++;
            count = cheese;
            solve();
        }
        System.out.println(time);
        System.out.println(count);

    }

    static void solve() {
        Queue<Loc> que = new LinkedList<>();
        que.offer(new Loc(0, 0));
        visited = new boolean[N][M];
        visited[0][0] = true;

        while (!que.isEmpty()) {
            Loc l = que.poll();
            int[] tmp = new int[2];
            tmp[0] = l.n;
            tmp[1] = l.m;

            for (int i = 0; i < 4; i++) {
                int ni = tmp[0] + di[i];
                int nj = tmp[1] + dj[i];

                if (ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj])
                    continue;

                if (map[ni][nj] == 1) {
                    cheese--;
                    map[ni][nj] = 0;
                } else if (map[ni][nj] == 0) {
                    que.offer(new Loc(ni, nj));
                }

                visited[ni][nj] = true;
            }
        }

    }
}