import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int R, C;
	static int[][] map;
	static int time, remain;
	static boolean[][] visit;

	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		R = sc.nextInt();
		C = sc.nextInt();

		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
			}
		} // end input

		remain = 0;
		time = 0;
		while (true) {
			int tmp = check();

			if (tmp == 0)
				break;

			remain = tmp;
			bfs();
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					map[i][j]= map[i][j] ==2?0:map[i][j];
				}
			}
			time++;
		}
		System.out.println(time);
		System.out.println(remain);
	}

	private static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		visit = new boolean[R][C];

		queue.add(new Point(0, 0));
		visit[0][0] = true;

		while (!queue.isEmpty()) {
			Point now = queue.poll(); // 현재 이동한 칸(공기)  큐에 저장

			for (int d = 0; d < 4; d++) {
				int ni = now.i + di[d]; // 다음 공기로 이동 
				int nj = now.j + dj[d];
				
				
				if (ni<0|| ni>=R || nj<0 || nj>=C || visit[ni][nj]) continue; // 공기가 범위 내에 있거나 방문했던적이 있는지  확인 
			
				if(map[ni][nj]==0) {
					queue.add(new Point (ni,nj));
					visit[ni][nj]= true;
				
				}else if (map[ni][nj]==1) {
					map[ni][nj]=2;
					
				}
			
			}

		}
	}

	static int check() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				cnt += map[i][j];
			}
		}
		return cnt;
	}

	static class Point {

		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}

}