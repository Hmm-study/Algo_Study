import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_상어중학교_김유진 {
	static int N, M;
	static Block[][] map;
	static int[] dr = { 0, 0, -1, 1 };
	static int[] dc = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("상어중학교"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 맵 크기 N, 블럭 최대 숫자 M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 맵 생성
		map = new Block[N][N];

		// 블럭 넣기
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = new Block(Integer.parseInt(st.nextToken()), r, c);
			}
		}

		// 시뮬레이션 및 출력
		System.out.println(simul());

	}

	static int simul() {
		int sum = 0; // 모든 점수 합
		int score = 0; // 매 스텝의 점수
		do {
//			System.out.println("시작");
//			showMap(sum);
			score = findBigGroup(); // 블럭 그룹을 찾아서 점수를 반환
			sum += score; // 누적 점수 계산
//			System.out.println("제거");
//			showMap(sum);
			grav(); // 중력으로 내려옴
			rotate(); // 회전함
			grav(); // 중력으로 내려옴
//			System.out.println("정리");
//			showMap(sum);
		} while (score != 0); // 점수가 0이면 종료한다.

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
		int score = 0; // 점수
		PriorityQueue<Group> pq = new PriorityQueue<>(); // 블럭 그룹 정렬하기 위해서 PQ사용

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == null || map[r][c].num == -1 || map[r][c].num == 0) // 맵이 비어 있거나 0 이거나 -1이면 생략
					continue;
				Group tmp = new Group(new Block(map[r][c].num, map[r][c].r, map[r][c].c)); // 그룹의 시작
				findBlock(tmp); // 그룹을 넣고 해당 블럭으로 시작하는 그룹을 tmp에 저장함
				pq.add(tmp); // 그룹을 저장
//				System.out.println(pq.size()+" 있음 "+pq.peek());
			}
		}
//		System.out.println("크기 다시 확인 : "+pq.size());

		Group bigGroup = pq.poll(); // 모든 위치로 시작하는 그룹을 저장하고 그중 가장 큰 그룹을 뽑아낸다.
		if (bigGroup == null) // 혹시나 그룹이 전혀 없는 경우 (모두 -1??)
			return 0; // 그냥 0을 리턴
		if (bigGroup.blockCnt < 2) // 가장 큰 그룹의 개수가 2보다 작은 경우
			return 0; // 리턴
//		System.out.println(bigGroup.toString());
		score = bigGroup.blockCnt; // 블럭의 개수
		score *= score; // 제곱

		for (Block deleteBlock : bigGroup.a) { // 그룹에 속한 모든 블럭 제거
			map[deleteBlock.r][deleteBlock.c] = null;
		}

		return score; // 점수 반환
	}

	static void findBlock(Group tmp) {
		int standardNum = tmp.b.num; // 해당 그룹의 블럭 숫자
		Queue<Block> q = new LinkedList<>();
		q.add(new Block(standardNum, tmp.b.r, tmp.b.c));
//		System.out.println(q.peek());
		boolean[][] chk = new boolean[N][N]; // 중복된 블럭 찾기 생략
		chk[tmp.b.r][tmp.b.c] = true; // 시작 블럭
//		System.out.println("------------");
		while (!q.isEmpty()) { // 비어 있지 않다면 계속 탐색
			Block bb = q.poll();
//			System.out.println(bb.toString());
			for (int d = 0; d < 4; d++) { // 블럭의 인접(4방) 탐색
				int nr = bb.r + dr[d];
				int nc = bb.c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || chk[nr][nc] || map[nr][nc] == null) // 위치가 0이거나 비어 있으면 생략
					continue;
				chk[nr][nc] = true; // 해당 위치 탐색함
				int newBlockNum = map[nr][nc].num; // 새로운 위치의 블럭 숫자
				if (newBlockNum == -1) // 검은 블럭이면 생략
					continue; // 생략
				else if (newBlockNum > 0 && newBlockNum != standardNum) // 0보다는 크지만 시작블럭과 같은 블럭이 아닌 경우
					continue; // 생략
				q.add(new Block(newBlockNum, nr, nc)); // 무지개거나 시작 블럭과 숫자가 같은 블럭인 경우
			}
			tmp.a.add(bb); // 그룹에 확정적으로 들어가는 블럭
		}
		tmp.setStandardBlock();
		tmp.setBlockCnt(); // 블럭 숫자 확인
		tmp.setRainbowCnt(); // 무지개 블럭 숫자 확인
//		System.out.println(tmp);
	}

	static void grav() {

		for (int c = 0; c < N; c++) {
			ArrayList<Block> col = new ArrayList<>(); // 해당 열의 모든 블록을 저장할 ArrayList
			for (int r = N - 1; r >= 0; r--) {
				if (map[r][c] == null)
					continue;
				col.add(map[r][c]); // 비어있지 않다면 모두 저장
				map[r][c] = null; // 저장한 블럭은 제거
			}

			int nr = N; // 시작 위치
			for (Block tmp : col) { // 저장한 블럭들을
				nr--; // 순서대로 아래에서 부터 채워 넣음
				if (tmp.num == -1) { // 만약 블럭이 -1인 검은색인 경우
					nr = tmp.r; // 해당 블럭의 위치로 높이를 초기화하고
					map[nr][c] = new Block(tmp.num, nr, c); // 다시 해당 위치에 집어 넣는다.
				} else { // -1이 아닌 경우에는
					map[nr][c] = new Block(tmp.num, nr, c); // 차근차근 쌓는다.
				}
			}
		}
	}

	static void rotate() {
		Block[][] newMap = new Block[N][N]; // 새로운 지도를 만든다.
		ArrayList<ArrayList<Integer>> tmp = new ArrayList<>(); // ArrayList 생성
		for (int i = 0; i < N; i++) { // 각 행마다 저장할 어레이 리스트 또 생성
			tmp.add(new ArrayList<>());
		}
		for (int r = 0; r < N; r++) { // 각 행의
			for (int c = 0; c < N; c++) { // 열 순으로 저장한다.
				if (map[r][c] == null) // 비어 있다면
					tmp.get(r).add(-2); // 임시로 -2를 저장
				else
					tmp.get(r).add(map[r][c].num); // 그렇지 않다면 해당 블럭의 숫자를 저장
			}
		}

		for (int c = 0; c < N; c++) { // 왼쪽부터
			for (int r = N - 1; r >= 0; r--) {
				int num = tmp.get(c).get(N - 1 - r); //
				if (num == -2) // -2는 비어있는
					continue;
				newMap[r][c] = new Block(num, r, c); // 아니면 새로 생성
			}
		}

		map = newMap; // 새로운 맵을 다시 갱신
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
		Block b; // 시작 블럭
		PriorityQueue<Block> a; // 블럭 그룹에 속하는 블럭 저장
		int blockCnt, rainbowCnt; // 블럭의 개수, 무기재 블럭 개수

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
