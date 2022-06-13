package mon6;

import java.util.Scanner;

public class Main_1343_폴리오미노 {

    static StringBuilder sb= new StringBuilder();
    static int Xcnt;
    static boolean plug;
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);


        String pol = sc.next();

        int len = pol.length();

        Xcnt=0;
        for (int i = 0; i < len; i++) {
            if(pol.charAt(i)=='.'){
                if(Xcnt>0){ // 바꿔야할 X들이 있음
                    chang(); // 점을 만나면 그동안 모아진 X값들을 바꾸러감
                    Xcnt=0;
                }
                sb.append('.');

            }else{
                Xcnt++; // X 카운트
            }
        }
        if(Xcnt>0){
            chang();
        }

        if(plug){
            System.out.println("-1");
        }else{
            System.out.println(sb.toString());
        }

    }

    private static void chang() {
        while (true){
            if(Xcnt==0) break;
            if(Xcnt-4>=0){
                sb.append("AAAA");
                Xcnt-=4;
            } else if (Xcnt-2>=0) {
                sb.append("BB");
                Xcnt-=2;
            }else{
                plug=true;
                break;
            }

        }


    }

}
