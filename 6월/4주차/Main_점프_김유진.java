import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: B1890_점프
 * author 	    : 김유진
 * date		    : 2022-06-21
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-06-21	        김유진  		        최초 생성
 */
public class B1890_점프 {
    static int N;
    static int[][] map;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        dp = new long[N][N];

        // 입력
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작시 1
        dp[0][0] = 1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // 마지막에 0이라서 중복으로 더해지는걸 방지함
                if (r == N - 1 && c == N - 1)
                    continue;
                // 이전에 움직인 기록이 있는 경우
                if (dp[r][c] != 0) {
                    // 아래로 움직인 경우
                    if (r + map[r][c] < N)
                        dp[r + map[r][c]][c] += dp[r][c];
                    // 오른쪽으로 움직인 경우
                    if (c + map[r][c] < N)
                        dp[r][c + map[r][c]] += dp[r][c];
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}