import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int[] people;
	static int[] result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		people = new int[N+1];
		for (int i = 1; i <= N; i++) {
			people[i] = sc.nextInt();
		}

		// 대기시간을 정렬
		Arrays.sort(people);
		
		result = new int[N+1];
		for (int i = 1; i <= N; i++) {
			result[i] = result[i-1] + people[i];	
		}
		
		System.out.println(Arrays.stream(result).sum());
		
	}
}