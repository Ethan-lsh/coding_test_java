import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] lines = new int[3];
		
		while (true) {
			lines[0] = sc.nextInt();
			lines[1] = sc.nextInt();
			lines[2] = sc.nextInt();
			
			if (lines[0] == 0) break;
			
			Arrays.sort(lines);
			
			if ((lines[0]*lines[0] + lines[1]*lines[1]) == lines[2]*lines[2]) System.out.println("right");
			else System.out.println("wrong");
		}
		
	}

}