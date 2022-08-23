package mon8;

class Programmers_약수의개수와덧셈 {
    public int solution(int left, int right) {
        int answer = 0;
        
        for(int i=left ; i<= right ; i++ ){
            // System.out.println(i/2);
            int cnt;
            if(i==1) cnt=1;
            else {cnt = 2;
            for(int j = 2 ; j<=i/2 ; j++){
                if(i%j==0) cnt++;
            }
            // System.out.println(i+" "+cnt);
                 }
            answer = (cnt%2==0 ? answer+i : answer-i);
        }
        
        return answer;
    }
}