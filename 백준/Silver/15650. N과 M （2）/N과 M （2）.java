import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int M;
    static int[] numArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];
        numArr = new int[N];

        for(int i = 1; i <= N; i++) {
            numArr[i - 1] = i;
        }

        combination(0, 0, new int[M]);
        System.out.print(sb);
        
    }

    public static void combination(int start, int cnt, int[] answer) {
        if(cnt == M) {
            for(int i = 0; i < M; i++) {
                sb.append(answer[i] + " ");
            }
            sb.append("\n");
        } else {
            for(int i = start; i < N; i++) {
                answer[cnt] = numArr[i];
                combination(i + 1,cnt + 1, answer);
            }
        }
    }

}