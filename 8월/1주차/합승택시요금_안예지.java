package mon8;

import java.util.*;

class Programmers_합승택시요금 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        
        int dist[][] = new int[n][n];
        for(int i=0; i<n; i++){
            Arrays.fill(dist[i], 987654321);
        }
        
        for(int i=0; i<fares.length; i++){
            dist[fares[i][0]-1][fares[i][1]-1] = fares[i][2];
            dist[fares[i][1]-1][fares[i][0]-1] = fares[i][2];
        }
        // for(int i=0; i<dist.length; i++){
        //     System.out.println(Arrays.toString(dist[i]));
        // }
        System.out.println();
        
        for(int k=0; k<n ; k++){
            for(int i=0; i<n ; i++){
                for(int j=0; j<n ; j++){
                    if(i==j) dist[i][j]=0;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }   
        }
        //     for(int i=0; i<dist.length; i++){
        //     System.out.println(Arrays.toString(dist[i]));
        // }
         for(int i=0; i<n; i++){
             if(dist[s-1][i]!=987654321 && dist[i][a-1]!=987654321&& dist[i][b-1]!=987654321)
            answer = Math.min(answer, dist[s-1][i]+dist[i][a-1]+dist[i][b-1]);
        }
        
        return answer;
    }
}