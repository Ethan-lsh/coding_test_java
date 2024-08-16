import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int N;
	static int ans = 100000;
	static int[][] arr;
	static boolean[] isSeleceted;

	static void combination(int index, int sour, int stub) {
		if (index == N) {
			if (ans > Math.abs(sour - stub) & stub !=0) {
				ans = Math.abs(sour - stub);
			}
			return;
		}
		
		isSeleceted[index] = true;
		combination(index + 1, sour * arr[index][0], stub + arr[index][1]);
		
		isSeleceted[index] = false;
		combination(index + 1, sour, stub);
		
	}
	
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		arr = new int[N][2];
		isSeleceted = new boolean[N];

		
		for (int n = 0; n < N; n++) {
			arr[n][0] = sc.nextInt(); // 신맛
			arr[n][1] = sc.nextInt(); // 쓴맛
		}

		combination(0, 1, 0);
		System.out.println(ans);

	}

}