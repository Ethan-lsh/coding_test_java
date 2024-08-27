import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static int N;
	public static int canRed, noRed;  // canRed: 적록색약이 아님, noRed:적록색약임;
	public static char[][] board;
	public static boolean[][] visited;
	
	public static int[][] dxy = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		board = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++)
			board[i] = sc.next().toCharArray();
		
		
		// 적록색약이 아닌 경우, option=0
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j])
					bfs(i, j, board[i][j], 0);
			}
		}
		
		visited = new boolean[N][N]; // 방문 배열 초기화
		
		// 적록색약이 있는 경우, 초록색을 모두 빨간색으로 변경
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 'G')
					board[i][j] = 'R';
			}
		}
		
		// 적록색약을 위한 탐색을 재실시. option=1
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j])
					bfs(i, j, board[i][j], 1);
			}
		}
		
		System.out.printf(canRed + " " + noRed);
	}
	
	private static void bfs(int x, int y, char color, int option) {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {x, y});
		while(!q.isEmpty()) {
			int[] xy = q.poll();
			int cx = xy[0], cy = xy[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = cx + dxy[d][0];
				int ny = cy + dxy[d][1];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {       // 격자 범위 안이고
					if (!visited[nx][ny] && board[nx][ny]==color) { // 미방문 상태이며 색이 시작점과 똑같을 때
						visited[nx][ny] = true;
						q.add(new int[] {nx, ny});
					}
				}
				
			}
		}
		
		// 옵션에 따라 값을 증가시킴
		if (option == 0) canRed++;
		else noRed++;
	}

}