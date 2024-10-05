import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long A = sc.nextLong();
		long B = sc.nextInt();
		long C = sc.nextInt();
		
		System.out.println(fast_exponentiation(A, B, C));
	}

	private static long fast_exponentiation(long A, long B, long C) {
		long result = 1;
		
		A = A % C;
		
		while (B > 0) {
			if (B % 2 == 1) {
				result = (result * A) % C;
			}
			
			A = (A * A) % C;
			B = B / 2;
		}
		
		return result;
	}
	
}