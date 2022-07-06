package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 비밀번호발음하기_4659 {

    static boolean haveMo(List<Character> list){
        return (list.contains('a') || list.contains('e') || list.contains('i') || list.contains('o') || list.contains('u'));
    }

    static boolean isMo(Character character){
        return (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u');
    }

    static boolean check(String str){
        char arr[] = str.toCharArray();
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < arr.length; i ++)
            list.add(arr[i]);

        if(!haveMo(list))
            return false;

        for(int i = 2; i < arr.length; i++){
            if(isMo(arr[i])){
                if(isMo(arr[i-1]) && isMo(arr[i-2]))
                    return false;
            }else {
                if(!isMo(arr[i-1]) && !isMo(arr[i-2]))
                    return false;
            }
        }
        for(int i = 1; i < arr.length; i++){
            if(arr[i] == arr[i -1]){
                if(arr[i] != 'e' && arr[i] != 'o')
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            String str = br.readLine();
            if(str.equals("end"))
                break;

            sb.append("<").append(str).append("> is ");
            if(!check(str))
                sb.append("not ");
            sb.append("acceptable.").append("\n");
        }

        System.out.println(sb);
    }
}
