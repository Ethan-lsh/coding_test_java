import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0;
		while (cnt++ < N) {
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
			
	}

}