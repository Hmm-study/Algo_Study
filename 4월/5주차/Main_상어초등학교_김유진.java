import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main_상어초등학교_김유진 {
    static int maxNum, N;
    static int[][][] map;
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static HashMap<String, Integer> recordPos;
    static Student[] sList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N][3];
        maxNum = N * N;
        recordPos = new HashMap<>();
        sList = new Student[N * N];

        for (int idx = 0; idx < maxNum; idx++) {
            String[] line = br.readLine().split(" ");
            sList[idx] = new Student(Integer.parseInt(line[0]) );
            for (int i = 1; i < 5; i++) {
                sList[idx].loveMember.add(Integer.parseInt(line[i]));
            }
//            System.out.println(sList[idx].num + " : " + sList[idx].loveMember.toString());
        }

        System.out.println(simul());
    }


    static public int simul() {
        int score = 0;
        for (int idx = 0; idx < maxNum; idx++) {
            setPos(idx);
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                score += calcScore(r, c);
            }
        }

        return score;
    }

    static public int calcScore(int r, int c) {
        int cnt = 0;
        int idx = recordPos.get("" + r + "," + c);
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                continue;
            if (sList[idx].loveMember.contains(map[nr][nc][0]))
                cnt++;
        }

        switch (cnt) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 10;

            case 3:
                return 100;
            default:
                return 1000;
        }

    }

    static public void setPos(int idx) {
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        int sIdx = sList[idx].num;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c][0] != 0)
                    continue;
                Pos p = new Pos(r, c);
                p.setLoveCnt(idx, r, c);
                p.setEmptyCnt(r, c);
                pq.add(p);
            }
        }
        Pos ans = pq.poll();
        map[ans.r][ans.c][0] = sIdx;
        recordPos.put("" + ans.r + "," + ans.c, idx);
    }

    static public class Student {
        int num;
        HashSet<Integer> loveMember;

        public Student(int num) {
            super();
            this.num = num;
            this.loveMember = new HashSet<>();
        }

    }

    static public class Pos implements Comparable<Pos> {
        int r, c, loveCnt, emptyCnt;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
            this.loveCnt = 0;
            this.emptyCnt = 0;
        }

        @Override
        public int compareTo(Pos o) {
            int c1 = -Integer.compare(this.loveCnt, o.loveCnt);
            if (c1 != 0)
                return c1;
            int c2 = -Integer.compare(this.emptyCnt, o.emptyCnt);
            if (c2 != 0)
                return c2;
            int c3 = Integer.compare(this.r, o.r);
            if (c3 != 0)
                return c3;
            return Integer.compare(this.c, o.c);
        }

        public void setLoveCnt(int idx, int r, int c) {
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;

                if (sList[idx].loveMember.contains(map[nr][nc][0]))
                    cnt++;
            }
            this.loveCnt = cnt;
        }

        public void setEmptyCnt(int r, int c) {
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                    continue;

                if (map[nr][nc][0] == 0)
                    cnt++;
            }
            this.emptyCnt = cnt;
        }

    }
}
