import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {

	static int N, M;         // N:가로, M:세로
	static int[][] board;    // 2차원 배열
	static int[][] visited;  // 방문한 지점에 출발점으로 부터의 거리를 저장
	
	static int[][] dxy = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 2차원 배열 초기화
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = str.charAt(j) - '0';
			}
		}
		
		// 방문 배열 초기화
		visited = new int[N][M];
		bfs(0, 0);
		
		System.out.println(visited[N-1][M-1]);  // 목적지 값
	
	}
	
	
	static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		
		q.offer(new int[] {x, y});
		
		visited[x][y] = 1;   // 초기 시작지점을 1로 설정
		
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			
			int cx = xy[0], cy = xy[1];
			
			if (cx == N-1 && cy == M-1) return;  // 목적지에 도착하면 리턴
			
			for (int d = 0; d < 4; d++) {
				int nx = cx + dxy[d][0];
				int ny = cy + dxy[d][1];
				
				if (nx < 0 | nx >= N | ny < 0 | ny >= M) continue;
				
				if (board[nx][ny] == 1 && visited[nx][ny] == 0) {
					visited[nx][ny] = visited[cx][cy] + 1;
					q.add(new int[] {nx, ny});
				}
				
			}
		}
	}

}