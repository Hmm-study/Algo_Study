package mon5;

import java.util.Scanner;

/*
* [정규 표현식]
* 1. [a,b,c] : a,b,c,중 하나의 문자
* 2. [a-d] : a부터 d중 하나의 문자
* 3. ? : 없거나 한개
* 4. + : 한개 이상
* 5. & : 문자열 종료
* 6. matches : 패턴을 해석하고 문자열에 대해 일치하는지 확인!
*
* */
public class Main_9342_염색체 {
    public static void main(String[] args) {

        Scanner sc= new Scanner(System.in);

        int N = sc.nextInt();
        String ss= "^[A-F]?A+F+C+[A-F]?&";
        for (int i = 0; i < N; i++) {
            String st = sc.next();
            if(st.matches(ss)){
                System.out.println("Infected!");
            }else{
                System.out.println("Good");
            }
        }
    }
}
