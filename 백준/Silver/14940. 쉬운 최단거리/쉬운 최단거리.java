import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] board;
	static int[][] visited;
	
	static int[][] dxy = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		visited = new int[n][m];
		
		int[] start = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 2) {
					start = new int[] {i, j};
					visited[i][j] = -1;
				} else if (num == 0) {
					visited[i][j] = 0;
				} else if (num == 1) {
					visited[i][j] = -1;
				}
				
				board[i][j] = num;
			}
		}
		
		bfs(start);
		
		StringBuilder sb = new StringBuilder();
		for (int[] line : visited) {
			for (int t : line) {
				sb.append(t).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	private static void bfs(int[] pos) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.add(pos);
		visited[pos[0]][pos[1]] = 0;
		
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			int x = xy[0], y = xy[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dxy[d][0];
				int ny = y + dxy[d][1];
				
				if (nx < 0 | nx >= n | ny < 0 | ny >= m) continue;
				if (visited[nx][ny] != -1) continue;
				
				if (board[nx][ny] == 1) {
					visited[nx][ny] = visited[x][y] + 1;
					q.add(new int[] {nx, ny});
				}
			}
		}
		
		return;
	}

}