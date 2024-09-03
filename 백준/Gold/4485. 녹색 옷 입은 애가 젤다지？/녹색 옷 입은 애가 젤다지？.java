import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] board;
	static int ans;
	
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			
			if (N == 0) break;
			
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			System.out.printf("Problem " + tc + ": ");
			System.out.println(dijkstra(0, 0, N-1, N-1));
			
			tc++;
		}
		
	}
	
	/**
	 * 다익스트라 알고리즘 풀이법
	 * @param sr 시작 행
	 * @param sc 시작 열
	 * @param er 도착 행
	 * @param ec 도착 열
	 * @return
	 */
	static int dijkstra(int sr, int sc, int er, int ec) {
		int INF = Integer.MAX_VALUE;

		boolean[][] visited = new boolean[N][N];  // 방문 배열
		 
		int[][] minDistance = new int[N][N];  // 최소 거리를 저장할 2차원 배열
		for (int i = 0; i < N; i++) {     // 아직 최소 거리를 모르기 때문에, 모두 최대 값으로 설정
			for (int j = 0; j < N; j++) {
				minDistance[i][j] = INF;
			}
		}
		
		// 우선순위 큐 생성
		// cost 값을 기준으로 정렬한다
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		
		minDistance[sr][sc] = board[sr][sc];   // 시작 지점의 가중치를 설정
		
		pq.offer(new int[] {sr, sc, minDistance[sr][sc]});  // 우선순위 큐에 삽입
		
		while (!pq.isEmpty()) {
			int[] stopOver = pq.poll();
			
			int r = stopOver[0];
			int c = stopOver[1];
			int cost = stopOver[2];
			
			if (visited[r][c]) continue; // 방문한 적이 있으면 패스
			
			visited[r][c] = true;  // 방문 처리
			
			// 도착지점이라면 cost를 반환
			// 도착지점에 도달하는 경로에 있는 모든 cost를 가져왔기 때문에, 바로 리턴 가능
			if (r == er && c == ec) return cost;
			
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 2차원 배열 범위 안에 존재
				// 방문하지 않았으며, minDistance의 값 보다 현재 노드까지의 가중치와 (nr, nc)가 가리키는 가중치의 합이 작으면 선택
				// 다익스트라 알고리즘의 핵심
				if (nr >= 0 && nr < N & nc >= 0 && nc < N && !visited[nr][nc] && minDistance[nr][nc] > cost + board[nr][nc]) {
					minDistance[nr][nc] = cost + board[nr][nc];
					pq.offer(new int[] {nr, nc, minDistance[nr][nc]});
				}
			}
			
		}
		
		return -1;
	}

}