package mon8;

import java.util.*;
class Programmers_게임맵최단거리 {
    static int da[][]= {{1,-1,0,0},{0,0,1,-1}};
    public int solution(int[][] maps) {
        int answer = 0;
        
        bfs(maps);
        
        // for(int i=0 ; i<maps.length ; i++){
        //     System.out.println(Arrays.toString(maps[i]));
        // }
        if(maps[maps.length-1][maps[0].length-1]==1){
            answer=-1;
        }else{
            answer = maps[maps.length-1][maps[0].length-1];
        }
        return answer;
    }
    
    public static void bfs(int [][] maps){
        Queue<Point> qu = new LinkedList<>();
        qu.add(new Point(0,0));
        while(!qu.isEmpty()){
          Point P = qu.poll();
            int x = P.x;
            int y = P.y;
            
            for(int i=0 ; i<4 ;i++){
            int X = x + da[0][i];
            int Y = y + da[1][i];
            
            if(X>=0 && Y>=0 && X<maps.length && Y<maps[0].length){
                if(maps[X][Y]==1 ){
                    maps[X][Y]= maps[x][y]+1;
                    qu.add(new Point(X,Y));
                    }
                }
            }
        }
       
 
    }
    
    public static class Point{
        int x;
        int y;
        
        public Point(int x, int y){
            this.x =x;
            this.y =y;
            
        }
    }
}