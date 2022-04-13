import java.util.*;
import java.util.Map.Entry;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, HashSet<String>> map = new HashMap<>();
        HashMap<String, Integer> record = new HashMap<>();
        int len = id_list.length;
        for(int i = 0  ;i <len;i++){
            map.put(id_list[i],new HashSet<String>());
            
        }
        for(String line : report){
            String[] tmps = line.split(" ");
            map.get(tmps[0]).add(tmps[1]);
            
        }
        
        for(int i = 0; i < len; i++){
            for(String name : map.get(id_list[i])){
                if(record.containsKey(name)){
                    record.put(name,record.get(name) + 1);
                }else{
                    record.put(name, 1);
            }
            }
        }
        
        // for(String key : record.keySet()){
        //     System.out.println(key + ", " + record.get(key));
        // }
        
        
        int[] ans = new int[len];
        for(int i = 0 ; i < len;i++){
            int cnt = 0;
            for(String key : map.get(id_list[i])){
                
                if(record.get(key) >= k)
                    cnt++;
                // System.out.println(key + ", " + record.get(key));
            }
            ans[i] = cnt;
        }
     
        
        return ans;
    }
}