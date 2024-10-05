import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DP를 이용한 문제 풀이
 * 삼각형의 아래에서부터 위로 올라가면서 최대값을 구한다
 * DFS로 풀면 시간초과 발생
 * @author Sanghyeon Lee
 *
 */
public class Main {

	static int n;
	static int[][] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dp[n-1]에 arr[n-1] 값을 복사
		System.arraycopy(arr[n-1], 0, dp[n-1], 0, arr[n-1].length);
		
		System.out.println(find(0, 0));
		
	}
	
	/**
	 * 밑에서부터 값을 저장해가며 최대값을 구하는 방식을 사용
	 * i-1행의 j, j-1의 값 중 최대 값을 골라서 (i, j)에 값을 더해주는 방식
	 * @param depth
	 * @param idx
	 * @return
	 */
	private static int find(int depth, int idx) {
		if (depth == n - 1) return dp[depth][idx];
		
		if (dp[depth][idx] == 0) {
			dp[depth][idx] = Math.max(find(depth+1, idx), find(depth+1, idx+1)) + arr[depth][idx];
		}
		
		return dp[depth][idx];
	}
	
}