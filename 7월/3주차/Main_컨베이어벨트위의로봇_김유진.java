import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static LinkedList<Pos> l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        l = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < 2 * N; n++) {
            l.add(new Pos(Integer.parseInt(st.nextToken()), false));
        }
        System.out.println(simul());
    }

    static int simul() {
        int step = 1;
        int cnt = 0;
        while (true) {
            // 2*N 번째 칸이 1로 이동한다.
            l.addFirst(l.pollLast());

            // N번째 칸의 로봇을 내린다.
            l.get(N - 1).r = false;

            // 로봇을 이동 시킴
            for (int i = N - 2; i > 0; i--) {
                if (l.get(i).r && !l.get(i + 1).r && l.get(i + 1).a > 0) {
                    l.get(i).r = false;
                    l.get(i + 1).r = true;
                    if (--l.get(i + 1).a == 0)
                        cnt++;
                }
            }

            // N번째 칸의 로봇을 내린다.
            l.get(N - 1).r = false;

            // 로봇을 올리 수 있으면 올린다
            if (l.get(0).a > 0) {
                if (--l.get(0).a == 0)
                    cnt++;
                l.get(0).r = true;
            }

            // 내구도가 0인 칸을 센다

            if (cnt >= K)
                return step;


            // 스텝 증가
            step++;
        }

    }

    static class Pos {
        int a;
        boolean r;

        public Pos(int a, boolean r) {
            this.a = a;
            this.r = r;
        }
    }
}