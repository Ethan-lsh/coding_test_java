import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static int allSum;
    static int[] sumArr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sumArr = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            allSum += Integer.parseInt(st.nextToken());
            sumArr[i + 1] = allSum;
        }

        for(int i = 0; i < M; i++) {
            int[] input2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = input2[0];
            int end = input2[1];

            int result = sumArr[end] - sumArr[start - 1];

            sb.append(result).append("\n");
        }

        System.out.print(sb);

    }
    
}