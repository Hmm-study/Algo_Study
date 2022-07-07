import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class B4659_비밀번호_발음하기_1 {
	static Pattern p1 = Pattern.compile("a|u|i|o|e");
	static Pattern p2 = Pattern.compile("[bcdfghjklmnpqrstvwxyz]{3,}");
	static Pattern p3 = Pattern.compile("[auioe]{3,}");
	static Pattern p4 = Pattern.compile("aa|bb|cc|dd|ff|gg|hh|ii|jj|kk|ll|mm|nn|pp|qq|rr|ss|tt|uu|vv|ww|xx|yy|zz");
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String tmp = br.readLine();
			if ("end".equals(tmp))
				break;

			sb.append("<" + tmp + ">");
			if (chkInput(tmp))
				sb.append(" is acceptable.").append("\n");
			else
				sb.append(" is not acceptable.").append("\n");
		}

		System.out.println(sb);
	}

	static boolean chkInput(String s) {
		if (!p1.matcher(s).find() || p2.matcher(s).find() || p3.matcher(s).find() || p4.matcher(s).find())
			return false;
		
		return true;
	}
}
