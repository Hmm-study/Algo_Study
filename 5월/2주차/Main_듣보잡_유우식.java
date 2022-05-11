import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;


public class BOJ_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] num = br.readLine().split(" ");
        int N = Integer.parseInt(num[0]);
        int M = Integer.parseInt(num[1]);


        HashSet<String> set = new HashSet<>();
        for(int i=0; i<N; i++){
            set.add(br.readLine());
        }
        ArrayList<String> result = new ArrayList<>();
        for(int i=0; i<M; i++){
            String tmp = br.readLine();
            if(set.contains(tmp)){
                result.add(tmp);
            }
        }

        Collections.sort(result);

        System.out.println(result.size());
        for(String s : result){
            System.out.println(s);
        }




    }
}
