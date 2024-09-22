import java.util.Scanner;

public class Main {

	static int ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();
		
		// 주어진 영역을 N-1 x N-1 크기의 4개의 영역으로 계속 분할한다
		dq(r, c, (int)Math.pow(2, N));
		System.out.println(ans);
	}
	
	private static void dq(int r, int c, int size) {

		if (size == 1) {
			return;
		}
		
		int half_size = size / 2; 
		
		// 좌상단
		if (r < half_size && c < half_size) {
			dq(r, c, half_size);
		}
		
		// 우상단
		else if (r < half_size && c >= half_size) {
			ans += size * size / 4;
			dq(r, c - half_size, half_size);
		}
		
		// 좌하단
		else if (r >= half_size && c < half_size) {
			ans += (size * size / 4) * 2;
			dq(r - half_size, c, half_size);
		}
		
		// 우하단
		else {
			ans += (size * size / 4) * 3;
			dq(r - half_size, c - half_size, half_size);
		}
		
	}

}