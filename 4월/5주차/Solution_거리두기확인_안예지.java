import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_거리두기확인 {
    public static void main(String[] args) {
        String[][] places={{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"}, {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"}, {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}, {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"}, {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        System.out.printf(Arrays.toString(solution(places)));
    }

    static int da1[][]={{-1,0,1,0},{0,1,0,-1}};
    static int da2[][]={{-1,-1,1,1},{-1,1,-1,1}};
 
    private static int[] solution(String[][] places) {
        int [] answer = new int[5];



        for (int i = 0; i < places.length; i++) {
            boolean plug=true;
            char arr[][]= new char[5][5];
            int num =0;
            for (int j = 0; j < places[i].length; j++) {
               arr[num]=places[i][j].toCharArray();
//                System.out.println(Arrays.toString(arr[num]));
               num++;

            }


          aa:  for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    // 응시자일경우 주변 탐색
                    if(arr[j][k]=='P'){
                        plug=  bfs(arr,j,k);
                        if(!plug) break aa;

                    }
                }
            }

            if(plug) answer[i]=1;
            else answer[i]=0;




        }
        return answer;


    }

    private static boolean bfs(char[][] arr,int j,int k) {
         Queue<Point> qu = new LinkedList<>();
         boolean check[][]= new boolean[5][5];
         qu.add(new Point(j,k));
         check[j][k]=true;
        while (!qu.isEmpty()){

            Point P = qu.poll();

            int x= P.x;
            int y=P.y;
            for (int i = 0; i < 4; i++) {
                int X= x+da1[0][i];
                int Y= y+da1[1][i];
                int D= Math.abs(X-j)+Math.abs(Y-k);

                if(X>=0 && Y>=0 && X<5 && Y<5 && D<=2){
                    if(!check[X][Y] && arr[X][Y]=='O'){
                        qu.add(new Point(X,Y));
                        check[X][Y]=true;
                    } else if (!check[X][Y] && arr[X][Y]=='P') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
