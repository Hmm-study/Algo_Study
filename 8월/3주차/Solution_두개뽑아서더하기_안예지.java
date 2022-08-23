package mon8;

import java.util.*;
class Programmers_두개뽑아서더하기 {
    static int box[] = new int [2];
    static HashSet<Integer> set = new HashSet<>();
    static List <Integer> list = new LinkedList<>();
    public int[] solution(int[] numbers) {
        
        com(0,0,numbers);
        int[] answer = new int [set.size()];
        int idx = 0;
        for(int key : set){
            list.add(key);
        }
        // set이 자동 정렬인줄 알았으나 아님! 정렬해줘야함
        Collections.sort(list);
        
        for(int kk : list){
            answer[idx] = kk;
            idx++;
        }
        
             
        return answer;
    }
    
    public static void com(int cnt, int index, int [] numbers){
        
        if(cnt>=2){
            int sum = 0;
                for(int i =0 ; i< 2 ; i++){
                    sum+= box[i];
                }
             // System.out.println(sum);
            set.add(sum);
            return;
        }
        
        for(int i = index ; i<numbers.length; i++){
            box[cnt] = numbers[i];
            
            com(cnt+1, i+1, numbers);
            
        }
    }
}