import java.util.ArrayList;
class Solution {
    public int solution(String s) {
        
        int len = s.length();
        ArrayList<ArrayList<String>> splitString = new ArrayList<>();
        
        int lenMax = len/2;
        
        if(len == 1)
            return 1;
        
        for(int l = 1; l <= lenMax; l++){
            splitString.add(new ArrayList<>());
            int start = 0;
            int end = l;
            
            for(int ss = 0, se = l; ss < len; ss+=l, se+=l){
                String tmp = s.substring(ss,se >= len ? len : se);
                splitString.get(l-1).add(tmp);
            }
            
        }
        
        // for(String tmp : splitString.get(0)){
        //     System.out.print(tmp);
        // }
        // System.out.println();
        
        int answer = Integer.MAX_VALUE;
        
        for(ArrayList<String> line : splitString){
            String con = line.get(0);
            int cnt = 0;
            String comp = "";
            for(String token : line){
                if(token.equals(con))
                    cnt++;
                else{
                    comp += "" + (cnt == 1 ? "" : cnt) + con;
                    con = token;
                    cnt = 1;
                }
            }
            comp += "" + (cnt == 1 ? "" : cnt) + con;
            // System.out.println(comp);
            answer = Math.min(answer, comp.length());
        }
        
        return answer;
    }
}