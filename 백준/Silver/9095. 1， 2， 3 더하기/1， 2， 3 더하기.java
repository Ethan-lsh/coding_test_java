import java.util.Scanner;

public class Main {

	static int ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int tc=1; tc <= TC; tc++) {
			int N = sc.nextInt();
			
			ans = 0;
			recursion(N);
			System.out.println(ans);
			
		}

	}
	
	private static void recursion(int N) {
		if (N == 0) {
			ans ++;
			return;
		}
		
		if (N - 3 >= 0) {
			recursion(N-3);
		}
		
		if (N - 2 >= 0) {
			recursion(N-2);
		} 
		
		if (N - 1 >= 0) {
			recursion (N - 1);
		} 
	}

}