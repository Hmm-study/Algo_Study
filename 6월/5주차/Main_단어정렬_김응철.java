import java.util.*;
import java.io.*;

public class Main_단어정렬_1181 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] strArr = new String[N];
        for(int i=0;i<N;i++){
            strArr[i] = br.readLine();
        }

        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() == o2.length()){
                    return o1.compareTo(o2);
                }
                return o1.length() - o2.length();
            }
        });

        strArr = Arrays.stream(strArr).distinct().toArray(String[]::new);

        for(String str : strArr){
            System.out.println(str);
        }

    }
}
