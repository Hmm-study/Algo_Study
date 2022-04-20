import java.util.*;

public class Programmers_가장_먼_노드 {

    public int solution(int n, int[][] edge) {
        int answer = 0;
        //그래프 구현
        ArrayList<ArrayList<Integer>> list=new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<edge.length;i++){
            list.add(new ArrayList<Integer>());
        }
        //노드 연결
        int a, b;
        for(int[] node:edge){
            a=node[0];
            b=node[1];
            list.get(a).add(b);
            list.get(b).add(a);
        }

        int[] count=new int[n+1];//1과의 거리 저장
        boolean[] visited=new boolean[n+1];//방문여부
        Queue<Integer> q=new LinkedList<>();
        q.add(1);//시작점
        visited[0]=visited[1]=true;//1에서 시작(0은 사용안함)
        int now;
        while(!q.isEmpty()){
            now=q.poll();
            for(int v:list.get(now)){//나와 연결된 노드들
                if(!visited[v]){//방문하지 않은 곳이라면
                    count[v]=count[now]+1;//1과의 길이 저장
                    visited[v]=true;
                    q.add(v);//이곳과 연결된 노드에 방문하기 위함
                }
            }
        }
        //System.out.println(Arrays.toString(count));
        int max=0;//1과 가장 멀리 떨어진 노드와의 길이 저장
        for(int cnt:count){
            if(max<cnt){//더 큰 길이가 있다면 그게 max
                max=cnt;
                answer=1;
            }
            else if(max==cnt) answer++;
        }
        return answer;
    }

}
