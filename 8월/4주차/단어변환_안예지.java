package mon8;

class Programmers_단어변환 {
    static int answer =0;
    static boolean check[];
    public int solution(String begin, String target, String[] words) {
        check = new boolean [words.length];
        dfs(begin,target,words,0);
        return answer;
    }
    public static void dfs(String begin, String target, String[] words, int cnt){
        if(begin.equals(target)){
            answer=cnt;
            return;
        }
        
        for(int i=0; i<words.length; i++){
            if(check[i]) continue;
            String ss = words[i];
            int cha = 0;
            for(int j=0; j<words[i].length() ; j++){
                if(begin.charAt(j)==ss.charAt(j))
                    cha++;
            }
            if(cha == ss.length()-1){
                check[i] = true;
                // for(int k=0 ; k<check.length; k++){
                //     System.out.print(check[k]+" ");
                // }
                // System.out.println(cnt+" "+words[i]);
                dfs(ss,target,words,cnt+1);
                check[i] = false;
            }
        }
    }
}