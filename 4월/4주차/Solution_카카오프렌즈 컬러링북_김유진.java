import java.util.Queue;
import java.util.LinkedList;
class Solution {
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    static int M, N;
    static boolean[][] chk;
    static int[][] map;
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        chk = new boolean[m][n];
        map = picture;
        M = m;
        N = n;
        for(int r = 0; r < m; r++){
            for(int c = 0; c < n; c++){
                if(map[r][c] != 0 && !chk[r][c]){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, cntArea(r,c));
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    static int cntArea(int r, int c){
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(r,c));
        chk[r][c] = true;
        int cnt = 1;
        while(!q.isEmpty()){
            Pos tmp = q.poll();
            for(int d = 0; d < 4;d++){
                int nr = tmp.r + dr[d];
                int nc = tmp.c + dc[d];
                if(nr < 0 || nr >= M || nc < 0 || nc >= N || chk[nr][nc] || map[nr][nc] == 0 || map[nr][nc] != map[tmp.r][tmp.c])
                    continue;
                chk[nr][nc] = true;
                q.add(new Pos(nr, nc));
                cnt++;
            }
        }
        return cnt;
    }
    
    static class Pos{
        int r, c;
        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}