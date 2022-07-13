package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1302_베스트셀러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();


        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            if(map.containsKey(st)){
                map.put(st,map.get(st)+1);

            }else{
                map.put(st,1);
            }

        }

        List<String> keySet = new ArrayList<>(map.keySet());

        //value 값으로 오름차순 정렬
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1)==(map.get(o2))){
                    return  o1.compareTo(o2);
                }
                return map.get(o2)- map.get(o1);
            }
        });

        // 내림 차순
//        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));


//        for (String key:
//             keySet) {
//            System.out.print(map.get(key)+" ");
//            System.out.println(key);
//        }
        System.out.println(keySet.get(0));

    }
}
