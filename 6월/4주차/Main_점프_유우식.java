import java.util.Scanner;

public class BOJ_1890 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] map = new int[101][101];
        long[][] dp = new long[101][101]; //경로의 개수는 2^63-1보다 작거나 같다. => long 사용
        int n = sc.nextInt();

        dp[0][0] = 1; //기본값 설정

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //거르는 경우 : dp[i][j] == 0 , 마지막 도착지점인 경우
                if (dp[i][j] == 0 || (i == n - 1 && j == n - 1)) {
                    continue;
                }
                //이동할 수 있는 거리
                int dist = map[i][j];
                int down = dist + i;
                int right = dist + j;

                if (down < n) { //칸수보다는 작아야 칸 안에서 돌 수 있기 때문에
                    dp[down][j] += dp[i][j];
                }

                if (right < n) {
                    dp[i][right] += dp[i][j];
                }
            }
        }
        //출력
        System.out.println(dp[n - 1][n - 1]);
    }


}
