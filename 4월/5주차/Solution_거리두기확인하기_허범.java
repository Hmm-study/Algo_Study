package 프로그래머스;

import java.util.*;
import java.awt.Point;

public class 거리두기확인하기 {

    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};

    public static int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for(int i=0; i<places.length; i++) {
            String str[] = places[i];

            boolean isCheck = true;
            for(int r=0; r<5 && isCheck; r++) {
                for(int c=0; c<5 && isCheck; c++) {
                    if(str[r].charAt(c) == 'P') {
                        if(!bfs(r,c,str))
                            isCheck = false;
                    }
                }
            }
            if(isCheck)
                answer[i] = 1;
            else
                answer[i] = 0;
        }

        return answer;
    }

    public static boolean bfs(int r, int c, String []places) {
        Queue<Point> queue = new LinkedList<Point>();

        queue.add(new Point(r,c));

        while(!queue.isEmpty()) {
            Point p = queue.poll();

            for(int d=0; d<4; d++) {
                int nx = p.x + dx[d];
                int ny = p.y + dy[d];

                if(nx<0 || ny<0 || nx>=5 || ny>=5 || (nx == r && ny == c))
                    continue;

                int dist = Math.abs(nx - r) + Math.abs(ny - c);

                if(places[nx].charAt(ny) == 'P' && dist <= 2)
                    return false;
                else if(places[nx].charAt(ny) == 'O' && dist < 2)
                    queue.offer(new Point(nx, ny));
            }
        }

        return true;
    }
}
