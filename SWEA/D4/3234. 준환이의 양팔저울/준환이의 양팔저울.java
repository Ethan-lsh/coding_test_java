import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

    static int N;
    static int[] weightArr;
    static boolean[] visited;
    static int sumAnswer;

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= testCase; tc++) {
            sumAnswer = 0;
            N = Integer.parseInt(br.readLine());
            weightArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            visited = new boolean[N];

            orderPermutation(0, new int[N]);
            sb.append("#" + tc + " " + sumAnswer).append("\n");
        }
        System.out.println(sb);
    }

    public static void orderPermutation(int depth, int[] answer) {
        if(depth == N) {
            backSum(answer, 0, 0, 0);
        } else {
            for(int i = 0; i < N; i++) {
                if(visited[i]) {
                    continue;
                } else {
                    visited[i] = true;
                    answer[depth] = weightArr[i];
                    orderPermutation(depth + 1, answer);
                    visited[i] = false;
                }
            }
        }
    }

    public static void backSum(int[] combArr, int start, int rightSum, int leftSum) {
        if(start == N) {
            sumAnswer++;
        } else {
            leftSum += combArr[start];
            backSum(combArr, start + 1, rightSum, leftSum);
            
            leftSum -= combArr[start];
            rightSum += combArr[start];

            if(leftSum >= rightSum) {
                backSum(combArr, start + 1, rightSum, leftSum);
            }
        }
    }
    
}
