import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_거리두기_확인하기 {

    public static void main(String[] args) {
        String[][] places={{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        System.out.printf(Arrays.toString(solution(places)));


    }

    private static int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i=0; i<places.length; i++){
            String[] p = places[i];
            boolean check = true;

            for(int j=0; j<5 && check; j++){
                for(int k=0; k<5 && check; k++){
                   if(p[j].charAt(k)== 'P'){
                       if(!bfs(j, k, p)){
                           check = false;
                       }
                   }
                }
            }

            answer[i] = check ? 1 : 0;

        }
    return answer;



    }

    private static boolean bfs(int j, int k, String[] p) {
        int[] dj = {-1, 1, 0, 0};
        int[] dk = {0, 0, -1, 1};

        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{j, k});

        while(!q.isEmpty()){
            int[] pos = q.poll();

            for(int i=0; i<4; i++){
                int nj = pos[0] + dj[i];
                int nk = pos[1] + dk[i];

                if(nj < 0 || nk < 0 || nj>=5 || nk>= 5 || (nj==j && nk==k))
                    continue;

                int d = Math.abs(nj - j) + Math.abs(nk - k);

                if(p[nj].charAt(nk) == 'P' && d<2)
                    return false;

                else if(p[nj].charAt(nk)== 'O' && d<2)
                    q.offer(new int[]{nj, nk});


            }

        }
        return true;


    }


}
