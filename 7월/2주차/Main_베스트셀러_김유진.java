import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B1302_베스트셀러_1 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashMap<String, Integer> map = new HashMap<>();
		for(int i = 0; i < N;i++) {
			String title = br.readLine();
			if(map.containsKey(title))
				map.put(title, map.get(title) + 1);
			else
				map.put(title, 1);
		}
		
		
		String ans = map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() == entry2.getValue() ? -entry1.getKey().compareTo(entry2.getKey()) : Integer.compare(entry1.getValue(), entry2.getValue())).get().getKey();
		System.out.println(ans);
	}
}
