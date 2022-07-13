import java.util.*;
import java.io.*;

public class Main_로봇청소기_14503 {
    static int N, M, r, c, d, cnt = 1;
    static int[][] map;
    static boolean[][] cleaned;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cleaned = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        } // input

        cleaned[r][c] = true;

        int turn = 0;
        while(true){
            if(turn == 4){
                if(d == 0){
                    if(map[r+1][c] == 1)
                        break;
                    else{
                        r+=1;
                        turn = 0;
                    }
                }else if(d == 1){
                    if(map[r][c-1] == 1)
                        break;
                    else{
                        c-=1;
                        turn = 0;
                    }
                }else if(d == 2){
                    if(map[r-1][c] == 1)
                        break;
                    else{
                        r-=1;
                        turn = 0;
                    }
                }else if(d == 3){
                    if(map[r][c+1] == 1)
                        break;
                    else{
                        c+=1;
                        turn = 0;
                    }
                }
            }

            if(d == 0){
                if(map[r][c-1] == 0 && !cleaned[r][c-1]){
                    c -=1;
                    cnt++;
                    cleaned[r][c] = true;
                    turn = 0;
                }else{
                    turn++;
                }
                d = 3;
            }else if(d == 1){
                if(map[r-1][c] == 0 && !cleaned[r-1][c]){
                    r -= 1;
                    cnt++;
                    cleaned[r][c] = true;
                    turn = 0;
                }else{
                    turn++;
                }
                d = 0;
            }else if(d == 2){
                if(map[r][c+1] == 0 && !cleaned[r][c+1]) {
                    c += 1;
                    cnt++;
                    cleaned[r][c] = true;
                    turn = 0;
                }else{
                    turn++;
                }
                d = 1;
            }else if(d == 3){
                if(map[r+1][c] == 0 && !cleaned[r+1][c]){
                    r += 1;
                    cnt++;
                    cleaned[r][c] = true;
                    turn = 0;
                }else{
                    turn++;
                }
                d = 2;
            }
        }

        System.out.println(cnt);
    }

}
