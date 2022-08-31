package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 토마토_7569 {

    static int[] dx = {0, 0, 0, 0, -1, 1};
    static int[] dy = { 0, 0, -1, 1, 0, 0 };
    static int[] dz = { -1, 1, 0, 0, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int count = 0;

        int[][][] map = new int[H][N][M];

        Queue<node> queue = new LinkedList<>();

        for(int h = 0; h < H; h++){
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++){
                    map[h][i][j] = Integer.parseInt(st.nextToken());
                    if(map[h][i][j] == 0)
                        count++;
                    else if(map[h][i][j] == 1){
                        queue.add(new node(i, j, h));
                    }
                }
            }
        }
        if(count == 0){
            System.out.println("0");
            return;
        }

        int time = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            int check = 0;
            time++;
            for(int i = 0; i < size; i++){
                node now = queue.poll();
                int x = now.x;
                int y = now.y;
                int z = now.z;

                for(int dir = 0 ; dir < 6; dir++){
                    int nx = x + dx[dir], ny = y + dy[dir], nz = z + dz[dir];

                    if(nx<0||ny<0||nz<0||nx>=N||ny>=M||nz>=H)
                        continue;

                    if(map[nz][nx][ny]==-1||map[nz][nx][ny]==1)
                        continue;

                    if(map[nz][nx][ny] == 0){
                        map[nz][nx][ny] = 1;
                        check++;
                        queue.add(new node(nx, ny, nz));
                    }
                }
                count -= check;
                if(count == 0)
                    break;
            }
        }
        if(count == 0)
            System.out.println(time);
        else
            System.out.println("-1");
    }
}
class node{
    int x, y, z;

    public node(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}