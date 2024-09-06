import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int K, W, H, board[][];
	static int[][][] visited; // (r, c) 말의 이동 여부체크

	static int ans;

	static int[][] drc = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 4방 탐색
	static int[][] hrc = {{-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};

	static class Node {
		int r, c;
		int k;
		
		public Node(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		board = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bfs();

	}

	private static void bfs() {
		Queue<Node> q = new ArrayDeque<>();

		boolean[][][] visited = new boolean[H][W][K+1];
		
		q.offer(new Node(0, 0, 0));  // cnt에는 말처럼 이동할 수 있는 횟수인 K를 준다
		
		visited[0][0][0] = true;
		
		int dist = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Node cur = q.poll();
				
				int r = cur.r;
				int c = cur.c;
				int k = cur.k;
				
				// 목적지에 도달하면
				if (r == H-1 && c == W-1) {
					System.out.println(dist);
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = r + drc[d][0];
					int nc = c + drc[d][1];
					
					if (!isInRange(nr, nc)) continue;
					if (board[nr][nc] == 1) continue;
					if (visited[nr][nc][k]) continue;
					
					q.offer(new Node(nr, nc, k));
					visited[nr][nc][k] = true;
				}
				
				if (k == K) continue;
				
				
				for (int d = 0; d < 8; d++) {
					int nr = r + hrc[d][0];
					int nc = c + hrc[d][1];
					
					if (!isInRange(nr, nc)) continue;  // 범위를 벗어남						
					if (board[nr][nc] == 1) continue;  // 도착지점에 장애물이 있는 경우
					if (visited[nr][nc][k+1]) continue;
					
					q.offer(new Node(nr, nc, k+1));
					visited[nr][nc][k+1] = true;
				}
			}
			dist++;
		}
		System.out.println(-1);
	}
	
	private static boolean isInRange(int x, int y) {
		return (x >= 0 && x < H && y >= 0 && y < W);
	}

}