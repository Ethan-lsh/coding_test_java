import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static int[][] dxy = { { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, 0 } };

	static class Node {
		int x, y;
		int cnt;
		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		board = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j+1] = str.charAt(j) - '0';
			}
		}

		bfs();

	}

	
	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N+1][M+1][2];
		
		q.offer(new Node(1, 1, 0));
		visited[1][1][0] = true;
		
		int dist = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {     // 목적지까지 거리를 구하기 위해, 현재 큐 값이 살아있을 때까지 반복
				Node cur = q.poll();
				
				int x = cur.x;
				int y = cur.y;
				int cnt = cur.cnt;  // 벽을 부쉈는지 체크하는 변수
				
				if (x == N && y == M) {    // 목적지에 도달하면 거리를 반환
					System.out.println(dist);
					return;
				}
				
				for (int dir = 0; dir < 4; dir++) {
					int nx = x + dxy[dir][0];
					int ny = y + dxy[dir][1];
					
					// 범위를 벗어나면 continue
					if (nx < 1 || nx > N || ny < 1 || ny > M) continue;
					
					// 벽이 존재
					if (board[nx][ny] == 1) {
						if (cnt == 1) continue;   // 이미 벽을 부순적이 있다면 cotinue
						if (visited[nx][ny][1]) continue;   // 해당 지점이 이미 벽이 부숴진 적이 있으면 continue
						
						q.offer(new Node(nx, ny, cnt+1));  // cnt+1 증가시킨 노드를 추가
						visited[nx][ny][cnt+1] = true;    // 해당 지점 방문처리
					}
					
					else {
						if (visited[nx][ny][cnt]) continue;  // 현재 좌표에 방문한 적이 있다면 continue
						
						q.offer(new Node(nx, ny, cnt));  // 큐에 추가
						visited[nx][ny][cnt] = true;     // 방문처리
					}
				}
			}
			
			dist++;  // 이전(t-1 시간) 큐에 들어있던 모든 요소에 대해 탐색이 끝나면, 거리를 1증가 
		}
		
		System.out.println(-1);
	}

}