package mon8;

import java.util.*;
class Programmers_짝지어제거하기
{
    public int solution(String s)
    {
        int answer = 0;

        Stack<Character> sta = new Stack<>();
        
        for(int i=0; i<s.length() ; i++){
            char cc = s.charAt(i);
            if(sta.isEmpty()){
                sta.push(cc);
            }else if(sta.peek()==cc){
                sta.pop();
            }else if((sta.peek()!=cc)){
                sta.push(cc);
            }
        }
        
        if(sta.isEmpty()){
            answer =1;
        }
        return answer;
    }
}