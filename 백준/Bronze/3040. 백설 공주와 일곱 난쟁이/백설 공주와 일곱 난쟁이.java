import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] numArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numArr = new int[9];
        int sum = 0;
        int fake1 = 0;
        int fake2 = 0;

        for(int i = 0; i < 9; i++) {
            numArr[i] = Integer.parseInt(br.readLine());
            sum += numArr[i];
        }

        Tag : for(int i = 0; i < 8; i++) {
            for(int j = i + 1; j < 9; j ++) {
                int sum_temp = numArr[i] + numArr[j];
                if (sum - sum_temp == 100) {
                    fake1 = i;
                    fake2 = j;
                    break Tag;
                }
            }
        }

        for(int i = 0; i < 9; i++) {
            if(i == fake1 || i == fake2) {
                continue;
            }
            System.out.println(numArr[i]);
        }

    }
    
}