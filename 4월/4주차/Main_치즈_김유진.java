import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_치즈_김유진 {
    static int[][] map;
    static boolean[][] chk;
    static int M, N, time, num;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        chk = new boolean[M][N];
        int cnt = 0;
        // map 저장
        for (int r = 0; r < M; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1)
                    cnt++;
            }
        }

        simul(cnt);

        System.out.println(time);
        System.out.println(num);

    }

    static void simul(int startCnt) {
        int cnt = startCnt;
        while (cnt > 0) {
            Queue<Pos> q = new LinkedList<>();
            q.add(new Pos(0, 0));
            chk = new boolean[M][N];
            chk[0][0] = true;
            Queue<Pos> cq = new LinkedList<>();
            // bfs로 외각을 탐색해서 외부 공기와 접촉한 치즈를 cq에 저장한다.

            while (!q.isEmpty()) {
                Pos tmp = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = tmp.r + dr[d];
                    int nc = tmp.c + dc[d];
                    if (chkMap(nr, nc))
                        continue;

                    if (map[nr][nc] == 0) {
                        chk[nr][nc] = true;
                        q.add(new Pos(nr, nc));
                    } else {
                        chk[nr][nc] = true;
                        cq.add(new Pos(nr, nc));
                    }

                }
            }

            time++;
            num = cnt;
            // 녹을 치즈 개수 차감
            cnt -= cq.size();
            // 치즈 녹는 과정
            while (!cq.isEmpty()) {
                Pos tmp = cq.poll();
                map[tmp.r][tmp.c] = 0;
            }

        }

    }

    static boolean chkMap(int r, int c) {
        if (r < 0 || r >= M || c < 0 || c >= N || chk[r][c])
            return true;
        return false;
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
