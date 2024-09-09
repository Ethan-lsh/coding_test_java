import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

    static int N;
    static int[] weightArr, answer;
    static boolean[] visited;
    static int sumAnswer;

    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= testCase; tc++) {
            sumAnswer = 0;
            N = Integer.parseInt(br.readLine());
            
            weightArr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
            	weightArr[i] = Integer.parseInt(st.nextToken());
            
            visited = new boolean[N];
            answer = new int[N];
            orderPermutation(0);
            sb.append("#" + tc + " " + sumAnswer).append("\n");
        }
        System.out.println(sb);
    }

    public static void orderPermutation(int depth) {
        if(depth == N) {
            backSum(0, 0, 0);
        } else {
            for(int i = 0; i < N; i++) {
                if(visited[i]) {
                    continue;
                } else {
                    visited[i] = true;
                    answer[depth] = weightArr[i];
                    orderPermutation(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }

    // 부분집합을 사용하여 왼쪽과 오른쪽에 추를 올린 경우의 수를 고려한다
    public static void backSum(int cnt, int leftSum, int rightSum) {
        if (leftSum < rightSum) return;
        if (cnt == N) {
            sumAnswer++;
            return;
        }
        backSum(cnt + 1, leftSum, rightSum+answer[cnt]);
        backSum(cnt + 1, leftSum + answer[cnt], rightSum);
    }
    
}