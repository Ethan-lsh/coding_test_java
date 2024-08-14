import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int M;
    static int[] numArr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = temp[0];
        M = temp[1];
        int sum = 0;
        int answer = -1;

        for(int i = 0; i < N-2; i++) {
            for(int j = i + 1; j < N-1; j++) {
                for(int k = j + 1; k < N; k++) {
                    sum = numArr[i] + numArr[j] + numArr[k];
                    if(answer == -1 && M >= sum) {
                        answer = sum;
                    } else if (M >= sum && sum > answer){
                        answer = sum;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}