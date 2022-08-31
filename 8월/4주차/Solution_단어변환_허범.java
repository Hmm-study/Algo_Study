import java.util.*;

class Solution {
    
    int answer;
    boolean[] visit;
    
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        visit = new boolean[words.length];
        dfs(begin, target, 0, words);
        if(answer > 100)
            answer = 0;
        return answer;
    }
    
    public void dfs(String cur, String target, int cnt, String[] words){
        if(cur.equals(target))
            answer = Math.min(answer, cnt);
        if(cnt > words.length)
            return;
        for(int i = 0; i < words.length; i++){
            int check = 0;
            for(int j = 0; j < cur.length(); j++){
                if(cur.charAt(j) != words[i].charAt(j))
                    check++;
            }
            if(check == 1 && !visit[i]){
                visit[i] = true;
                dfs(words[i], target, cnt + 1, words);
            }
        }
    }
}