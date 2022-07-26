import java.util.*;
import java.io.*;

public class Main_컨베이어벨트위의로봇_20055 {
    static int N, K, cnt = 1, zCnt = 0;
    static int[] A, robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N * 2];
        robot = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N * 2; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            rotation();
            move();
            put();

            if (zCnt >= K)
                break;
            cnt++;
        }

        System.out.println(cnt);
    }

    private static void put() {
        if (A[0] != 0) {
            robot[0] = 1;
            A[0] -= 1;
            if (A[0] == 0)
                zCnt++;
        }
    }

    private static void move() {
        for (int i = N-2;i>=0;i--) {
            if (robot[i] == 1 && robot[i + 1] != 1 && A[i + 1] != 0) {
                robot[i + 1] = 1;
                robot[i] = 0;
                A[i + 1] -= 1;
                if (A[i + 1] == 0)
                    zCnt++;
            }
        }

        if (robot[N - 1] == 1) {
            robot[N - 1] = 0;
        }

    }

    private static void rotation() {
        int tmp = 0;
        tmp = A[N * 2 - 1];
        for (int i = N * 2 - 1; i > 0; i--) {
            A[i] = A[i - 1];
        }
        A[0] = tmp;

        tmp = robot[N - 1];
        for (int i = N - 1; i > 0; i--) {
            robot[i] = robot[i - 1];
        }
        robot[0] = tmp;

        if (robot[N - 1] == 1) {
            robot[N - 1] = 0;
        }
    }
}
