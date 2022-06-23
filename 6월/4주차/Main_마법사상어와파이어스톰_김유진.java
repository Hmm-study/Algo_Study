import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: B20058_마법사상어와파이어스톰
 * author 	    : 김유진
 * date		    : 2022-06-22
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-06-22	        김유진  		        최초 생성
 */
public class B20058_마법사상어와파이어스톰 {
    static int N, Q, len, totalCnt, maxGroupSize;
    static int[][] A, map;
    static int[] L;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0,};
    static boolean[][] chkMelt, chkmap;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 맵 사이즈
        Q = Integer.parseInt(st.nextToken()); // 마법 시전 횟수
        len = (int) Math.pow(2, N); // 맵 크기 구하기
        A = new int[len][len]; // 맵 셋팅
        // 맵 입력
        for (int r = 0; r < len; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < len; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 마법 저장
        st = new StringTokenizer(br.readLine());
        L = new int[Q];
        for (int idx = 0; idx < Q; idx++) {
            L[idx] = Integer.parseInt(st.nextToken());
        }

        simul(); // 마법 수행
        System.out.println(totalCnt); // 남아있는 얼음 합
        System.out.println(maxGroupSize); // 남아 있는 얼음 중 가장 큰 얼음 덩어리
    }

    static void simul() {
        // 마법 횟수 만큼 반복
        for (int q = 0; q < Q; q++) {
            map = new int[len][len]; // 새로운 상태 저장
            chkMelt = new boolean[len][len]; // 녹을 애들
            quard((int) Math.pow(2, L[q])); // 부분 사이즈로 분할

            for (int r = 0; r < len; r++) {
                for (int c = 0; c < len; c++) {
                    if (chkMelt[r][c]) // 녹을 애들이면 녹음
                        map[r][c]--;
                }
            }
            A = map;
        }

        cntIce(); // 얼음 개수
    }

    // 분할
    static void quard(int newLen) {

        for (int r = 0; r < len; r += newLen) {
            for (int c = 0; c < len; c += newLen) {
                rotate(r, c, newLen);
            }
        }

        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (isOut(nr, nc))
                        continue;
                    if (map[nr][nc] > 0)
                        cnt++;

                }

                if (cnt < 3 && map[r][c] != 0)
                    chkMelt[r][c] = true;
            }
        }
    }

    // 회전 (2^L 사이즈 부분 행렬 시계방향으로 회전)
    static void rotate(int mr, int mc, int half) {
        // 회전할 부분 행렬 저장
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        for (int i = 0; i < half; i++) {
            a.add(new ArrayList<>());
        }
        // 열별로 저장
        for (int r = mr; r < mr + half; r++) {
            for (int c = mc; c < mc + half; c++) {
                a.get(r - mr).add(A[r][c]);
            }
        }
        // 행별로 뱉어냄
        for (int c = mc + half - 1; c >= mc; c--) {
            for (int r = mr; r < mr + half; r++) {
                map[r][c] = a.get(half + mc - 1 - c).get(r - mr);
            }
        }
    }
    // 전체 얼음 개수 세기
    static void cntIce() {
        chkmap = new boolean[len][len]; // 중복 그룹 확인 방지
        for (int r = 0; r < len; r++) {
            for (int c = 0; c < len; c++) {
                totalCnt += A[r][c];
                if (A[r][c] != 0 && !chkmap[r][c])
                    findGroup(r, c);
            }
        }
    }
    // 그룹
    static void findGroup(int r, int c) {
        boolean[][] chk = new boolean[len][len];
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r, c));
        chk[r][c] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            Pos tmp = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = tmp.r + dr[d];
                int nc = tmp.c + dc[d];
                if (isOut(nr, nc) || chk[nr][nc] || A[nr][nc] == 0)
                    continue;

                chk[nr][nc] = true;
                chkmap[nr][nc] = true; // 중복 그룹 방지용 체크 한번더
                q.add(new Pos(nr, nc));
            }
            cnt++;
        }
        maxGroupSize = Math.max(cnt, maxGroupSize);

    }

    static boolean isOut(int r, int c) {
        if (r < 0 || r >= len || c < 0 || c >= len)
            return true;
        return false;
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