package day04;

import java.util.Scanner;

public class Main_17609_회문 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int tc = 0; tc < T; tc++) {

			// 원본 문자열
			String st = sc.next();

			StringBuilder sb = new StringBuilder(st);

			// 반대로 돌린 문자열
			String con = sb.reverse().toString();

			// 원본 문자열도 빌더에 저장
			StringBuilder conSb = new StringBuilder(st);
			if (con.equals(st)) {
				System.out.println("0");
			} else {
				for (int i = 0; i < st.length(); i++) {
					if (st.charAt(i) != con.charAt(i)) {
						// 반대로 돌린 문자열에서 하나 빼서 비교해보기

						String aa = sb.deleteCharAt(i).toString();
						String bb = sb.reverse().toString();

						if (aa.equals(bb))
							System.out.println("1");
						else {
							// 원본 문자열에서 하나 빼고 비교해보기
							aa = conSb.deleteCharAt(i).toString();
							bb = conSb.reverse().toString();
							if (aa.equals(bb)) {
								System.out.println("1");
							} else {
								// 둘다  아니라면 2 반환
								System.out.println("2");
							}
						}

						break;
					}
				}

			}

		}
	}

}
