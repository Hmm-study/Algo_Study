import java.util.*;
import java.io.*;

public class Main_지구온난화_5212 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int R,C;
        int[] di = {-1,1,0,0};
        int[] dj = {0,0,-1,1};

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        boolean[][] check = new boolean[R][C];

        for(int i=0;i<R;i++){
            String tmp = br.readLine();
            for(int j=0;j<C;j++){
                map[i] = tmp.toCharArray();
            }
        }

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j] == 'X'){
                    int cnt=0;
                    for(int d=0;d<4;d++){
                        int ni = i+di[d];
                        int nj = j+dj[d];
                        if(ni < 0 || ni >= R || nj < 0 || nj >= C){
                            cnt++;
                            continue;
                        }
                        if(map[ni][nj] == '.')
                            cnt++;

                    }
                    if(cnt<3)
                        check[i][j] = true;
                }
            }
        }

        int maxI=-1,minI=100,maxJ=-1,minJ=100;

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(check[i][j]){
                    if(i > maxI)
                        maxI = i;
                    if(i < minI)
                        minI = i;
                    if(j > maxJ)
                        maxJ = j;
                    if(j < minJ)
                        minJ = j;
                }
            }
        }

        for(int i = minI;i<=maxI;i++){
            for(int j = minJ; j<=maxJ;j++){
                if(check[i][j])
                    System.out.print('X');
                else
                    System.out.print('.');
            }
            System.out.println();
        }

    }
}
