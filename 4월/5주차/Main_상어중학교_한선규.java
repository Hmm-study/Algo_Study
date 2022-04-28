package april.fiveweeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_상어중학교_한선규 {

    private static int[][] map;
    private static int[] dr = {-1, 0, 1, 0}; // (행) 위, 오른쪽, 아래, 왼쪽
    private static int[] dc = {0, 1, 0, -1}; // (열) 위, 오른쪽, 아래, 왼쪽
    private static boolean[][] visited;
    private static PriorityQueue<BlockGroup> groupInfo;
    private static int N, M, score;

    static class BlockGroup implements Comparable<BlockGroup> {
        int r, c, size, rainbowCnt; // 차례대로 블록의 행, 열, 크기, 블록을 구성하는 무지개 블록의 개수
        ArrayList<int[]> blocks; // 기준블럭을 포함한 BlockGroup 을 구성하는 block list

        public BlockGroup(int r, int c, int size, int rainbowCnt, ArrayList<int[]> blocks) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.rainbowCnt = rainbowCnt;
            this.blocks = blocks;
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.size == o.size) { // #1. 블록의 크기가 동일하다면 무지개 블록의 개수로 내림차순
                if (this.rainbowCnt == o.rainbowCnt) { // #2. 무지개 블록의 개수가 동일하다면 기준블럭의 행 번호로 내림차순
                    if (this.r == o.r) // #3. 행 번호가 동일하다면 기준블럭의 열 번호로 내림차순
                        return o.c - this.c;
                    return o.r - this.r;
                }
                return o.rainbowCnt - this.rainbowCnt;
            }
            return o.size - this.size; // 기본적으로 블록의 크기로 내림차순 정렬
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 1 <= N <= 20, 격자 한 변의 크기
        M = Integer.parseInt(st.nextToken()); // 1 <= M <= 5, 색상의 개수
        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while (findBlockGroup()) { // 반복조건 : 블록그룹이 존재하는 지
            // #1. 블록 그룹화
            // -> findBlockGroup() 메서드를 통해서 블록그룹을 찾는 작업 진행
            // #2. 가장 큰 블록을 제거하면서 점수 획득
            removeMaxBlockGroup(groupInfo.poll());
            // #3. 격자에 중력 작용
            gravity();
            // #4. 격자가 90도 반시계 방향으로 회전
            map = rotate();
            // #5. 격자에 중력 작용
            gravity();
        }

        System.out.println(score);
    }

    private static boolean findBlockGroup() {
        visited = new boolean[N][N];
        groupInfo = new PriorityQueue<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] > 0 && !visited[r][c]) { // 일반 블록인 경우에만 bfs 탐색
                    bfs(r, c, new boolean[N][N]); // bfs 한번당 그룹 한개를 찾음
                }
            }
        }

        if (groupInfo.isEmpty())
            return false;
        return true;
    }

    private static void bfs(int r, int c, boolean[][] usedRainbow) {
        int blockColor = map[r][c]; // 지금 현재 기준블록의 색깔
        ArrayList<int[]> groups = new ArrayList<>();
        visited[r][c] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        groups.add(new int[] {r, c});
        int rainbowCnt = 0;

        while (!q.isEmpty()) {
            int cr = q.peek()[0];
            int cc = q.poll()[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (isInside(nr, nc) && !visited[nr][nc] && !usedRainbow[nr][nc]) {
                    if (map[nr][nc] == blockColor) { // 인접한 블록이 기준블록과 동일한 색의 일반 블록인 경우
                        q.offer(new int[] {nr, nc});
                        groups.add(new int[] {nr, nc});
                        visited[nr][nc] = true;
                    } else if (map[nr][nc] == 0) { // 인접한 블록이 무지개 블록인 경우
                        q.offer(new int[] {nr, nc});
                        groups.add(new int[] {nr, nc});
                        rainbowCnt++;
                        usedRainbow[nr][nc] = true;
                    }
                }
            }
        }

        // (r, c) 기준블록으로 부터 시작해서 완성된 그룹에 대한 정보가 리스트 groups 에 저장되어 있음.
        int size = groups.size();
        if (size >= 2) {
            groupInfo.add(new BlockGroup(r, c, size, rainbowCnt, groups));
        }
    }

    private static void removeMaxBlockGroup(BlockGroup blockGroup) {
        ArrayList<int[]> blocks = blockGroup.blocks;
        for (int[] block : blocks) {
            map[block[0]][block[1]] = -2;
        }

        score += Math.pow(blocks.size(), 2);
    }

    private static void gravity() {
        for (int c = 0; c < N; c++) { // 모든 열에 대해서 반복
            int bottom = N - 1;
            for (int r = N - 1; r >= 0; r--) { // 제일 밑의 행부터 위로 탐색
                if (map[r][c] == -2) // 블럭이 삭제되거나 이동한 경우는 패스
                    continue;
                if (map[r][c] == -1) // r행에서 검은색 블록을 만났다면
                    bottom = r - 1; // r-1 행을 바닥으로 설정
                else {
                    int blockColor = map[r][c];
                    map[r][c] = -2;
                    map[bottom--][c] = blockColor;
                }
            }
        }
    }

    private static int[][] rotate() {
        int[][] newMap = new int[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                newMap[N-c-1][r] = map[r][c];
            }
        }

        return newMap;
    }

    private static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
