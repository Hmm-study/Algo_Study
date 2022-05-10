import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_스타트택시_김유진
 * author 	    : 김유진
 * date		    : 2022-05-07
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-07	        김유진  		        최초 생성
 */
public class Main_스타트택시_김유진2 {
    static int[][] map;
    static int N, M; // 승객은 2부터 표시
    static HashMap<Integer, Pos> des;
    static Taxi taxi;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxi = new Taxi(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, fuel);

        des = new HashMap<>();
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int manR = Integer.parseInt(st.nextToken()) - 1;
            int manC = Integer.parseInt(st.nextToken()) - 1;
            int desR = Integer.parseInt(st.nextToken()) - 1;
            int desC = Integer.parseInt(st.nextToken()) - 1;

            map[manR][manC] = idx + 2;
            des.put(idx + 2, new Pos(desR, desC));
        }

        System.out.println(simul());

    }

    static int simul() {
        int cnt = 0;
        while (cnt != M) {
//            System.out.println(cnt + "---------------------------");
//            System.out.println("(" + taxi.r + ", " + taxi.c + "), fuel : " + taxi.fuel + ", guest : " + taxi.guest);
            // 택시가 가장 가까운 승객을 찾는다.
            if (goGuest())
                break;

//            System.out.println("(" + taxi.r + ", " + taxi.c + "), fuel : " + taxi.fuel + ", guest : " + taxi.guest);
            // 승객을 찾지 못했다면 손님을 태울 수 없는 상황이므로 무한히 운전하다 종료한다.
            // => 연료를 다 사용하고 끝난다.
            if (taxi.guest == 0) {
                taxi.fuel = -1;
                break;
            }

            // 택시가 승객을 찾았다면 목적지로 이동한다.
            if (taxi.guest != 0 && goDes())
                break;

            if (taxi.guest == 0)
                cnt++;
            else{
                taxi.fuel = -1;
                break;
            }

        }

        return taxi.fuel < 0 ? -1 : taxi.fuel;
    }

    static boolean goGuest() {
        Queue<Pos> q = new LinkedList<>();
        boolean[][] chk = new boolean[N][N];
        q.add(new Pos(taxi.r, taxi.c));
        chk[taxi.r][taxi.c] = true;
        // 가장 가까운 손님을 찾기 위해서 pq 사용
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        // step은 연료이다.
        int step = 0;
        while (!q.isEmpty()) {
            // 현재 거리가 몇 군데인지 확인
            int len = q.size();

            // 현재 필요한 연료가 가진 연료보다 크다면 종료
            if (taxi.fuel < step) {
                taxi.fuel = -1;
                return true;
            }

            // 동일한 거리에 대해서 탐색 진행
            for (int l = 0; l < len; l++) {
                Pos tmp = q.poll();
                // 이때 지도의 값이 1보다 크다 == 손님이 있다.
                // pq에 넣는다.
                if (map[tmp.r][tmp.c] > 1)
                    pq.add(new Pos(tmp.r, tmp.c));

                // 4방 탐색을 진행
                for (int d = 0; d < 4; d++) {
                    int nr = tmp.r + dr[d];
                    int nc = tmp.c + dc[d];
                    // 맵을 벗어나거나, 벽이거나, 방문했다면 무시
                    if (isOut(nr, nc) || map[nr][nc] == 1 || chk[nr][nc])
                        continue;

                    // 아닌 경우 다음 탐색 지점 예약
                    chk[nr][nc] = true;
                    q.add(new Pos(nr, nc));

                }
            }
            // 손님을 1명이상 태울 수 있다면
            if (pq.size() > 0) {
                // 가장 가까운(조건) 손님 태우고
                Pos guest = pq.peek();
                taxi.r = guest.r;
                taxi.c = guest.c;
                taxi.guest = map[guest.r][guest.c];
                map[guest.r][guest.c] = 0;
                taxi.fuel -= step;
                return false;
            }

            step++;

        }
        taxi.fuel = -1;
        return true;
    }

    static boolean goDes() {
        Queue<Pos> q = new LinkedList<>();
        boolean[][] chk = new boolean[N][N];
        q.add(new Pos(taxi.r, taxi.c));
        chk[taxi.r][taxi.c] = true;

        // 목적지가 정해져 있다.
        Pos destination = des.get(taxi.guest);

        int step = 0;
        while (!q.isEmpty()) {
            int len = q.size();

            // 현재 필요한 연료가 가진 연료보다 크다면 종료
            if (taxi.fuel < step) {
                taxi.fuel = -1;
                return true;
            }

            for (int l = 0; l < len; l++) {
                Pos tmp = q.poll();

                if (tmp.r == destination.r && tmp.c == destination.c) {
                    taxi.fuel = taxi.fuel + step;
                    taxi.r = tmp.r;
                    taxi.c = tmp.c;
                    taxi.guest = 0;
                    return false;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = tmp.r + dr[d];
                    int nc = tmp.c + dc[d];

                    if (isOut(nr, nc) || map[nr][nc] == 1 || chk[nr][nc])
                        continue;

                    chk[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                }
            }
            step++;
        }

        taxi.fuel = -1;
        return true;
    }

    static boolean isOut(int r, int c) {
        if (r < 0 || r >= N || c < 0 || c >= N)
            return true;
        return false;
    }

    static class Pos implements Comparable<Pos> {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Pos o) {
            int c1 = Integer.compare(this.r, o.r);
            if (c1 != 0)
                return c1;

            return Integer.compare(this.c, o.c);
        }
    }

    static class Taxi {
        int r, c, fuel, guest;

        public Taxi(int r, int c, int fuel) {
            this.r = r;
            this.c = c;
            this.fuel = fuel;
        }
    }
}