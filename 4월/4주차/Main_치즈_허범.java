package 백준;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 치즈_2636 {

    public static int N, M, cheese, cnt, time;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    cheese++;
            }
        }
        while (cheese != 0) {
            time++;
            cnt = cheese;
            meltingCheese();
        }
        System.out.println(time);
        System.out.println(cnt);
    }

    public static void meltingCheese() {
        Queue<int[]> que = new LinkedList<int[]>();
        que.offer(new int[] { 0, 0 });
        visited = new boolean[N][M];
        visited[0][0] = true;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
                if (map[nx][ny] == 1) {
                    cheese--;
                    map[nx][ny] = 0;
                } else if (map[nx][ny] == 0) {
                    que.offer(new int[] { nx, ny });
                }
                visited[nx][ny] = true;
            }
        }
    }
}
