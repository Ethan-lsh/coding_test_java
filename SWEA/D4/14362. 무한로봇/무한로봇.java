import java.util.Scanner;

public class Solution {
	
	static int x, y, d;
	static String str;
	
	static int dx[]={1, 0, -1, 0};
    static int dy[]={0, 1, 0, -1};
	
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			
			str = sc.next();
			
			ans = 0;
			
			solve();
			
			System.out.printf("#" + tc + " ");
			if (ans == -1) System.out.println("oo");
			else System.out.println(ans);
		}
	}
	
	private static void solve() {
		x = 0;
		y = 0;
		d = 0;
		
		for (int i = 0; i < 4; i++) {
			int sd = d; // 시작 방향
			
			command(); // 한줄 명령어 실행
			
			// 명령어 한 줄을 실행 하고도 원점에 도착하면 그냥 종료
			if (x == 0 && y == 0){
				return;
			}
			
			// 초기 방향과 똑같은 방향을 바라본다면 무한대
			if (d == sd) {
				ans = -1;
				return;
			}
		}
	}

	private static void command() {
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == 'S') {
				x += dx[d];
				y += dy[d];
				ans = Math.max(ans, x * x + y * y);
			} else if (c == 'L') {
				d = (d + 1) % 4;
			} else if (c == 'R') {
				d = (d + 3) % 4;
			}
		}
		
	}
}