package mon8;

class Programmers_타겟넘버 {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    
    public static void dfs(int[] numbers , int target , int depth , int sum){
        if(depth == numbers.length){
            if(sum == target){
                answer++;
             }
        }else{
        dfs(numbers , target , depth+1 , sum+numbers[depth]);
        dfs(numbers , target , depth+1 , sum-numbers[depth]);
               
        }
    }
}