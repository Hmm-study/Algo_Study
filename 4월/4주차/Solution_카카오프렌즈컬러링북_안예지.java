import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Programmers_Ä«Ä«¿ÀÇÁ·»ÁîÄÃ·¯¸µºÏ2 {

	public static void main(String[] args) {
//		int m =6;
//		int n=4;
//		int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
		
		int m =4;
		int n=4;
		int[][] picture =  { { 1, 1, 1, 1 }, { 1, 4, 1, 1 }, { 1, 3, 2, 1 }, { 1, 1, 1, 1 } };
		System.out.println(Arrays.toString(solution(m,n,picture)));

	}
	static Queue<Point> qu = new LinkedList<>();
	static int max=0;
	static long arr[][];
	static int da[][] = {{1,-1,0,0},{0,0,1,-1}};
	private static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
		 int[] answer = new int[2];

		arr = new long[m][n];
		for (int i = 0; i < picture.length; i++) {
			for (int j = 0; j < picture[i].length; j++) {
				arr[i][j]= picture[i][j];
			}
		}
		
		for (int i = 0; i < picture.length; i++) {
			for (int j = 0; j < picture[i].length; j++) {
				if(arr[i][j]>=1) {
					bfs(i,j,arr,m,n);
					numberOfArea++;
				}
			}
		}

		maxSizeOfOneArea=max;
		answer[0]=numberOfArea;
		answer[1]=maxSizeOfOneArea;
		return answer;
	}
	
	private static void bfs(int i, int j, long[][] arr,int m, int n) {
		qu.add(new Point(i,j));
		
		int cnt =1;
		long now = arr[i][j];
		arr[i][j]=0;
		while(!qu.isEmpty()) {
			
			Point P= qu.poll();
			int x= P.x;
			int y= P.y;
			
			for (int k = 0; k < 4; k++) {
				int X= x+da[0][k];
				int Y= y+da[1][k];
				
				if(X>=0 &&Y>=0 && X<m && Y<n && arr[X][Y]==now) {
					qu.add(new Point(X,Y));
					arr[X][Y]=0;
					cnt++;
				}
			}
			
		}
		max= Math.max(max, cnt);
	}

	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
