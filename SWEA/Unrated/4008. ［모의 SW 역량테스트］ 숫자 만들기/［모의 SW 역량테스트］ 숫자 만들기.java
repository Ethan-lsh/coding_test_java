import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;                              // N: 숫자의 개수
	static int plus, minus, product, divide;   // 각 연산자의 개수
	static int[] numbers;                      // 숫자 배열
	static int max, min;                       // 최소값과 최대값

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test_case = Integer.parseInt(br.readLine());
		for (int tc=1; tc <= test_case; tc++) {
			
			N = Integer.parseInt(br.readLine());

			max = -100000000; min = 100000000;  // 최소값과 최대값의 임의의 값 (문제 조건을 따름)
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 각 연산자의 실행 횟수
			plus = Integer.parseInt(st.nextToken());
			minus = Integer.parseInt(st.nextToken());
			product = Integer.parseInt(st.nextToken());
			divide = Integer.parseInt(st.nextToken());
			
			// 숫자 배열을 생성
			numbers = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}

			// 각 연산자 개수에 따른 DFS를 실행한다
			// parameter로 각 연산자의 남은 개수와 현재 합 그리고 사용한 숫자의 개수를 전달한다
			dfs(plus, minus, product, divide, numbers[0], 1);
			
			System.out.println("#" + tc + " " + Math.abs(max - min));
		}
	}

	/**
	 * 재귀 호출을 사용해 선택된 연산자에 따른 결과값을 인자로 전달한다.
	 * @param s plus의 남은 개수
	 * @param m minus의 남은 개수
	 * @param p product의 남은 개수
	 * @param d divide의 남은 개수
	 * @param sum 현재 숫자 결과 값
	 * @param cnt 선택된 숫자의 개수
	 */
	private static void dfs(int s, int m, int p, int d, int sum, int cnt) {
		if (cnt == N) {
			if (sum < min) min = sum;
			if (sum > max) max = sum;
		}
		
		// 사용가능한 연산자가 남아있다면 개수를 1차감하고 dfs를 실행
		if (s > 0) dfs(s-1, m, p, d, sum+numbers[cnt], cnt+1);
		if (m > 0) dfs(s, m-1, p, d, sum-numbers[cnt], cnt+1);
		if (p > 0) dfs(s, m, p-1, d, sum*numbers[cnt], cnt+1);
		if (d > 0) dfs(s, m, p, d-1, sum/numbers[cnt], cnt+1);
		
	}

}