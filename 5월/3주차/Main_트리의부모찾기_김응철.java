import java.util.*;
import java.io.*;

public class Main_트리의부모찾기_11725 {
    static int N;
    static int[] parents;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // 노드의 갯수 입력
        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        parents[1] = 1;

        for (int i = 0; i <= N + 1; i++) {
            tree.add(new ArrayList<Integer>());
        }

        // 연결된 노드들 입력
        for (int n = 1; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(1);

        while (!queue.isEmpty()) {
            int tmp = queue.poll();
            for (int num : tree.get(tmp)) {
                if (parents[num] == 0) {
                    parents[num] = tmp;
                    queue.add(num);
                }
            }
        }

        for (int i = 2; i < N + 1; i++) {
            System.out.println(parents[i]);
        }
    }
}
