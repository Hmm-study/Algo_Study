import java.util.*;

class Solution {
	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};
	boolean[][] visit;
	
    public int solution(int[][] maps) {
    	int n = maps.length;
    	int m = maps[0].length;
    	
        visit = new boolean[n][m];
    	int answer = Integer.MAX_VALUE;
        
        Queue<node> q = new LinkedList<node>();
        q.add(new node(0,0,1));
        while(!q.isEmpty()) {
        	node now = q.poll();
        	
        	if(now.x == m-1 && now.y == n-1) {
        		answer = Math.min(answer, now.cnt);
        		continue;
        	}
        	
        	for (int dir = 0; dir < 4; dir++) {
				int nx = dx[dir] + now.x;
                int ny = dy[dir] + now.y;
				
				if(nx < 0 || ny < 0 || nx >= m || ny >= n || visit[nx][ny]) continue;
				if(maps[nx][ny] == 0) continue;
				
				visit[nx][ny] = true;
				int cnt = now.cnt + 1;
				q.add(new node(nx, ny, cnt));
			}
        }
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}

class node {
	int x, y, cnt;
	
	public node(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}