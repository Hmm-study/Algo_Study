import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, cnt;
	static int[][] map;
	static Shark shark;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					shark = new Shark(r, c);
					map[r][c] = 0;
				}
			}
		}

		System.out.println(simul());

	}

	static int simul() {
		int total = 0;
		int cnt = 0;
		while (true) {
			cnt = isEat();
			if (cnt == 0)
				break;
			total += cnt;
		}

		return total;
	}

	static int isEat() {

		boolean[][] chk = new boolean[N][N];
		chk[shark.r][shark.c] = true;
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(shark.r, shark.c));
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		int step = 0;

		while (!q.isEmpty()) {
			int len = q.size();
			for (int idx = 0; idx < len; idx++) {
				Pos now = q.poll();
				for (int d = 0; d < 4; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];
					if (isOut(nr, nc) || chk[nr][nc] || map[nr][nc] > shark.size)
						continue;

					chk[nr][nc] = true;
					q.add(new Pos(nr, nc));
					if (map[nr][nc] < shark.size && map[nr][nc] > 0)
						pq.add(new Fish(nr, nc, step+1));

				}
			}
			step++;
			if (pq.size() != 0) {

				Fish fish = pq.poll();

				shark.eatFish(fish);

				return step;
			}
		}

		return 0;
	}

	static boolean isOut(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N)
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

	static class Fish implements Comparable<Fish> {
		int r, c, dis;

		public Fish(int r, int c, int dis) {
			this.dis = dis;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Fish o) {
			int con1 = Integer.compare(this.dis, o.dis);
			if (con1 != 0)
				return con1;
			int con2 = Integer.compare(this.r, o.r);
			if (con2 != 0)
				return con2;
			return Integer.compare(this.c, o.c);
		}

	}

	static class Shark {
		int size, num, r, c;

		public Shark(int r, int c) {
			this.size = 2;
			this.num = 0;
			this.r = r;
			this.c = c;
		}

		public void eatFish(Fish fish) {
			map[fish.r][fish.c] = 0;
			this.r = fish.r;
			this.c = fish.c;
			this.num++;
			if (this.num == this.size) {
				size++;
				num = 0;
			}

		}
	}
}
