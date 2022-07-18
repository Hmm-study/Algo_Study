package gold3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B14621_나만_안되는_연애_ans {
	static int N, M;
	static PriorityQueue<Edge> pq;
	static int[] heads;
	static boolean[] gender;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		init();

		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			if (st.nextToken().equals("M"))
				gender[n] = true;
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			if ((gender[a] && !gender[b]) || (!gender[a] && gender[b]))
				pq.add(new Edge(!gender[b] ? b : a, gender[a] ? a : b, d));
		}

		int cnt = 0;
		int ans = 0;
		while (!pq.isEmpty()) {
			Edge tmp = pq.poll();
			if (union(tmp.M, tmp.W)) {
				cnt++;
				ans += tmp.dis;
			}
			if (cnt == N - 1)
				break;
		}

		System.out.println(cnt == N - 1 ? ans : -1);

	}

	static boolean union(int a, int b) {
		int ahead = find(a);
		int bhead = find(b);
		if (ahead == bhead)
			return false;
		heads[bhead] = ahead;
		return true;
	}

	static int find(int a) {
		if (a == heads[a])
			return a;
		return heads[a] = find(heads[a]);
	}

	static void init() {
		heads = new int[N + 1];
		gender = new boolean[N + 1];
		pq = new PriorityQueue<>((Edge e1, Edge e2) -> Integer.compare(e1.dis, e2.dis));
		for (int i = 1; i <= N; i++) {
			heads[i] = i;
		}
	}

	static class Edge {
		int W, M, dis;

		public Edge(int w, int m, int dis) {
			super();
			W = w;
			M = m;
			this.dis = dis;
		}

	}
}
