import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B1774_우주신과의_교감_ans {
	static int N, M, cnt;
	static double ans;
	static Node[] nodes;
	static int[] heads;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		init();

		// 노드 기록
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine());
			nodes[n] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			if(union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))
				cnt++;
		}

		// 간선 생성
		for (int a = 1; a <= N; a++) {
			for (int b = a + 1; b <= N; b++) {
				if (heads[a] != heads[b])
					pq.add(new Edge(a, b, calcDis(nodes[a], nodes[b])));

			}
		}

		
		while (!pq.isEmpty()) {
			if (cnt == N-1)
				break;

			Edge tmp = pq.poll();
			if (union(tmp.a, tmp.b)) {
//				System.out.println(tmp.a + ", " + tmp.b + " 연결");
				cnt++;
				ans += tmp.dis;
			}

		}

		System.out.println(String.format("%.2f", ans));

	}

	static boolean union(int a, int b) {
		int aR = find(a);
		int bR = find(b);
		if (aR == bR)
			return false;
		heads[bR] = aR;
		return true;
	}

	static int find(int a) {
		if (a == heads[a])
			return a;
		return heads[a] = find(heads[a]);
	}

	static Double calcDis(Node a, Node b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	static void init() {
		nodes = new Node[N+1];
		heads = new int[N+1];
		pq = new PriorityQueue<Edge>();
		for (int n = 1; n <= N; n++) {
			heads[n] = n;
		}
	}

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static class Edge implements Comparable<Edge> {
		int a, b;
		double dis;

		public Edge(int a, int b, double dis) {
			this.a = a;
			this.b = b;
			this.dis = dis;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.dis, o.dis);
		}

	}
}
