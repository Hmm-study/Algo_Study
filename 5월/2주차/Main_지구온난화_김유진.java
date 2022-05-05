import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_지구온난화_김유진
 * author 	    : 김유진
 * date		    : 2022-05-05
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-05	        김유진  		        최초 생성
 */
public class Main_지구온난화_김유진 {
    static int R, C;
    static char[][] map;
    static boolean[][] chk;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] mapRC = br.readLine().split(" ");

        R = Integer.parseInt(mapRC[0]);
        C = Integer.parseInt(mapRC[1]);

        map = new char[R][C];
        chk = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }

        int maxR = Integer.MIN_VALUE;
        int minR = Integer.MAX_VALUE;
        int maxC = Integer.MIN_VALUE;
        int minC = Integer.MAX_VALUE;


        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (isOut(nr, nc) || map[nr][nc] == '.') {
                        cnt++;
                        continue;
                    }


                }
                if (cnt < 3 && map[r][c] == 'X') {
                    chk[r][c] = true;
                    maxR = Math.max(maxR, r);
                    maxC = Math.max(maxC, c);
                    minR = Math.min(minR, r);
                    minC = Math.min(minC, c);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = minR; r <= maxR; r++) {
            for (int c = minC; c <= maxC; c++) {
                if (chk[r][c])
                    sb.append("X");
                else
                    sb.append(".");
            }
            sb.append("\n");
        }

        System.out.println(sb);


    }

    static boolean isOut(int r, int c) {
        if (r < 0 || r >= R || c < 0 || c >= C)
            return true;
        return false;
    }
}