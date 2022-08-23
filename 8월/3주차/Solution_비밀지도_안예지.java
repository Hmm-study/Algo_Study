package mon8;

import java.util.*;

class Programmers_비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] answer = new String[n];

        for (int i = 0; i < arr1.length; i++) {

            String num = Integer.toBinaryString(arr1[i]);
            String num2 = Integer.toBinaryString(arr2[i]);

            char numa1[] = new char[n];
            char numa2[] = new char[n];

            while(num.length() < n){

                num = "0" + num;

            }
            numa1= num.toCharArray();

            while(num2.length() < n){

                num2 = "0" + num2;

            }

            numa2 = num2.toCharArray();

String result ="";
            for (int j = 0; j <n ; j++) {
                if(numa1[j]=='1'|| numa2[j]=='1'){
                    result+='#';
                }else if(numa1[j]=='0' && numa2[j]=='0'){
                  result+=' ';
                }

                }

                answer[i]=result;





        }  System.out.println(Arrays.toString(answer));

        return answer;
    }
}