class Solution {
    public int solution(int n) {
        int answer = 0;
        String str = "";
        
        while(n != 0) {
        	str = (n %3) + str;
        	n /= 3;
        }
        
        StringBuilder sb = new StringBuilder(str);
        str  = sb.reverse().toString();
        
        answer  = Integer.parseInt(str, 3);
        
        return answer;
    }
}