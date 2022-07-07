import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B21924_도시_건설_1 {
	static int N, M;
	static long all;
	static PriorityQueue<Edge> pq;
	static int[] head;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		make();

		pq = new PriorityQueue<>();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int des1 = Integer.parseInt(st.nextToken());
			int des2 = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			pq.add(new Edge(des1, des2, dis));
			all += dis;
		}

		int cnt = 0;
		long sum = 0;
		for (int i = 0; i < M; i++) {
			Edge tmp = pq.poll();
			if (find(tmp.des1) != find(tmp.des2)) {
				cnt++;
				sum += tmp.dis;
				union(tmp.des1, tmp.des2);
			}

			if (cnt == N - 1)
				break;

		}
		if (cnt == N - 1)
			System.out.println(all - sum);
		else
			System.out.println(-1);
	}

	static void make() {
		head = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			head[i] = i;
		}
	}

	static int find(int a) {
		if (a == head[a])
			return a;
		return head[a] = find(head[a]);
	}

	static boolean union(int a, int b) {

		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;

		head[bRoot] = aRoot;
		return true;

	}

	static class Edge implements Comparable<Edge> {
		int des1, des2, dis;

		public Edge(int des1, int des2, int dis) {
			super();
			this.des1 = des1;
			this.des2 = des2;
			this.dis = dis;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.dis, o.dis);
		}

	}
}
