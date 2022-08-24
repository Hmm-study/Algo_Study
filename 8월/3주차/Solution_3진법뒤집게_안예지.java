package mon8;

import java.util.*;
class Programmers_3진법뒤집게 {
    public int solution(int n) {
        int answer = 0;
        Queue<Integer> qu = new LinkedList<>();
        while(n>=3){
            qu.add(n%3);
            n = n/3;
        }
        qu.add(n);
        for(int i = qu.size()-1 ; i>=0 ; i--){
            answer+= Math.pow(3,i)*qu.poll();
        }
        return answer;
    }
}