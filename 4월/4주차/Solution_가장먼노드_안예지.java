import java.util.*;

public class Programmers_가장먼노드 {
    public static void main(String[] args) {
        int n =6;
        int[][] edge={{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        System.out.println(solution(n,edge));
    }

    private static int solution(int n, int[][] edge) {
        int answer =0;

        // 노드마다 이어져 있는 애들 줄 세우기 위한 리스트
        LinkedList<Integer> list[]= new LinkedList[n+1];

        Queue<Integer> qu = new LinkedList<>();
        boolean check[]= new boolean[n+1];
        check[1]=true;
        for (int i = 0; i <=n; i++) {
            list[i]= new LinkedList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            list[edge[i][0]].add(edge[i][1]);
            list[edge[i][1]].add(edge[i][0]);
        }
        for (int i = 0; i < list[1].size(); i++) {
            qu.add(list[1].get(i));
            check[list[1].get(i)]=true;

        }

        while (!qu.isEmpty()){

            int size = qu.size();
            answer=size;
            for (int i = 0; i < size; i++) {
                int num = qu.poll();

                for (int j = 0; j < list[num].size(); j++) {
                    int num2 = list[num].get(j);
                    if(!check[list[num].get(j)]){
                        qu.add(list[num].get(j));
                        check[list[num].get(j)]=true;

                    }

                }

            }

        }
        return answer;
    }
}
