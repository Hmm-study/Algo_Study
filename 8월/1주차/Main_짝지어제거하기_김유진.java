import java.util.Stack;
import java.util.LinkedList;
class Solution
{   static Stack<Character> stack;
    public int solution(String s)
    {
        stack = new Stack<>();
        char[] input = s.toCharArray();
        int len = input.length;
        int idx = 0;
        while(idx < len){
            if(stack.isEmpty() || stack.peek() != input[idx]){
                stack.push(input[idx]);
                idx++;
            }
            
            
            if(idx < len && stack.peek() == input[idx]){
                stack.pop();
                idx++;
            }
        }
        
       
        if(stack.isEmpty())
            return 1;
        else
            return 0;
     
    }
}