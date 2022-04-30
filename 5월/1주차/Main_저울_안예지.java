import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2437_저울 {

    public static void main(String[] args) throws IOException {

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int arr[]= new int[N];
        StringTokenizer st= new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < N; i++) {
            arr[i]= Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int sum=0;
        for (int i = 0; i < N; i++) {
            if(sum+1>= arr[i]){
                sum+=arr[i];
            }else {
//                sum+=1; -> 왜 여기다가 넣으면 틀린걸까.. 아시는분 알려주세요
                break;
            }
        }
        System.out.println(sum+1);
    }
}
