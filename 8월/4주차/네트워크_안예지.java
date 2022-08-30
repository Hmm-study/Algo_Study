
package mon8;

class Programmers_네트워크 {
    static boolean check[];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        check = new boolean [n];
        
        for(int i=0; i<computers.length ; i++){
            if(!check[i]){
                answer++;
                dfs(i,computers);
                
            }
        }
        
        return answer;
    }
    
    public static void dfs(int now, int[][] computers){
        check[now] = true;
        
        for(int i=0; i<computers.length ; i++){
            if(!check[i] && computers[now][i]==1){
                dfs(i,computers);
            }
        }
    }
}