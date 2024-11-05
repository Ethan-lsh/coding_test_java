import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static int ans;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static class Pos implements Comparable<Pos>{
		
		int x, y;
		int crashed_wall;
		
		public Pos(int x, int y, int crashed_wall) {
			super();
			this.x = x;
			this.y = y;
			this.crashed_wall = crashed_wall;
		}

		@Override
		public int compareTo(Pos o) {
			return Integer.compare(this.crashed_wall, o.crashed_wall);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] ch = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = ch[j] - '0';
			}
		}
		
		dijkstra(0, 0, N-1, M-1);
		
		System.out.println(ans);
	}
	
	private static void dijkstra(int sx, int sy, int ex, int ey) {
		
		// 우선 순위 큐를 생성
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		
		// 초기 좌표를 지정
		pq.offer(new Pos(sx, sy, 0));
		
		// 방문 좌표
		// 벽을 부수고 도착한 개수를 저장
		int[][] counting_map = new int[N][M];
		for (int[] row : counting_map) Arrays.fill(row, Integer.MAX_VALUE);
		
		// NOTE: 시작점을 무조건 방문 처리 해줘야 함
		counting_map[sx][sy] = 0;
		
		while (!pq.isEmpty()) {
			
			Pos cur = pq.poll();
			
			// 도착하면 break
			if (cur.x == ex && cur.y == ey) {
				ans = cur.crashed_wall;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				// 범위를 벗어나는 경우 continue
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				int new_crashed_wall = cur.crashed_wall + (map[nx][ny] == 1 ? 1 : 0);
				
				if (new_crashed_wall < counting_map[nx][ny]) {
					counting_map[nx][ny] = new_crashed_wall;
					pq.offer(new Pos(nx, ny, new_crashed_wall));
				}
			}
		}
		
	}

}