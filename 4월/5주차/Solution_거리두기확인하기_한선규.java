package april.fiveweeks;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_거리두기확인하기_한선규 {

    public int[] solution(String[][] places) {
        int len = places.length;
        int[] answer = new int[len];

        for (int i = 0; i < len; i++) {
            String[] place = places[i];

            boolean flag = true;

            loop:
            for (int r = 0; r < len; r++) {
                for (int c = 0; c < len; c++) {
                    if ('P' == place[r].charAt(c)) { // 응시자로 부터 사방탐색을 진행
                        if (!bfs(place, r, c)) {
                            flag = false;
                            break loop;
                        }
                    }
                }
            }

            answer[i] = flag ? 1 : 0;
        }

        return answer;
    }

    public boolean bfs(String[] place, int r, int c) {
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c}); // 현재 사방탐색의 기준이 되는 응시자의 좌표는 (r, c)
//        boolean[][] visited = new boolean[5][5];
//        visited[r][c] = true;

        while (!q.isEmpty()) {
            int cr = q.peek()[0];
            int cc = q.poll()[1];

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (isInside(nr, nc) && !(nr == r && nc == c)) { // 기존에는 !visited[nr][nc] 로 방문체크했지만... 탐새을 시작한 응시자의 좌표인지만 확인하면된다....
                    int dist = getDistance(r, c, nr, nc);
                    if ('P' == place[nr].charAt(nc) && dist <= 2) // 지금 사방탐색으로 탐색을 하고있는 위치가 거리가 2이하면서 다른 응시자가 앉아있는 좌표라면 거리두기 미준수
                        return false;
                    else if ('O' == place[nr].charAt(nc) && dist < 2) { // 지금 사방탐색으로 탐색을 하고있는 위치가 거리가 1이면서 빈테이블이라면 큐에 넣어서 다시한번 사방탐색을 해본다.
                        q.offer(new int[] {nr, nc});
//                        visited[nr][nc] = true;
                    }
                }
            }
        }

        return true;
    }

    public int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    public boolean isInside(int r, int c) {
        return r >= 0 && r < 5 && c >= 0 && c < 5;
    }
}
