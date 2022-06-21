package mon6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1922_네트워크연결 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int adjMatrix[][] = new int[N][N]; // 간선들의 정보가 담겨 있는 배열
        boolean[] visited = new boolean[N];
        int minEdge[] = new int[N];

        StringTokenizer st=null;
        for (int i = 0; i < M; i++) {
            st= new StringTokenizer(br.readLine()," ");

            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            adjMatrix[a][b] =c;
            adjMatrix[b][a] =c;

        }
        Arrays.fill(minEdge,Integer.MAX_VALUE);
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(adjMatrix[i]));
//        }
        int result =0; //최소신장트리 비용
        minEdge[0]= 0; //임의의 시작점 0의 간선비용을 0으로 세팅

        for (int i = 0; i < N; i++) {
            //1. 신장트리에 포함되지 않은 정점 중 최소 간선 비용의 정점 찾기
            int min = Integer.MAX_VALUE;
            int minVertex =-1; // 최소간선비용의 정점 번호

            for (int j = 0; j < N; j++) {
                if(!visited[j] && min>minEdge[j]){
                    min = minEdge[j];
                    minVertex=j;

                }
            }


            visited[minVertex] =true; //신장트리에 포함시킴
            result+=min; // 간선 비용 누적

            //2. 선택된 기준으로 신장트리에 연결되지 않은 타 정점과 간선 비용 최소로 업데이트
            for (int j = 0; j < N; j++) {
                if(!visited[j] && adjMatrix[minVertex][j]!=0 && minEdge[j]>adjMatrix[minVertex][j]){
                    minEdge[j]=adjMatrix[minVertex][j];
                }
            }


        }
        System.out.println(result);


    }
}
