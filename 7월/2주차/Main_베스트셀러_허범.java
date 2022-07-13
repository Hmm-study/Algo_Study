package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 베스트셀러_1302 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        HashMap<String, Integer> book = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String title = br.readLine();

            if(!book.containsKey(title))
                book.put(title, 1);
            else
                book.put(title, book.get(title) + 1);
        }

        List<Map.Entry<String, Integer>> list = new LinkedList<>(book.entrySet());
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() - o2.getValue();
            }
        });
//        for(Map.Entry<String, Integer> i : list)
//            System.out.println(i.getKey() + " + " + i.getValue());
        System.out.println(list.get(list.size()-1).getKey());
    }
}
