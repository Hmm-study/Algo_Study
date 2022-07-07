import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1647_도시_분할_계획_1 {
	static int N, M;
	static int[] head;
	static PriorityQueue<Edge> pq;

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
		}

		int cnt = 0;
		long sum = 0;

		for (int i = 0; i < M; i++) {
			Edge tmp = pq.poll();
			if (find(tmp.a) != find(tmp.b)) {
				cnt++;
				sum += tmp.dis;
				union(tmp.a, tmp.b);
			}

			if (cnt == N - 1) {
				sum -= tmp.dis;
				break;
			}
		}

		System.out.println(sum);

	}

	static void make() {
		head = new int[N + 1];
		for(int i = 0; i<=N;i++) {
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
		int a, b, dis;

		public Edge(int a, int b, int dis) {
			super();
			this.a = a;
			this.b = b;
			this.dis = dis;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.dis, o.dis);
		}

	}
}
