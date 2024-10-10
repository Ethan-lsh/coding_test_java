import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R, C;
	static char[][] board;
	static boolean[][] visited;
	static Queue<int[]> waters;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Hok {
		int x, y;
		int time;
		
		public Hok(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		visited = new boolean[R][C];
		
		waters = new ArrayDeque<>();
		
		Hok hok = null;
		for (int i = 0; i < R; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				char val = line[j].charAt(0);
				if (val == 'S') {
					hok = new Hok(i, j, 0);
				} else if (val == '*') {
					waters.offer(new int[] {i, j});
				} else if (val == 'X') {
					visited[i][j] = true;
				}
				board[i][j] = val;
			}
		}
		
		bfs(hok);
		
	}
	
	private static void bfs(Hok hok) {
		Queue<Hok> hq = new ArrayDeque<>();
		
		hq.offer(hok);
		
		visited[hok.x][hok.y] = true;

		while (!hq.isEmpty()) {
			
			int water_size = waters.size();
			
			for (int i = 0; i < water_size; i++) {
				
				int[] wxy = waters.poll();
				
				int wx = wxy[0], wy= wxy[1];
				for (int d = 0; d < 4; d++) {
					int nx = wx + dx[d];
					int ny = wy + dy[d];
					
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
					if (visited[nx][ny]) continue;
					if (board[nx][ny] == 'D' | board[nx][ny] == 'X') continue;
					
					visited[nx][ny] = true;
					waters.offer(new int[] {nx, ny});
				}
				
			}
			
			int	hok_size = hq.size();
			
			for (int i = 0; i < hok_size; i++) {
				
				Hok cur_hok = hq.poll();
				
				int cx = cur_hok.x, cy = cur_hok.y;
				
				if (board[cx][cy] == 'D') {
					System.out.println(cur_hok.time);
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
					if (visited[nx][ny]) continue;
					if (board[nx][ny] == '*' | board[nx][ny] == 'X') continue;
					
					visited[nx][ny] = true;
					hq.offer(new Hok(nx, ny, cur_hok.time+1));
				}
			}
			
		}
		
		System.out.println("KAKTUS");
		
	}
	
}