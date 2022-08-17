import java.util.*;
class Solution {
    public int[] solution(int[] numbers) {
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < numbers.length; i++){
            for(int j = 1 + i; j < numbers.length; j++){
                int a = numbers[i] + numbers[j];
                
                if(!list.contains(a))
                    list.add(a);
            }
        }
        
        int[] answer = new int[list.size()];
        
        for(int i = 0; i < list.size(); i++)
            answer[i] = list.get(i);
        
        Arrays.sort(answer);
        return answer;
    }
}