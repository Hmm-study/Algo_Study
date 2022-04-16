import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    static Node[] node;
    static boolean[] chk;
    public int solution(int n, int[][] edge) {
        node = new Node[n+1];
        chk = new boolean[n+1];
        int nIdx = n + 1;
        for(int i = 1; i < nIdx; i++){
            node[i] = new Node();
        }
        
        for(int [] e : edge){
            int a = e[0];
            int b = e[1];
            node[a].edges.add(b);
            node[b].edges.add(a);
        }
        
        return find();
    }
    
    static int find(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        chk[1] = true;
        int len = 0;
        while(!q.isEmpty()){
            len = q.size();
            for(int i = 0; i < len;i++){
                int tmp = q.poll();
                for(int next : node[tmp].edges){
                    if(chk[next])
                        continue;
                    else{
                        chk[next] = true;
                        q.add(next);
                    }
                }
            }
            
        }
        return len;
    }
    
    
    static class Node{
        ArrayList<Integer> edges;
        public Node(){
            this.edges = new ArrayList<Integer>();
        }
    }
}