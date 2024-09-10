import java.util.Scanner;

public class Main {

	static int[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int tc = 1; tc <= TC; tc++) {
			int N = sc.nextInt();
			
			if (N == 0) {
				System.out.println(1 + " " + 0);
				continue;
			} else if (N == 1) {
				System.out.println(0 + " " + 1);
				continue;
			}
			
			arr = new int[N+1][2];
			
			arr[0][0] = 1;
			arr[1][1] = 1;
			
			for (int i = 2; i <= N; i++) {
				arr[i][0] = arr[i-2][0] + arr[i-1][0];
				arr[i][1] = arr[i-2][1] + arr[i-1][1];
			}
			
			System.out.println(arr[N][0] + " " + arr[N][1]);
		}
		
	}
	
	
}