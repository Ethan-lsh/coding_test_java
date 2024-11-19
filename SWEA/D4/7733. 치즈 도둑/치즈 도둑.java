import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static int result;
	
	private static int MAX_VALUE;
	
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	
	private static int ans;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 가장 큰 치즈의 값을 구하기
			for (int[] row : map) {
				MAX_VALUE = Math.max(MAX_VALUE, Arrays.stream(row).max().getAsInt());
			}
			
			for (int k = 0; k <= MAX_VALUE; k++) {
				visited = new boolean[N][N];
				
				result = 0;
				
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (!visited[i][j] && map[i][j] > k) {
							bfs(i, j, k);
							result++;
						}
					}
				}
				
				ans = Math.max(ans, result);
			}
			
			System.out.println("#" + tc + " " + ans);
		}

	}
	
	private static void bfs(int x, int y, int s) {
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		
		dq.offer(new int[] {x, y});
		
		visited[x][y] = true;
		
		while (!dq.isEmpty()) {
			
			int[] cur = dq.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
				if (visited[nx][ny]) continue;
				
				if (map[nx][ny] > s) {
					visited[nx][ny] = true;
					dq.offer(new int[] {nx, ny});
				}
			}
			
		}
		
	}

}