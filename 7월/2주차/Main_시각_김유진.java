import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B18312_시각_1 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);

		int cnt = 0;

		for (int h = 0; h <= N; h++) {
			if (h / 10 == K || h % 10 == K) {
				cnt += 3600;
				continue;
			}
			for (int m = 0; m < 60; m++) {
				if (m / 10 == K || m % 10 == K) {
					cnt += 60;
					continue;
				}

				for (int s = 0; s < 60; s++) {
					if (s / 10 == K || s % 10 == K)
						cnt++;
				}
			}
		}
		System.out.println(cnt);

	}
}
