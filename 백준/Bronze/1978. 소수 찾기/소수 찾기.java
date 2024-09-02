import java.util.Scanner;

public class Main {

	static boolean[] prime; // 소수를 체크할 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int cnt = 0;
		while (N-- > 0) {
			int val = sc.nextInt();
			
			if(isPrime(val)) cnt++;
		}
		
		System.out.println(cnt);

	}
	
	static boolean isPrime(int n) {
		if (n < 2) {  // 1은 소수가 아니므로 패스
			return false;
		}
		
		// 2부터 n-1까지의 수로 n을 나눌 수 있으면 약수가 존재하는 것이기 때문에 소수가 아님
		for (int i = 2; i < n; i++) {
			if (n % i == 0) return false;
		}
		
		return true;
		
	}

}