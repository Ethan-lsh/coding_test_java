package algo0906;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:49724kb, 실행시간:388ms
 */
public class Main_B_1600_말이되고픈원숭이 {

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

		/**
		 * 방문 좌표는 3차원 배열로 설정한다
		 * 1열과 2열은 각각 행과 열의 위치를 나타내고, 3열은 K값일 때 방문 여부를 나타낸다
		 * 이는, 우리가 좌표를 방문할 수 있는 방법이 걷는것과 뛰는 것 두개로 구분되기 때문이다.
		 * 예를 들어, (1, 2) 좌표를 임의의 출발점에서 걸어서 갈 수도 있고, 뛰어서 도착할 수도 있다.
		 * 그런데 만약 방문 배열을 2차원 배열을 쓰게되면, 걸어서 간 경우에 대해 방문처리를 했으므로 뛰어갈 수 있는 경우를 막아버린다
		 * 즉, 서로 독립적인 케이스로 고려해야 하기 때문에 방문 배열을 K개 만큼 설정해주어야 한다.
		 * 또한, k==2 에서 k==3 일때 (0, 0) -> (1, 2); k==2에서 k==3 일 때 (1, 1) -> (1, 2) 인 경우는 같은 k값에 이미 방문한 좌표이기 때문에 패스하는 것도 고려해야 한다.
		 */
		boolean[][][] visited = new boolean[H][W][K+1]                                                                                                                                                                                                                        ;
		
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
					
					if (!isInRange(nr, nc)) continue;  // 범위를 벗어남
					if (board[nr][nc] == 1) continue;  // 도착지점에 장애물이 있는 경우
					if (visited[nr][nc][k]) continue;  // k 시점에 방문한 적이 있는 경우
					
					q.offer(new Node(nr, nc, k));
					visited[nr][nc][k] = true;
				}
				
				if (k == K) continue;
				
				
				// 말이 뛰기 때문에, K+1
				for (int d = 0; d < 8; d++) {
					int nr = r + hrc[d][0];
					int nc = c + hrc[d][1];
					
					if (!isInRange(nr, nc)) continue;  // 범위를 벗어남						
					if (board[nr][nc] == 1) continue;  // 도착지점에 장애물이 있는 경우
					if (visited[nr][nc][k+1]) continue;  // k+1 시점에 방문한 적이 있는 경우
					
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
