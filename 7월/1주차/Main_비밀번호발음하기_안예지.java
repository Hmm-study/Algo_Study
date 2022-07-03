package mon7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_4659_비밀번호발음하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            boolean check1 = false; // 모음을 하나 사용했는 체크
            boolean check2 =true; //사용 가능한지 여부
            int mo = 0;
            int za = 0;
            String ss ="";
            String st = br.readLine();
            if(st.equals("end")) break; // end를 치면 끝
            char start = st.charAt(0);

            if(start=='a' || start =='e' || start =='i' || start=='e' || start =='o' || start =='u'){
                mo ++;
                check1=true;

            }else{
                za++;
            }

            for (int i = 1; i < st.length(); i++) {

                char ch = st.charAt(i);

                if(ch=='a' || ch =='e' || ch =='i' || ch=='e' || ch =='o' || ch =='u'){
                    mo ++;
                    check1=true;
                    za=0;
                    ss+=ch;

                }else{
                    za++;
                    mo=0;


                }

                if(mo>=3 || za>=3){ // 연속으로 3번 나왔을 경우
                    check2 =false;
                    break;

                }


                if(ch==st.charAt(i-1)){// 연속으로 두개가 왔을 경우
                    if(ch=='e' || ch=='o')continue;
                    else{
                        check2 =false;
                        break;
                    }

                }


            }

            if(!check2 || !check1){
                System.out.println("<"+st+">"+" is not acceptable.");

            }else{
                System.out.println("<"+st+">"+" is acceptable.");
            }


        }
    }
}
