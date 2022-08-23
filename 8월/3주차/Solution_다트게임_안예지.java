package mon8;

import java.util.*;
class Programmers_다트게임 {
    public int solution(String dartResult) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < dartResult.length() ; i++){
            char ch = dartResult.charAt(i);
            int pp =0;
            if(ch-'0'>=0 && ch-'0'<=10){
                // System.out.println(ch);
                if(ch-'0'==1 && dartResult.charAt(i+1)-'0'==0){
                 stack.add(10);
                    i++;
                }else
                    stack.add(ch-'0');
            }else if(ch=='S' ){
          
            }else if(ch=='D'){
                pp=stack.pop();
                stack.add(pp*pp);
            }
            else if(ch=='T'){
                // stack.add(Math.pow(stack.pop(),3));
                pp=stack.pop();
                stack.add(pp*pp*pp);
            }else if(ch=='*'){
                int s1 = stack.pop()*2;
                if(stack.size()>=1){
                int s2 = stack.pop()*2;
                stack.add(s2);
                }
                stack.add(s1);
            }else if(ch=='#'){
                stack.add(stack.pop()*(-1));
            }
        }
        
        while(!stack.isEmpty()){
            answer+=stack.pop();
        }
        return answer;
    }
}