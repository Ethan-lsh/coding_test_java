import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] numArr;
    static boolean[] boolArr;
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputArr[0];
        M = inputArr[1];
        numArr = new int[N];
        boolArr = new boolean[N];

        for(int i = 1; i <= N; i++) {
            numArr[i - 1] = i;
        }

        getSeq(0, new int[M]);
        System.out.println(sb);

    }

    public static void getSeq(int depth, int[] answer) {
        if(depth == M) {
            for(int i = 0; i < M; i++) {
                sb.append(answer[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N; i++) {
            if(boolArr[i]) {
                continue;
            } else {
                boolArr[i] = true;
                answer[depth] = numArr[i];
                getSeq(depth + 1, answer);
                boolArr[i] = false;
            }
        }
    }
    
}