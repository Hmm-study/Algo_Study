package mon5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// for문으로 했으나 시간초과... 그래서 Map을 써서 다시 품
public class Main_1764_듣보잡2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine()," ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int len= N+M;
        Map<String,Integer> map= new HashMap<>();
        Queue <String> qu = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            String ss= br.readLine();
            // 똑같은 키값 발견 했으면 중복 큐에다 저장
            if(map.containsKey(ss)){
                qu.add(ss);
            }else{
                map.put(ss,0);
            }
        }


        System.out.println(qu.size());
        int size= qu.size();
        String arr[]= new String[size];
        int cnt=0;
        while (!qu.isEmpty()){
           arr[cnt]= qu.poll();
            cnt++;
        }

        Arrays.sort(arr);

        for(int i=0; i<arr.length;i++){
            System.out.println(arr[i]);
        }


    }
}
