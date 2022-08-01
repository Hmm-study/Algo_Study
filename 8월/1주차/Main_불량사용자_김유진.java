import java.util.HashSet;
class Solution {
    static int  B, U;
    static boolean[] chk;
    static String[] u;
    static String[] b;
    static HashSet<String> hs;
    public int solution(String[] user_id, String[] banned_id) {
        u = user_id;
        b = banned_id;
        B = b.length;
        U = u.length;
        chk = new boolean[U];
        hs = new HashSet<>();
        dfs(0);
    
        return hs.size();
    }
    
    static void dfs(int dep){
        if(dep == B){
            StringBuilder sb = new StringBuilder();
            for(boolean aaaa : chk){
                if(aaaa)
                    sb.append("1");
                else
                    sb.append("0");
                
            }
            hs.add(sb.toString());
            
            return;
        }
        
        char[] nowBan = b[dep].toCharArray();
        
        for(int uIdx = 0; uIdx <U;uIdx++){
            if(chk[uIdx])
                continue;
            char[] nowUser = u[uIdx].toCharArray();
            if(nowBan.length != nowUser.length)
                continue;
            
            if( comchk(nowBan, nowUser)){
                chk[uIdx] = true;
                dfs(dep+1);
                chk[uIdx] = false;
            }
            
        }
        
    }
    
    static boolean comchk(char[] a,  char[] b){
        int len = a.length;
        for(int l = 0; l < len; l++){
            if(a[l] == '*')
                continue;
            if(a[l] != b[l])
                return false;
        }
        return true;
    }
    
}