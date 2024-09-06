import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리:22,348kb, 실행시간:133ms
 */
public class Solution {

	static int N, K;
	static int[][] board;
	static boolean[][] visited;
	static List<Hill> hills;
	
	static int ans;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static class Hill {
		int r, c;
		int height;
		int dig;
		
		public Hill(int r, int c, int height, int dig) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
			this.dig = dig;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			ans = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			board = new int[N][N];
			// 배열을 저장하면서 가장 높은 봉우리 값을 찾는다
			int max_hill = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					max_hill = Math.max(max_hill, temp);
					board[i][j] = temp;
				}
			}
			
			
			hills = new ArrayList<>();
			findHighest(max_hill);  // 가장 높은 봉우리 위치 찾기
			
			// 가장 높은 봉우리들만 시작점으로 순회
			for (Hill hill : hills) {
				visited = new boolean[N][N];
				visited[hill.r][hill.c] = true;
				dfs(hill, 1);
			}
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
			
			
		}
		
		System.out.println(sb);

	}
	
	private static void dfs(Hill hill, int depth) {
		int r = hill.r;
		int c = hill.c;
		int height = hill.height;

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;  // 범위를 벗어나면
			if (visited[nr][nc]) continue;    // 방문 했다면
			
			// 현재 봉우리가 다음 봉우리보다 높을 때
			if (height > board[nr][nc]) { 
				visited[nr][nc] = true;
				dfs(new Hill(nr, nc, board[nr][nc], hill.dig), depth+1);
				visited[nr][nc] = false;
			} 
			// 다음 봉우리가 더 높을 때, 언덕을 팔 수 있다면
			else if (height <= board[nr][nc]) {
				if (hill.dig == 0) {
					for (int k = 1; k <= K; k++) {
						if (board[nr][nc] - k < height) {
							// 방문처리와 봉우리 높이 깎기
							board[nr][nc] -= k;
							visited[nr][nc] = true;
							
							dfs(new Hill(nr, nc, board[nr][nc], hill.dig+1), depth+1);
							
							// 방문처리 해재와 봉우리 복원
							board[nr][nc] += k;
							visited[nr][nc] = false;
						}
					}
				}
				
			}
		}
		
		ans = Math.max(ans, depth);
		
	}
	
	/**
	 * 가장 높은 봉우리 위치 찾기
	 * @param h 제일 높은 봉우리 값
	 */
	private static void findHighest(int h) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == h) hills.add(new Hill(i, j, h, 0));
			}
		}
	}

}