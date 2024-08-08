import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 0; t < T; t++) {
			int a = sc.nextInt();
			int b = sc.nextInt();

			int result = 1;
			for (int i = 1; i <= b; i++) {
				result = result * a % 10;
			}
			
			result = result == 0 ? 10 : result;
			
			System.out.println(result);
			
		}

	}

}
