import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int len = nums.length;
        int answer = 0;
        HashMap<Integer,Boolean> map = new HashMap<>();
        for(int i = 0; i<len;i++){
            if(!map.containsKey(nums[i])){
                map.put(nums[i],true) ;
                answer++;
                if(answer>= len/2)
                    break;
            }
        }
        return answer >= len/2 ? len/2 : answer;
    }
}