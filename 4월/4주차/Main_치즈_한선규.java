package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_치즈_한선규 {

    private static int weight, height, time, cheeseCnt, remainCheeseCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        weight = Integer.parseInt(st.nextToken());
        int[][] map = new int[height][weight];

        for (int i = 0; i < height; i++) { // init map
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < weight; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cheeseCnt += (map[i][j] == 1) ? 1 : 0;
            }
        }

        while (cheeseCnt != 0) {
            time++;
            remainCheeseCnt = cheeseCnt;
            bfs(map);
        }

        System.out.println(time);
        System.out.println(remainCheeseCnt);
    }

    public static void bfs(int[][] map) {
        int[] dx = {-1, 0, 1, 0}; // 위, 오른쪽, 아래, 왼쪽
        int[] dy = {0, 1, 0, -1}; // 위, 오른쪽, 아래, 왼쪽
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[height][weight];
        q.offer(new int[] {0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int cx = q.peek()[0];
            int cy = q.poll()[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isInside(nx, ny)) { // 배열범위 안에 있는 외부공기에 대해서만 탐색
                    if (!visited[nx][ny] && map[nx][ny] == 1) { // 외부공기로부터 인접한 치즈 (녹아서 없어질 치즈)
                        map[nx][ny] = 0;
                        --cheeseCnt;
                        visited[nx][ny] = true;
                    } else if (!visited[nx][ny] && map[nx][ny] == 0) { // 외부공기는 계속해서 큐에 넣어준다.
                        q.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }

    public static boolean isInside(int x, int y) {
        return x >= 0 && x < height && y >= 0 && y < weight;
    }
}
