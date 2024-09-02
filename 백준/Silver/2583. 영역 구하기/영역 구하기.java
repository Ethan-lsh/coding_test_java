import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int M, N, K;
	static int[][] board;
	static boolean[][] visited;
	static List<Integer> result = new LinkedList<>();
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int[M][N];
		visited = new boolean[M][N];
		
		
		// 얼어붙은 영역 칠하기
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			
			// 아래 점
			int ly = Integer.parseInt(st.nextToken());
			int lx = Integer.parseInt(st.nextToken());
			
			// 위에 점
			int hy = Integer.parseInt(st.nextToken());
			int hx = Integer.parseInt(st.nextToken());
			
			for (int x = lx; x < hx; x++) {
				for (int y = ly; y < hy; y++) {
					board[M-x-1][y] = -1;
				}
			}
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] & board[i][j] != -1)
					bfs(i, j);  // 영역 찾기
			}
		}
		
		Collections.sort(result);
		System.out.println(result.size());
		for (int r : result) {
			System.out.printf(r + " ");
		}
		
	}
	
	private static void bfs(int x, int y) {
		
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		int cnt = 1;   // 영역의 넓이 체크
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			
			int cx = xy[0];
			int cy = xy[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (nx < 0 | nx >= M | ny < 0 | ny >= N) continue;
				
				if (!visited[nx][ny] & board[nx][ny] != -1) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
					cnt++;
				}
				
				
			}
		}
		
		result.add(cnt);
	}

}