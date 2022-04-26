package april.fiveweeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_상어초등학교_한선규 {

    private static int[][] classroom;
    private static int[] dr = {-1, 0, 1, 0}; // (행) 위, 오른쪽, 아래, 왼쪽
    private static int[] dc = {0, 1, 0, -1}; // (열) 위, 오른쪽, 아래, 왼쪽
    private static int N;
    private static Map<Integer, Student> hm = new HashMap<>();

    static class Student { // 자리가 배정된 학생에 대한 정보를 가지는 클래스
        int r, c; // 학생의 자리 좌표
        int[] friends; // 학생이 좋아하는 4명의 친구들 목록

        public Student(int r, int c, int[] friends) {
            this.r = r;
            this.c = c;
            this.friends = friends;
        }
    }

    static class Seat implements Comparable<Seat> { // 자리에 대한 정보를 가지는 클래스
        int r, c; // 자리의 좌표
        int friendCnt = 0; // 자리를 기준으로 좋아하는 학생의 수
        int emptyCnt = 0; // 자리를 기준으로 빈 자리의 수

        public Seat(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Seat o) {
            // #1. 첫번째 조건 : 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
            if (this.friendCnt != o.friendCnt)
                return o.friendCnt - this.friendCnt;
            else { // #2. 두번째 조건 : 좋아하는 학생이 인접한 칸의 개수가 같다면 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
                if (this.emptyCnt != o.emptyCnt)
                    return o.emptyCnt - this.emptyCnt;
                else {
                    // #3. 세번째 조건 : 행의 번호가 가장 작은 칸
                    if (this.r != o.r)
                        return this.r - o.r;
                }
            }
            return this.c - o.c; // #4. 네번째 조건 : 열의 번호가 가장 작은 칸
        }

        public void setFriendCnt(int num, int[] friends) { // 학생의 번호가 num 이고 자리가 (r,c) 일때 사방탐색을 통해서 인접한 칸에 좋아하는 학생이 몇명이 앉아있는지 Set
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nr = this.r + dr[d];
                int nc = this.c + dc[d];

                if (isInside(nr, nc)) {
                    boolean find = Arrays.stream(friends).anyMatch(friend -> classroom[nr][nc] == friend);
                    cnt = find ? cnt + 1 : cnt;
                }
            }

            this.friendCnt = cnt;
        }

        public void setEmptyCnt(int num) { // 학생의 번호가 num 이고 자리가 (r,c) 일때 사방탐색을 통해서 인접한 칸에 빈자리가 몇칸인지 Set
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nr = this.r + dr[d];
                int nc = this.c + dc[d];

                if (isInside(nr, nc) && classroom[nr][nc] == 0)
                    cnt++;
            }

            this.emptyCnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // 학생의 번호
            // 좋아하는 학생 4명의 번호
            int f1 = Integer.parseInt(st.nextToken());
            int f2 = Integer.parseInt(st.nextToken());
            int f3 = Integer.parseInt(st.nextToken());
            int f4 = Integer.parseInt(st.nextToken());

            findSeat(num, new int[] {f1, f2, f3, f4}); // num 번호의 학생의 자리를 find
        }

//        System.out.println(Arrays.deepToString(classroom)); // 학생들 자리배치가 제대로 되어있는지 확인
        System.out.println(getSatisfaction());
    }

    public static void findSeat(int num, int[] friends) {
        PriorityQueue<Seat> pq = new PriorityQueue<>(); // 우선순위가 높은 자리에 배정하기위한 PQ 생성
        Seat seat;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (classroom[r][c] != 0)
                    continue;
                // 교실의 (r,c) 자리가 빈자리일 때만 PQ 에 넣는다.
                seat = new Seat(r, c); // (r, c) 의 빈자리가 존재
                seat.setFriendCnt(num, friends); // (r, c) 기준으로 num 번호의 학생이 앉았을 때 인접한 칸에 좋아하는 학생이 몇명이 있는지 Set
                seat.setEmptyCnt(num); // (r, c) 기준으로 num 번호의 학생이 앉았을 때 인접한 칸에 빈자리가 몇칸이 있는지 Set
                pq.offer(seat); // 완성된 seat 정보를 PQ 에 넣는다.
            }
        }

        seat = pq.poll(); // PQ 에서 처음으로 poll 한 seat 가 가장 우선순위가 높은 자리이다.
        classroom[seat.r][seat.c] = num; // 해당 자리에 num 번호의 학생을 배치
        hm.put(num, new Student(seat.r, seat.c, friends)); // num 번호의 학생이 어떤 자리에 있고 그 학생이 좋아하는 4명의 학생에 대한 정보를 map 에 저장
    }

    public static int getSatisfaction() {
        int score = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (isInside(nr, nc)) {
                        boolean find = Arrays.stream(hm.get(classroom[r][c]).friends).anyMatch(friend -> classroom[nr][nc] == friend);
                        cnt = find ? cnt + 1 : cnt;
                    }
                }

                if (cnt == 0)
                    continue;

                score += Math.pow(10, cnt - 1);
            }
        }

        return score;
    }

    public static boolean isInside(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
