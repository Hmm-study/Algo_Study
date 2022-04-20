package 프로그래머스;

import java.util.*;

public class 가장먼노드 {
    static Queue<Integer> q = new LinkedList<>();
    static int arr[];
    static ArrayList<Integer>[]list;
    static boolean visited[];
    public static int solution(int n, int[][] edge) {
        int ans = 0;
        arr = new int[n+1];
        visited = new boolean[n+1];
        list = new ArrayList[n+1];

        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            list[a].add(b);
            list[b].add(a);
        }


        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
            int a=  q.poll();
            for(int b : list[a]) {
                if(visited[b]) {
                    continue;
                }
                q.add(b);
                visited[b] = true;
                arr[b] = arr[a]+1;
            }
        }

        Arrays.sort(arr);
        int cnt = 0;
        int max = arr[n];
        for(int i=n; i>=1; i--) {
            if(max == arr[i]) {
                cnt++;
            }
            else {
                break;
            }
        }

        return cnt;
    }
}
