import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int[][] dp = new int[3][N+1];
		
		dp[0][1] = sc.nextInt();  // 주어진 수열을 저장하는 배열
		dp[1][1] = 1;             // n번째 값이 (n-1) 보다 큰지 체크하는 배열 (오름차순)
		dp[2][1] = 1;             // (n-1)번째 값이 n 보다 큰지 체크하는 배열 (내림차순)
		
		int max = 1;
		for (int i = 2; i <= N; i++) {
			dp[0][i] = sc.nextInt();
			
			// 값이 증가하는가
			if (dp[0][i] >= dp[0][i-1]) {
				dp[1][i] = dp[1][i-1] + 1;
			} else {
				dp[1][i] = 1;
			}
			
			// 값이 감소하는가
			if (dp[0][i] <= dp[0][i - 1]) {
				dp[2][i] = dp[2][i-1] + 1;
			} else {
				dp[2][i] = 1;
			}
			
			max = Math.max(max,  Math.max(dp[1][i], dp[2][i]));
		}
		
		
		System.out.println(max);
		
		
	}

}
