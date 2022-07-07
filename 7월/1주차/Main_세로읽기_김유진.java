import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B10798_세로읽기_1 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] saveInput = new char[5][];
		int maxLen = 0;
		for(int i = 0; i < 5; i++) {
			String tmp = br.readLine();
			maxLen = Math.max(maxLen, tmp.length());
			saveInput[i] = tmp.toCharArray();  
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < maxLen; i++) {
			for (int j = 0; j < 5; j++) {
				if(saveInput[j].length <= i)
					continue;
				if(saveInput[j][i] != ' ')
					sb.append(saveInput[j][i]);
			}
		}
		
		System.out.println(sb);
		
		
	}
}
