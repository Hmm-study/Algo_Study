package 프로그래머스;

public class 카카오프렌즈컬러링북 {

    static int max;
    static int maxCount;
    static int count = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public int[] solution(int m, int n, int[][] picture) {
        max =0;
        maxCount =0;
        int[] answer = new int[2];
        answer[0] = max;
        answer[1] = maxCount;

        boolean[][] check = new boolean[m][n];

        for(int i =0;i<m;i++){
            for(int j=0;j<n;j++){
                if(picture[i][j] != 0 && !check[i][j]){
                    max++;
                    dfs(i,j,picture,check);
                }
                if(count > maxCount) maxCount = count;
                count = 0;
            }
        }

        answer[0] = max;
        answer[1] = maxCount;

        return answer;
    }

    public static void dfs(int x,int y, int[][] picture, boolean[][] check){
        if(check[x][y]) return;

        check[x][y] = true;
        count++;

        for(int i =0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=picture.length || ny<0 || ny>=picture[0].length) continue;

            if(picture[x][y] == picture[nx][ny] && !check[nx][ny]){
                dfs(nx,ny,picture,check);
            }
        }
    }
}
