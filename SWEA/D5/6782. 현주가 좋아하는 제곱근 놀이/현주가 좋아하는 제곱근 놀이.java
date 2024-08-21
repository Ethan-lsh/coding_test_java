import java.util.Scanner;

public class Solution {

	static long N;
	static int cnt;
	
	static int solve() {
		int cnt = 0;
		
		while (N != 2) {
			// N이 정수의 제곱수라면 N에 루트를 취하고 cnt를 증가
			if (Math.sqrt(N) == (int)Math.sqrt(N)) {
				N = (long) Math.sqrt(N);
				cnt++;
			} 
			
			/**
			 N이 어떤 정수의 제곱이 되지 못한다면
			 N보다 큰 제곱수를 만듬과 동시에 가장 작은 정수인 root를 구한다
			 -> (int)Math.sqrt(N)를 사용해 N의 정수부를 구하고 +1을 한다
			 도출한 root의 제곱수를 맞추기위해 현재 N에 처리해야 하는, 덧셈과 제곱근을 취해야 하는 횟수를 cnt에 더한다
			 -> root*root - N : 제곱수와 N 사이에 1을 더하는 횟수
			 -> +1 : 루트를 1번 취한다
			 N을 root로 업데이트하면서 마무리
			 */
			else {
				int root = (int)Math.sqrt(N) + 1;
				cnt += root*root - N + 1;
				N = root;
			}
		}
		
		return cnt;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		for (int tc=1; tc <= test_case; tc++) {
			
			N = sc.nextLong();
			
			System.out.println("#" + tc + " " + solve());
		}
	}

}