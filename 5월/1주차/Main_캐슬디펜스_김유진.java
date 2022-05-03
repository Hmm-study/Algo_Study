import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_캐슬디펜스_김유진
 * author 	    : 김유진
 * date		    : 2022-05-04
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-04	        김유진  		        최초 생성
 */
public class Main_캐슬디펜스_김유진 {
    static int N, M, D, max;
    static int[][] map, map2;
    static int[] archer;
    static boolean[] chk;
    static int[] dr = { 0, -1, 0 };
    static int[] dc = { -1, 0, 1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        map2 = new int[N][M];
        chk = new boolean[M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                map2[r][c] = map[r][c];
            }
        }

        max = 0;
        comb(0, 0);
        System.out.println(max);
    }
    static void comb(int dep, int start) {
        if (dep == 3) {

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    map[r][c] = map2[r][c];

                }
            }
            archer = new int[3];
            int cnt = 0;
            for (int i = 0; i < M; i++) {
                if (chk[i])
                    archer[cnt++] = i;
            }
            simul();
            return;
        }

        for (int i = start; i < M; i++) {
            chk[i] = true;
            comb(dep + 1, i + 1);
            chk[i] = false;
        }
    }

    static void simul() {
        int kill = 0;
        for (int i = 0; i < N; i++) {
            // killChk
            Pos[] killMark = new Pos[3];
            for (int idx = 0; idx < 3; idx++) {
                killMark[idx] = bfs(idx);
            }

            // kill
            for (int idx = 0; idx < 3; idx++) {
                if (killMark[idx].r == -1 && killMark[idx].c == -1)
                    continue;
                if (map[killMark[idx].r][killMark[idx].c] == 1) {
                    map[killMark[idx].r][killMark[idx].c] = 0;
                    kill++;
                }
            }

            // move
            for (int r = N - 1; r > i; r--) {
                for (int c = 0; c < M; c++) {
                    map[r][c] = map[r - 1][c];
                    map[r - 1][c] = 0;
                }
            }

        }
        max = Math.max(kill, max);
    }

    static Pos bfs(int i) {
        Queue<Pos> q = new LinkedList<Pos>();
        q.add(new Pos(N - 1, archer[i]));
        boolean[][] chkMap = new boolean[N][M];
        chkMap[N - 1][archer[i]] = true;
        int step = 0;
        while (step != D) {
            ++step;
            int qSize = q.size();
            for (int idx = 0; idx < qSize; idx++) {
                Pos tmp = q.poll();
                if (map[tmp.r][tmp.c] == 1)
                    return tmp;
                for (int d = 0; d < 3; d++) {
                    int nr = tmp.r + dr[d];
                    int nc = tmp.c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M || chkMap[nr][nc])
                        continue;

                    chkMap[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                }
            }
        }
        return new Pos(-1, -1);
    }

    static class Pos {
        int r, c;

        public Pos(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

}