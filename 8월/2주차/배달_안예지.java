package mon8;

import java.util.*;
class Programmers_배달 {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        int dist[][] = new int [N][N];
        for(int i =0 ; i<N ; i++){
            Arrays.fill(dist[i],987654321);
        }
        for(int i = 0; i<road.length ; i++){
            int x = road[i][0];
            int y = road[i][1];
            int c = road[i][2];
            
            
            dist[x-1][y-1] = Math.min(c,dist[x-1][y-1]);
            dist[y-1][x-1] = Math.min(c,dist[y-1][x-1]);
        }
        
        for(int k = 0 ; k<N; k++){
            for(int i = 0 ; i<N ; i++){
                for(int j =0 ; j<N ; j++){
                    if(i==j) dist[i][j]=0;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }
        
        // for(int i = 0 ; i<N; i++){
        //    System.out.println(Arrays.toString(dist[i]));
        // }
        
        for(int i = 0 ; i<N; i++){
            if(dist[0][i]<=K) {
                answer++;
                // System.out.println(dist[0][i]);
            }
        }

        return answer;
    }
}