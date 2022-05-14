import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * packageName 	: PACKAGE_NAME
 * fileName 	: Main_트리의부모찾기_김유진
 * author 	    : 김유진
 * date		    : 2022-05-14
 * description	:
 * ===========================================================
 * DATE 		    AUTHOR 		        NOTE
 * -----------------------------------------------------------
 * 2022-05-14	        김유진  		        최초 생성
 */
public class Main_트리의부모찾기_김유진 {
    static int N;
    static Node[] nodes;
    static boolean[] chks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new Node[N + 1];
        chks = new boolean[N + 1];
        for (int n = 1; n <= N; n++) {
            nodes[n] = new Node(n);
        }

        for (int i = 0; i < N - 1; i++) {
            String[] nums = br.readLine().split(" ");
            int pIdx = Integer.parseInt(nums[0]);
            int cIdx = Integer.parseInt(nums[1]);
            nodes[pIdx].child.add(cIdx);
            nodes[cIdx].child.add(pIdx);
        }

        findParent();


        StringBuilder sb = new StringBuilder();
        for (int n = 2; n <= N; n++) {
            sb.append(nodes[n].parent).append("\n");
        }
        System.out.println(sb);

    }

    static void findParent() {
        chks[1] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        while (!q.isEmpty()) {
            int idx = q.poll();
            for (int num : nodes[idx].child) {
                if (chks[num])
                    continue;
                chks[num] = true;
                q.add(num);
                nodes[num].parent = idx;
            }
        }
    }

    static class Node {
        int idx;
        int parent;
        ArrayList<Integer> child;

        public Node(int idx) {
            this.idx = idx;
            this.parent = 0;
            this.child = new ArrayList<>();
        }
    }
}