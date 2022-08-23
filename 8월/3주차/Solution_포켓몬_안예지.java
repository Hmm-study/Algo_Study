package mon8;

import java.util.*;
class Programmers_포켓몬 {
    static int Pcnt;
    static HashMap<Integer,Integer> list = new HashMap<>();
    public int solution(int[] nums) {
        int answer = 0;
        
        Pcnt = (nums.length/2); // 가져갈 수 있는 포켓몬 수
        int index = 0;
        for(int i=0 ; i<nums.length ; i++){
            if(!list.containsValue(nums[i])){
                list.put(index,nums[i]);
                index++;
            }
        }
    
        if(Pcnt>list.size()){
            answer = list.size();
        }else if(Pcnt<=list.size()){
           answer = Pcnt; 
        }
        return answer;
    }
    

    
  
}