import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DP문제
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
		
		int tc = Integer.parseInt(st.nextToken());
		for (int t = 0; t < tc; t++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			
			arr = new int[2][n+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arr[0][i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arr[1][i] = Integer.parseInt(st.nextToken());
			}
//			System.out.println(Arrays.toString(arr[0]));
			
			dp = new int[2][n+1];
			
			dp[0][1] = arr[0][1];
			dp[1][1] = arr[1][1];
			
			// 현재 좌표의 최대값은
			// 반대행의 j-1과 j-2 중 최대값에 현재 좌표 값을 더하면 된다
			// 표에 그림을 그렸을 때, j열의 값은 (i-1, j-1) (i-1, j-2) 스티커의 영향만 받는다
			// 점화식은 A(n) = A(n-2) + A(n-1)
			for (int j = 2; j <= n; j++) {
				dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
				dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
			}
			
			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
		
	}

}