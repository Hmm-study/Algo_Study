import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main_파일_탐색기_김유진 {
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Block[] ans = new Block[N];
        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            Pattern p = Pattern.compile("[a-zA-Z]|([0-9])+");
            Matcher m = p.matcher(line);
            ArrayList<String> tmp = new ArrayList<>();
            while (m.find())
                tmp.add(m.group());
            int len = tmp.size();
            ans[n] = new Block(line, len);
            for (int i = 0; i < len; i++) {
                ans[n].block[i] = tmp.get(i);
            }
        }

        Arrays.sort(ans);

        StringBuilder sb = new StringBuilder();
        for (Block a : ans) {
            sb.append(a.origin).append("\n");
        }

        System.out.println(sb.toString());

    }

    static class Block implements Comparable<Block> {
        String origin;
        String[] block;

        public Block(String origin, int blockSize) {
            this.origin = origin;
            this.block = new String[blockSize];
        }

        @Override
        public int compareTo(Block o) {
            int len = Math.min(this.block.length, o.block.length);

            for (int i = 0; i < len; i++) {
                String tString = this.block[i];
                String oString = o.block[i];
                if (tString.matches("[a-zA-Z]") && oString.matches("[a-zA-Z]")) { // 둘 다 문자인 경우
                    int StringCompare = tString.toUpperCase().compareTo(oString.toUpperCase());
                    if (StringCompare != 0) // 문자가 다른 경우
                        return StringCompare;
                    else { // 문자가 같은 경우
                        int charCode = tString.charAt(0) - oString.charAt(0);
                        if (charCode != 0)
                            return charCode;
                    }
                } else if (tString.matches("([0-9])+") && oString.matches("([0-9])+")) { // 둘 다 숫자인 경우
                    String newtString = "";
                    String newOString = "";
                    int stringlen = Integer.compare(tString.length(), oString.length());
                    if (stringlen != 0) {
                        int diff = Math.abs(tString.length() - oString.length());
                        String exZero = "";
                        for (int z = 0; z < diff; z++) {
                            exZero += "0";
                        }
                        if (tString.length() < oString.length()) {
                            newtString = exZero + tString;
                            newOString = oString;
                        } else {
                            newOString = exZero + oString;
                            newtString = tString;
                        }
                    }else{
                        newtString = tString;
                        newOString = oString;
                    }
//                    int compareNum = tString.replaceAll("^0+", "").compareTo(oString.replaceAll("^0+", ""));
                    int compareNum = newtString.compareTo(newOString);
//                    System.out.println(newtString +", " +newOString+ ","+compareNum);
                    if (compareNum == 0) { // 앞에 0 붙인 숫자가 같은 경우
                        int compareLen = Integer.compare(tString.length(), oString.length());
                        if (compareLen != 0) // 숫자가 같지만 길이가 다른 경우 (0 개수)
                            return compareLen;
                    }else // 숫자가 다른 경우
                        return compareNum;
                } else { // 둘 중 하나가 숫자인 경우
                    return tString.compareTo(oString);
                }
            }


            return Integer.compare(this.block.length, o.block.length);

        }
    }
}
