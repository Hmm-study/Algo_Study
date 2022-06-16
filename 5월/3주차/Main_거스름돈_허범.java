import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_거스름돈_허범 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int price = Integer.parseInt(br.readLine());

        int count = 0;

        while(price>0){

            if(price%5 == 0){
                count = price/5 + count;
                break;
            }
            price -= 2;
            count++;
        }

        System.out.println((price < 0) ? -1 : count);
    }
}
