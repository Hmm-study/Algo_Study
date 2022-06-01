import java.io.*;

public class Main_그룹단어체커_1316 {
    public static void main(String[] args) throws IOException {
        //

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int n = 0; n < N; n++) {
            boolean flag = false;
            String str = br.readLine(); // ccazzzzbb, aabbbccb
            String tmp = "";
            char[] ch = str.toCharArray();
            for (int i = 1; i < str.length(); i++) {
                if(ch[i] != ch[i-1]){
                    if(tmp.contains(Character.toString(ch[i]))) {
                        flag = true;
                        break;
                    }else{
                        tmp += Character.toString(ch[i]);
                    }
                }
            }
            if(tmp.contains(Character.toString(ch[0])))
                flag = true;

            if (!flag)
                cnt++;
        }

        System.out.println(cnt);

    }
}
