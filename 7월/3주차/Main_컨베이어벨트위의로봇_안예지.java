package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_20055_컨베이어벨트위의로봇 {
    static int N,K,cnt;
    static int conveyor[], robot[];
    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 컨베이어벨트 길이
        K = Integer.parseInt(st.nextToken());

        conveyor = new int[N*2];
        robot = new int[N*2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N*2; i++) {
            conveyor[i] = Integer.parseInt(st.nextToken());
        } //입력

        // 배열 0 -> 올리는 자리 N-1 -> 내리는 자리

        int turn = 0;
       while (true){

           // 컨베어 벨트 회전
           rotation();

           // 로보트이동하기
           if(turn!=0) {
               moveRobot();
           }
           // 로보트 올리기
           if(robot[0]==0 && conveyor[0]!=0) {

               UpRobot();
           }
           turn++;
           if(K<=cnt){
               break;
           }
       }


        System.out.println(turn);
    }


    private static void UpRobot() {
        //올리는 자리에 로보트가 없고 내구도가 1이상이라면 로보트 올림
            robot[0] = 1;
            conveyor[0]--;
            if(conveyor[0]==0){
                cnt++;
            }

    }

    private static void moveRobot() {

        for (int i = N-1; i >0; i--) {
            // 로보트가 있고 다음자리에 로보트가 없고 내구도가 1이상이라면 이동 가능!
            if(robot[i-1]==1 && robot[i]!=1 && conveyor[i]>=1){
                robot[i-1] = 0;
                robot[i] = 1;
                conveyor[i]--;

                if(conveyor[i]==0) {
                    cnt++;
                }
            }
        }
        robot[N-1]=0; // 하차
    }

    static void rotation() {
        int temp = conveyor[N * 2 - 1];

        for (int i = N * 2 - 1; i >0; i--) {
            conveyor[i] = conveyor[i-1];

        }


        for (int i = N-2; i >0 ; i--) {
            robot[i] = robot[i-1];
        }
        conveyor[0] = temp;
        robot[0] = 0;
    }
}
