import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17609 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String word = "";
        int answer=0;

        for(int t=0; t<T; t++){
            word = br.readLine();
            answer = solve(word);
            if(answer>=2)
                System.out.println(answer-1);
            else
                System.out.println(answer);
        }


    }

    public static int solve(String word){
        int answer = 0;
        int left=0;
        int right=word.length()-1;
        while(left<=right) {
            if (word.charAt(left) == word.charAt(right)) {
                left++;
                right--;
            } else {
                answer=1;
                int l = left+1;
                int r = right;

                while (l <= r) {
                    if (word.charAt(l) == word.charAt(r)) {
                        l++;
                        r--;
                    } else {
                        answer++;
                        break;
                    }
                }

                l = left;
                r = right-1;
                while (l <= r) {
                    if (word.charAt(l) == word.charAt(r)) {
                        l++;
                        r--;
                    } else {
                        answer++;
                        break;
                    }
                }
                return answer;
            }
        }
        return answer;
    }
}
