import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ_9342 {

    static int N;
    static String wrong = "Good";
    static String correct = "Infected!";


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++){
            if(XY(br.readLine())){
                System.out.println(correct);
            }else{
                System.out.println(wrong);
            }
        }



    }

    public static boolean XY(String s){
        String pattern = "^[A-F]?A+F+C+[A-F]?$";
        boolean check = s.matches(pattern);
        return check;
    }
}
