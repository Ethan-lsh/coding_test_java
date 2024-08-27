import java.util.Scanner;

public class Main {

	static int R, C;
	static int ans = 0;
	static char[][] board;
	static boolean[] alphaCnt = new boolean['Z'+1];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		
		board = new char[R][C];
		for (int i = 0; i < R; i++)
			board[i] = sc.next().toCharArray();
		
		
		alphaCnt[board[0][0]] = true;
		dfs(0, 0, 1);
		
		System.out.println(ans);
		
	}
	
	private static void dfs(int x, int y, int depth) {
		
		if (x-1 >= 0 && !alphaCnt[board[x-1][y]]) {
			alphaCnt[board[x-1][y]] = true;
			dfs(x-1, y, depth+1);
			alphaCnt[board[x-1][y]] = false;
		}
		
		if (x+1 < R && !alphaCnt[board[x+1][y]]) {
			alphaCnt[board[x+1][y]] = true;
			dfs(x+1, y, depth+1);
			alphaCnt[board[x+1][y]] = false;
		}
		
		if (y-1 >= 0 && !alphaCnt[board[x][y-1]]) {
			alphaCnt[board[x][y-1]] = true;
			dfs(x, y-1, depth+1);
			alphaCnt[board[x][y-1]] = false;
		}
		
		if (y+1 < C && !alphaCnt[board[x][y+1]]) {
			alphaCnt[board[x][y+1]] = true;
			dfs(x, y+1, depth+1);
			alphaCnt[board[x][y+1]] = false;
		}
		
		
		
		ans = Math.max(ans, depth);
		
	}

}