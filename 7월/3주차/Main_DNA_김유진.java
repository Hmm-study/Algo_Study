package silver5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1969_DNA_1 {
	static int[][] cnt;
	static int N, M;
	static char[] compareChar = { 'A', 'C', 'G', 'T' };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		cnt = new int[M][4];
		for (int n = 0; n < N; n++) {
			char[] line = br.readLine().toCharArray();
			for (int m = 0; m < M; m++) {
				switch (line[m]) {
				case 'A':
					cnt[m][0]++;
					break;
				case 'C':
					cnt[m][1]++;
					break;
				case 'G':
					cnt[m][2]++;
					break;
				case 'T':
					cnt[m][3]++;
					break;
				}
			}
		}
		int hamDis = 0;
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			int mcnt = 0;
			int midx = 0;
			for (int idx = 0; idx < 4; idx++) {
				if (cnt[m][idx] > mcnt) {
					mcnt = cnt[m][idx];
					midx = idx;
				}
			}
			
			sb.append(compareChar[midx]);
			
			for (int idx = 0; idx < 4; idx++) {
				if (idx == midx)
					continue;
	
				hamDis += cnt[m][idx];
			}

		}
		
		System.out.println(sb.toString());
		System.out.println(hamDis);
	}
}
