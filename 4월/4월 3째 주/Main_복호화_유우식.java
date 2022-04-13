import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9046 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int num = Integer.parseInt(br.readLine());
        for(int i=0; i<num; i++){
            str = br.readLine().replace(" ","");
            int max=0;
            int cnt=0;
            int[] arr = new int[26];
            for(int j=0; j<str.length(); j++){
                arr[str.charAt(i)-'a']++;
            }
            for(int k=0; k<arr.length; k++){
                if(arr[max]<arr[k])
                    max=k;
            }

            for(int k=1; k<arr.length; k++){
                if(arr[max]==arr[k]){
                    cnt++;
                }
            }
            char answer = (char)(max+'a');


            if(cnt>1)
                System.out.println("?");
            else System.out.println(answer);
        }

    }



}
