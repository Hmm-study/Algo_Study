package gold5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14503_로봇_청소기_ans {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Robot robot;
	static int N, M;
	static int[][] map;
	static boolean[][] chk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		chk = new boolean[N][M];
		st = new StringTokenizer(br.readLine());
		robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(simul());

	}

	static int simul() {
		int cnt = 0;

		while (true) {
			// 청소 하지 않은 경우 청소한다.
			if (!chk[robot.r][robot.c]) {
				chk[robot.r][robot.c] = true;
				cnt++;
			}

			// 다음에 청소할 방향을 찾는다.
			int dirCnt = 0;
			for (int i = 0; i < 4; i++) {
				leftDir();
				// 다음 위치
				int nr = robot.r + dr[robot.d];
				int nc = robot.c + dc[robot.d];
				// 다음위치가 비어있고 청소하지 않은 경우
				if (map[nr][nc] == 0 && !chk[nr][nc]) {
					robot.r = nr;
					robot.c = nc;
					break;
				}
				dirCnt++;
			}
			// 4바퀴 돌고
			if (dirCnt == 4) {
				int br = robot.r + dr[backDir()];
				int bc = robot.c + dc[backDir()];
				// 뒤가 벽인 경우
				if (map[br][bc] == 1)
					break; // 종료
				else {// 벽이 아니면 후진
					robot.r = br;
					robot.c = bc;
				}
			}

		}

		return cnt;
	}

	static void leftDir() {
		robot.d = robot.d - 1 < 0 ? 3 : robot.d - 1;
	}

	static int backDir() {
		switch (robot.d) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 0;
		case 3:
			return 1;
		}
		return -1;
	}

	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
