import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M, K;
	static int[][] board;
	static boolean[][] visited;
	
	static int ans;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for (int tc=1; tc <= TC; tc++) {
			
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			
			board = new int[N][M];
			visited = new boolean[N][M];
			for (int k = 0; k < K; k++) {
				int j = sc.nextInt();
				int i = sc.nextInt();
				board[i][j] = 1;
			}
			
			ans = 0;
			
			// 1차원 접근법 사용
			for (int i = 0; i < N*M; i++) {
				int r = i / M;
				int c = i % M;
				
				if (!visited[r][c] & board[r][c] == 1) {
					bfs(r, c);
					ans++;
				}
			}
			
			System.out.println(ans);
		}
	}
	
	/**
	 * BFS
	 * @param r 행 좌표
	 * @param c 열 좌표
	 */
	private static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		while (!q.isEmpty()) {
			
			int[] xy = q.poll();
			
			int x = xy[0], y = xy[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (visited[nx][ny]) continue;
				if (board[nx][ny] == 0) continue;
				
				visited[nx][ny] = true;
				q.add(new int[] {nx, ny});
			}
		}
		
		return;
	}

}