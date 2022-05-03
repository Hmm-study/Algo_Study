import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_좌표_정렬하기2_김유진
 * author 	    : 김유진
 * date		    : 2022-05-03
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-03	        김유진  		        최초 생성
 */
public class Main_좌표_정렬하기2_김유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Pos tmp = pq.poll();
            sb.append(tmp.x).append(" ").append(tmp.y).append("\n");
        }

        System.out.println(sb);

    }

    static class Pos implements Comparable<Pos> {
        int y, x;

        @Override
        public int compareTo(Pos o) {
            int c1 = Integer.compare(this.y, o.y);
            if (c1 != 0)
                return c1;
            return Integer.compare(this.x, o.x);
        }

        public Pos(int x, int y) {
            this.y = y;
            this.x = x;
        }
    }
}