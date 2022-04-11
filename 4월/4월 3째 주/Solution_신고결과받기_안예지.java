package day03;
import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        	
		HashMap<String, Integer> list = new HashMap<>();
		HashMap<String, HashSet<String>> result = new HashMap<>();
		
		for (int i = 0; i < id_list.length; i++) {
			list.put(id_list[i], 0);
			result.put(id_list[i], new HashSet());
		}
		
		//신고 받은 사람 카운트 하기
		for (int i = 0; i < report.length; i++) {
			StringTokenizer st = new StringTokenizer(report[i]," ");
			String reporter = st.nextToken(); // 신고자 
			String reported = st.nextToken(); // 신고 당한 사람
		
			
				result.get(reported).add(reporter);
			
//			System.out.println(result.toString());
			
		}
		
		for (String key : result.keySet()) {
			HashSet<String> email = result.get(key);
			
			if(email.size()>=k) {
				for (String string : email) {
					list.put(string, list.get(string)+1);
				}
			}
		}
		
		int arr[]= new int[id_list.length];
		for (int i = 0; i < arr.length; i++) {
			arr[i]=list.get(id_list[i]);
		}
		
		
		
		return arr;
    }
}