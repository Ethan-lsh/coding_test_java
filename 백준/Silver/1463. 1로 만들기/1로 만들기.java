import java.util.Scanner;

public class Main {

	static int[] mem;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		mem = new int[N+1];
		
		/*DP*/
		System.out.println(dp(N, 0));

	}
	
	private static int dp(int n, int cnt) {
		if (n < 2) return cnt;
		
		/**
		 * n을 2와 3으로 나눴을 때의 몫을 패러미터로 전달
		 * cnt 횟수는 2와 3으로 나누었을 때 횟수 1번과 만약 나머지가 있는 경우 1을 빼주는 횟수를 더한 것과 같다
		 * 예) 5 / 2 = 2 ... 1 (1을 1번 빼줘야 한다 -> n % 2)
		 */
		return Math.min(dp(n / 2, cnt+1+(n%2)), dp(n / 3, cnt+1+(n%3)));
		
	}
	

}