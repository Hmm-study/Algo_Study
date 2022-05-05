import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_듣보잡_김유진
 * author 	    : 김유진
 * date		    : 2022-05-05
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-05	        김유진  		        최초 생성
 */
public class Main_듣보잡_김유진 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums = br.readLine().split(" ");

        int N = Integer.parseInt(nums[0]);
        int M = Integer.parseInt(nums[1]);

        HashSet<String> dpeople = new HashSet<>();
        for (int n = 0; n < N; n++) {
            dpeople.add(br.readLine());
        }
        PriorityQueue<String> pq = new PriorityQueue<>();
        for (int m = 0; m < M; m++) {
            String name = br.readLine();
            if(dpeople.contains(name))
                pq.add(name);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(pq.size()).append("\n");
        while(!pq.isEmpty()){
            sb.append(pq.poll()).append("\n");
        }
        System.out.println(sb);

    }
}