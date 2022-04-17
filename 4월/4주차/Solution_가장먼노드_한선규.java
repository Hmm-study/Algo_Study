package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_가장먼노드_한선규 {

    public static void main(String[] args) {
        System.out.println(solution(6, new int[][] { {3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2} }));
    }

    public static int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>(); // 인접 그래프

        for (int i = 0; i <= n; i++) { // n 개의 정점에 대해서 연결정보리스트 추가
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) { // 간선은 양방향이므로 양쪽에 리스트를 추가
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }

        answer = bfs(graph, new boolean[n + 1]);

        return answer;
    }

    public static int bfs(ArrayList<ArrayList<Integer>> graph, boolean[] visited) {
        Queue<Integer> q = new LinkedList<Integer>(); // 같은 너비에 있는 탐색할 정점들의 순서를 저장할 큐
        q.add(1); // 1번 노드 부터 탐색
        visited[1] = true; // 1번 노드 방문 처리
        int cnt = 0; // 마지막 거리에 대해서 큐에 들어갔던 원소들의 개수 (즉, 가장 먼 노드의 개수)

        while (!q.isEmpty()) {
            cnt = q.size();

            for (int i = 0; i < cnt; i++) {
                int num = q.poll();

                ArrayList<Integer> nextList = graph.get(num); // num 에 인접한 정점들의 정보를 가지는 리스트

                for (int j = 0; j < nextList.size(); j++) {
                    int next = nextList.get(j);
                    if (!visited[next]) { // 아직 탐색하지 않은 인접한 정점이라면
                        visited[next] = true; // 방문 처리
                        q.add(next); // 큐에 삽입
                    }
                }
            }
        }

        return cnt;
    }
}
