import java.util.*;
class Solution {
    static int N;
    static char[][] map;
    static int[] dr = {0,0,1,-1}; // ???? 이거 순서 바꾼다고 답이 맞고 틀리고 하네...??? {0,0,-1,1}
    static int[] dc = {1,-1,0,0};
    public int[] solution(String[][] places) {
        N = 5;
        
        int[] answer = new int[N];
        for(int n = 0; n<N;n++){
            map = new char[N][N];
            for(int r = 0;r <N;r++){
                map[r] = places[n][r].toCharArray();
            }
            answer[n] = chkDist();
        }
        return answer;
    }
    
    static public int chkDist(){
        for(int r = 0; r<N;r++){
            for(int c = 0; c<N;c++){
                if(map[r][c] == 'P' && bfs(r,c))
                    return 0;
            }
        }
        return 1;
    }
    
    static public boolean bfs(int r, int c){
        Queue<Pos> q = new LinkedList<>();
        boolean[][] chk = new boolean[N][N];
        chk[r][c] = true;
        q.add(new Pos(r,c));
        int cnt = 0;
        while(!q.isEmpty() && cnt < 2){
            Pos tmp = q.poll();
            for(int d= 0 ;d<4;d++){
                int nr = tmp.r + dr[d];
                int nc = tmp.c + dc[d];
                
                if(nr < 0 || nr >= N || nc < 0 || nc >= N || chk[nr][nc] || map[nr][nc] == 'X')
                    continue;
                
                if(map[nr][nc] == 'P')
                    return true;
                
                chk[nr][nc] = true;
                q.add(new Pos(nr, nc));
            }
            cnt++;
        }
        
        
        return false;
    }
    
    static class Pos{
        int r,c;
        private Pos(int r, int c){
            this.r =r;
            this.c =c;
        }
    }
}