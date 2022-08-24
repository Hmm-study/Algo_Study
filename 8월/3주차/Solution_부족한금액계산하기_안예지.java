package mon8;

class Programmers_부족한금액계산하기 {
    public long solution(int price, int money, int count) {
        long answer = -1;

        long sum = 0;
        for(int i = 1 ; i<= count ; i++){
            sum += price *i;
        }
        
        if(money>sum){
            answer = 0;
        }else{
            answer = sum - money;
        }
        
        return answer;
    }
}