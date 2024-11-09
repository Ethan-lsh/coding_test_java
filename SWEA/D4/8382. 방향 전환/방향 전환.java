import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	static final int HORIZON = 0;
	static final int VERTICAL = 1;
	static final int MAX = 200;
	
	static int[][] dh = {{0, -1}, {0, 1}};  // 가로 방향
	static int[][] dv = {{-1, 0}, {1, 0}};  // 세로 방향
	
	static int ans;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TC; tc++) {
			
			int[][] map = new int[MAX+1][MAX+1];
			
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken()) + 100;
			int y1 = Integer.parseInt(st.nextToken()) + 100;
			int x2 = Integer.parseInt(st.nextToken()) + 100;
			int y2 = Integer.parseInt(st.nextToken()) + 100;
			
			ans = Integer.MAX_VALUE;
			bfs(x1, y1, x2, y2, HORIZON);
			bfs(x1, y1, x2, y2, VERTICAL);
			
			System.out.println("#" + tc + " " + ans);
		}
		
	}
	
	private static void bfs(int sx, int sy, int ex, int ey, int sd) {
		ArrayDeque<int[]> q = new ArrayDeque<>();	
		
		q.offer(new int[] {sx, sy, sd});

		boolean[][] visited = new boolean[300][300];
		visited[sx][sy] = true;
		
		int dist = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				
				int[] cur = q.poll();
				
				int cx = cur[0];
				int cy = cur[1];
				int cd = cur[2];
				
				if (cx == ex && cy == ey) {
					ans = Math.min(ans, dist);
					return;
				}
				
				if (cd == HORIZON) {
					for (int d = 0; d < 2; d++) {
						int nx = cx + dv[d][0];
						int ny = cy + dv[d][1];
						
						if(isNotInRange(nx, ny)) continue;
						if(visited[nx][ny]) continue;
						
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny, VERTICAL});
					}
				} else if (cd == VERTICAL){
					for (int d = 0; d < 2; d++) {
						int nx = cx + dh[d][0];
						int ny = cy + dh[d][1];
						
						if(isNotInRange(nx, ny)) continue;
						if(visited[nx][ny]) continue;
						
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny, HORIZON});
					}
				}
			}
			dist++;
		}
		
	}
	
	private static boolean isNotInRange(int x, int y) {
		return (x < 0 || x > MAX || y < 0 || y > MAX);
	}

}