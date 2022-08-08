import java.util.*;
class Solution {
    public String solution(String p) {
        return dfs(p);
    }
    
    static String dfs(String p){
        if(p.equals(""))
            return "";
        
        int len = p.length();
        int openCnt = 0;
        int closeCnt = 0;
        boolean correct = true;
        String u = "";
        String v = "";
        for(int i = 0; i< len; i++){
            if(p.charAt(i) == '(')
                openCnt++;
            else
                closeCnt++;
            
            if(openCnt < closeCnt)
                correct = false;
            
            if(openCnt == closeCnt){
                u = p.substring(0,i+1);
                v = p.substring(i+1, len);
                break;
            }
        }
        
        if(correct)
            return u + dfs(v);
        else{
            String newU = "";
            int tmplen = u.length();
            if(tmplen > 2){
                char[] tmp = u.substring(1,tmplen-1).toCharArray();
                for(int i = 0; i< tmp.length;i++){
                    tmp[i] = tmp[i] == '(' ? ')' : '(';
                }
                newU += new String(tmp);
            }
            return "(" + dfs(v) + ")" + newU;
        }
    }
}