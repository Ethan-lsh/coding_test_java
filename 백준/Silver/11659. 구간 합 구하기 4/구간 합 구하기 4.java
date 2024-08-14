import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int M;
    static int allSum;
    static int[] sumArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input1[0];
        M = input1[1];
        sumArr = new int[N + 1];

        int[] inputArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < N; i++) {
            allSum += inputArr[i];
            sumArr[i + 1] = allSum;
        }

        for(int i = 0; i < M; i++) {
            int[] input2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = input2[0];
            int end = input2[1];

            sb.append(sumArr[end] - sumArr[start - 1] + "\n");
        }
        
        System.out.print(sb);

    }
    
}