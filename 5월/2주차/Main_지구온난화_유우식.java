import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5212 {

    static int R, C;
    static int dy[] = {-1,1,0,0}, dx[]= {0,0,-1,1};
    static char map[][];
    static Queue<Loc> q = new LinkedList<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }
        check();
        change();
        print();
    }

    private static void print() { // 육지에 대해서 상하좌우값을 갱신하는 방식으로 map의 크기를 찾는다 이후 출력
        int rmin = 10, rmax = 0, cmin = 10, cmax = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'X') {
                    rmin = Math.min(rmin, i);
                    rmax = Math.max(rmax, i);
                    cmin = Math.min(cmin, j);
                    cmax = Math.max(cmax, j);
                }
            }
        }
        for (int i = rmin; i <=rmax; i++) {
            for (int j = cmin; j <=cmax; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void change() { // 변경 가능한 육지 체크했으면 다시 바다로 변경
        while(!q.isEmpty()) {
            Loc n = q.poll();
            map[n.y][n.x] = '.';
        }
    }

    private static void check() { // 육지에 대해서 4방탐색해서 바다 체크 배열 범위 벗어난곳도 바다
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 'X') {
                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int ny = i + dy[k];
                        int nx = j + dx[k];
                        if(ny < 0 || nx < 0 || ny>= R || nx >= C) {
                            count++;
                            continue;
                        }
                        if(map[ny][nx] =='X')
                            continue;
                        count++;
                    }
                    if(count >= 3)
                        q.offer(new Loc(i,j));
                }
            }
        }
    }

    static class Loc{
        int y,x;
        Loc(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}