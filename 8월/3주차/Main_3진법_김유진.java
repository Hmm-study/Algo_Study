import java.util.*;
class Solution {
    public int solution(int n) {
        LinkedList<Integer> tmp = new LinkedList<>();
        while(n!=0){
            tmp.add(n%3);
            n/=3;
        }
        
        int answer = 0;
        int com = 1;
        int len = tmp.size();
        for(int i = len-1;i>=0;i--){
            answer += com * tmp.get(i);
            com*=3;
        }

        return answer;
    }
}