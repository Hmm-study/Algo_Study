package day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main_9046_복호화 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		char arr[]= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'}; 
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			
			int cntArr[]= new int[26];
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			int cnt = st.countTokens();
			int Max= 0;
			for (int i = 0; i < cnt; i++) {
				// 공백을 나눠서 문자 하나하나 검사하기
				String ss= st.nextToken();
				for (int j = 0; j <ss.length() ; j++) {
					for (int k = 0; k < arr.length; k++) {
						// 현재 문자 하나랑 알파벳 배열에 있는 해당 위치 문자와 같다면
						if(ss.charAt(j)==arr[k]) {
							// 카운트 해주기
							cntArr[k]++;
							// 최대값 기록해주기
							if(Max<cntArr[k]) Max= cntArr[k];
						}
					}
				}
				
			}
			
			int fre= 0;
			int num =0;
			for (int i = 0; i < cntArr.length; i++) {
				// 만약 최대값이 하나 이상인지 체크
				if(cntArr[i]==Max) {
					num=i;
					fre++;
				}
			}
			
			if(fre==1) {
				System.out.println(arr[num]);
			}else {
				System.out.println("?");
			}
		
			
			
		}
		
	
	}

}
