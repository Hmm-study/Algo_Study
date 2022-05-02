import java.util.Arrays;
import java.util.Scanner;

public class BOJ_11651 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for(int i=0; i<n; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            arr[i][0] = x;
            arr[i][1] = y;
        }//input

        //람다식 활용
        Arrays.sort(arr, (arr1, arr2) -> {
            if(arr1[1] == arr2[1]){
                return arr1[0] - arr2[0];
            }
            else {
                return arr1[1] - arr2[1];
            }
        });
        //arr배열에서 첫번째 요소를 arr1, 두번쌔 요소를 arr2로 지정하고,
        //각 배열의 1번 요소(y값)을 비교
        //y값이 같다면 x값 비교하여 리턴
        //리턴값이 양수면 위치를 바꿈, 음수면 위치를 바꾸지 않음
        for(int i=0; i<n; i++){
            System.out.println(arr[i][0] + " "+ arr[i][1]);
        }
    }
}
