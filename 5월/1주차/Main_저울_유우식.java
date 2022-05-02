import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2437 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for(int i=0; i<n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        //배열 정렬
        int sum = 1;
        //양의 정수의 최소값은 1
        for(int i=0; i<n; i++){
            if(sum>=arr[i])
                sum+=arr[i];
            else
                break;
        }
        //추의 누적합 -> 추로 만들 수 있는 수의 최대값 + 양의정수 최소값 1, 1~누적합까지 추로 다 만들 수 있음
        //정렬한 배열의 다음 요소가 누적합보다 크다면, 누적합 이후로 추로 측정할 수 없기 때문에
        //배열 내 누적합 + 양의 정수 최소값이 추로 더이상 측정할 수 없는 최소값이 됨
        System.out.println(sum);
    }
}
