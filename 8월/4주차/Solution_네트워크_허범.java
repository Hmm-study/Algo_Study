import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
    int answer = 0;
    boolean[] visit = new boolean[n];
    Queue<int[]> q = new LinkedList<>();

    for(int i=0; i<computers.length; i++) {
        if(visit[i]) continue;

        q.offer(computers[i]);
        visit[i] = true;

        while(!q.isEmpty()) {
            int[] computer = q.poll();

            for(int j=0; j<computer.length; j++) {
                if(!visit[j] && computer[j] == 1) {
                    q.offer(computers[j]);
                    visit[j] = true;
                }
            }
        }
        answer++;
    }

    return answer;
    }
}