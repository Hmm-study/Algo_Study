package may.oneweeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_저울_한선규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 저울추의 개수 (1 <= N <= 1000)
        int min = 1; // 주어진 추들로 측정할 수 없는 양의 저울 무게 중 최소무게
        int[] weights = new int[N]; // N 개의 저울추의 무게에 대한 정보를 저장할 배열
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 0; n < N; n++) {
            weights[n] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(weights); // 저울추의 무게로 오름차순 정렬
        // 1 1 2 3 6 7 30
        for (int i = 0; i < N; i++) {
            if (min < weights[i]) // 지금 현재 저울추의 무게가 최소무게보다 크다면 그대로 최소무게를 반환.
                break;

            min += weights[i];
        }

        System.out.println(min);
    }
}
