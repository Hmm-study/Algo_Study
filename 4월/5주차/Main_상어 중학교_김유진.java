import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_������б�_������ {
	static int N, M;
	static Block[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("������б�"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// �� ũ�� N, �� �ִ� ���� M �Է�
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// �� ����
		map = new Block[N][N];

		// �� �ֱ�
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = new Block(Integer.parseInt(st.nextToken()), r, c);
			}
		}

		// �ùķ��̼� �� ���
		System.out.println(simul());

	}

	static int simul() {
		int sum = 0; // ��� ���� ��
		int score = 0; // �� ������ ����
		do {
//			System.out.println("����");
//			showMap(sum);
			score = findBigGroup(); // �� �׷��� ã�Ƽ� ������ ��ȯ
			sum += score; // ���� ���� ���
//			System.out.println("����");
//			showMap(sum);
			grav(); // �߷����� ������
			rotate(); // ȸ����
			grav(); // �߷����� ������
//			System.out.println("����");
//			showMap(sum);
		} while (score != 0); // ������ 0�̸� �����Ѵ�.

		return sum;
	}

	static void showMap(int sum) {
		System.out.println(sum + "--------------------------");
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == null)
					System.out.print(" X ");
				else if (map[r][c].num < 0)
					System.out.print(map[r][c].num + " ");
				else
					System.out.print(" " + map[r][c].num + " ");
			}
			System.out.println();
		}
	}

	static int findBigGroup() {
		int score = 0; // ����
		PriorityQueue<Group> pq = new PriorityQueue<>(); // �� �׷� �����ϱ� ���ؼ� PQ���

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == null || map[r][c].num == -1 || map[r][c].num == 0) // ���� ��� �ְų� 0 �̰ų� -1�̸� ����
					continue;
				Group tmp = new Group(new Block(map[r][c].num, map[r][c].r, map[r][c].c)); // �׷��� ����
				findBlock(tmp); // �׷��� �ְ� �ش� ������ �����ϴ� �׷��� tmp�� ������
				pq.add(tmp); // �׷��� ����
//				System.out.println(pq.size()+" ���� "+pq.peek());
			}
		}
//		System.out.println("ũ�� �ٽ� Ȯ�� : "+pq.size());

		Group bigGroup = pq.poll(); // ��� ��ġ�� �����ϴ� �׷��� �����ϰ� ���� ���� ū �׷��� �̾Ƴ���.
		if (bigGroup == null) // Ȥ�ó� �׷��� ���� ���� ��� (��� -1??)
			return 0; // �׳� 0�� ����
		if (bigGroup.blockCnt < 2) // ���� ū �׷��� ������ 2���� ���� ���
			return 0; // ����
//		System.out.println(bigGroup.toString());
		score = bigGroup.blockCnt; // ���� ����
		score *= score; // ����

		for (Block deleteBlock : bigGroup.a) { // �׷쿡 ���� ��� �� ����
			map[deleteBlock.r][deleteBlock.c] = null;
		}

		return score; // ���� ��ȯ
	}

	static void findBlock(Group tmp) {
		int standardNum = tmp.b.num; // �ش� �׷��� �� ����
		Queue<Block> q = new LinkedList<>();
		q.add(new Block(standardNum, tmp.b.r, tmp.b.c));
//		System.out.println(q.peek());
		boolean[][] chk = new boolean[N][N]; // �ߺ��� �� ã�� ����
		chk[tmp.b.r][tmp.b.c] = true; // ���� ��
//		System.out.println("------------");
		while (!q.isEmpty()) { // ��� ���� �ʴٸ� ��� Ž��
			Block bb = q.poll();
//			System.out.println(bb.toString());
			for (int d = 0; d < 4; d++) { // ���� ����(4��) Ž��
				int nr = bb.r + dr[d];
				int nc = bb.c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || chk[nr][nc] || map[nr][nc] == null) // ��ġ�� 0�̰ų� ��� ������ ����
					continue;
				chk[nr][nc] = true; // �ش� ��ġ Ž����
				int newBlockNum = map[nr][nc].num; // ���ο� ��ġ�� �� ����
				if (newBlockNum == -1) // ���� ���̸� ����
					continue; // ����
				else if (newBlockNum > 0 && newBlockNum != standardNum) // 0���ٴ� ũ���� ���ۺ��� ���� ���� �ƴ� ���
					continue; // ����
				q.add(new Block(newBlockNum, nr, nc)); // �������ų� ���� ���� ���ڰ� ���� ���� ���
			}
			tmp.a.add(bb); // �׷쿡 Ȯ�������� ���� ��
		}
		tmp.setStandardBlock();
		tmp.setBlockCnt(); // �� ���� Ȯ��
		tmp.setRainbowCnt(); // ������ �� ���� Ȯ��
//		System.out.println(tmp);
	}

	static void grav() {

		for (int c = 0; c < N; c++) {
			ArrayList<Block> col = new ArrayList<>(); // �ش� ���� ��� ����� ������ ArrayList
			for (int r = N - 1; r >= 0; r--) {
				if (map[r][c] == null)
					continue;
				col.add(map[r][c]); // ������� �ʴٸ� ��� ����
				map[r][c] = null; // ������ ���� ����
			}

			int nr = N; // ���� ��ġ
			for (Block tmp : col) { // ������ ������
				nr--; // ������� �Ʒ����� ���� ä�� ����
				if (tmp.num == -1) { // ���� ���� -1�� �������� ���
					nr = tmp.r; // �ش� ���� ��ġ�� ���̸� �ʱ�ȭ�ϰ�
					map[nr][c] = new Block(tmp.num, nr, c); // �ٽ� �ش� ��ġ�� ���� �ִ´�.
				} else { // -1�� �ƴ� ��쿡��
					map[nr][c] = new Block(tmp.num, nr, c); // �������� �״´�.
				}
			}
		}
	}

	static void rotate() {
		Block[][] newMap = new Block[N][N]; // ���ο� ������ �����.
		ArrayList<ArrayList<Integer>> tmp = new ArrayList<>(); // ArrayList ����
		for (int i = 0; i < N; i++) { // �� �ึ�� ������ ��� ����Ʈ �� ����
			tmp.add(new ArrayList<>());
		}
		for (int r = 0; r < N; r++) { // �� ����
			for (int c = 0; c < N; c++) { // �� ������ �����Ѵ�.
				if (map[r][c] == null) // ��� �ִٸ�
					tmp.get(r).add(-2); // �ӽ÷� -2�� ����
				else
					tmp.get(r).add(map[r][c].num); // �׷��� �ʴٸ� �ش� ���� ���ڸ� ����
			}
		}

		for (int c = 0; c < N; c++) { // ���ʺ���
			for (int r = N - 1; r >= 0; r--) {
				int num = tmp.get(c).get(N - 1 - r); //
				if (num == -2) // -2�� ����ִ�
					continue;
				newMap[r][c] = new Block(num, r, c); // �ƴϸ� ���� ����
			}
		}

		map = newMap; // ���ο� ���� �ٽ� ����
	}

	static class Block implements Comparable<Block> {
		int num, r, c;

		public Block(int num, int r, int c) {
			super();
			this.num = num;
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Block o) {
			int c0 = Integer.compare(o.num, this.num);
			if (c0 != 0)
				return c0;
			int c1 = Integer.compare(this.r, o.r);
			if (c1 != 0)
				return c1;

			return Integer.compare(this.c, o.c);
		}

		@Override
		public String toString() {
			return "Block [num=" + num + ", r=" + r + ", c=" + c + "]";
		}

	}

	static class Group implements Comparable<Group> {
		Block b; // ���� ��
		PriorityQueue<Block> a; // �� �׷쿡 ���ϴ� �� ����
		int blockCnt, rainbowCnt; // ���� ����, ������ �� ����

		public Group(Block b) {
			this.a = new PriorityQueue<>();
			this.blockCnt = 0;
			this.rainbowCnt = 0;
			this.b = b;
		}

		@Override
		public int compareTo(Group o) {
			int c1 = Integer.compare(o.blockCnt, this.blockCnt);
			if (c1 != 0)
				return c1;
			int c2 = Integer.compare(o.rainbowCnt, this.rainbowCnt);
			if (c2 != 0)
				return c2;
			int c3 = Integer.compare(o.b.r, this.b.r);
			if (c3 != 0)
				return c3;
			return Integer.compare(o.b.c, this.b.c);
		}

		public void setBlockCnt() {
			this.blockCnt = this.a.size();
		}

		public void setRainbowCnt() {
			int cnt = 0;
			for (Block tmp : this.a) {
				if (tmp.num == 0)
					cnt++;
			}
			this.rainbowCnt = cnt;
		}

		public void setStandardBlock() {
			this.b = a.peek();
		}

		@Override
		public String toString() {
			return "Group [b=" + b.num + "(" + b.r + ", " + b.c + ")" + ", " + " blockCnt=" + blockCnt + ", rainbowCnt="
					+ rainbowCnt + "]";
		}

	}
}
