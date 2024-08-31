import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int[][] board;
	static boolean[] visited;
	static int ans;
	
	static int[] dr = {1, 1, -1, -1};
	static int[] dc = {1, -1, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			N = Integer.parseInt(br.readLine());

			board = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 루트에 같은 숫자가 없어야 하기 때문에, visited는 배열의 최대 크기인 100 만큼의 일차원 배열로 설정
			// 보드에서 가리키는 값을 방문처리 해준다
			visited = new boolean[101];
			
			ans = -1;
			
			/**
			 * 1.dfs 탐색 시작
			 * 문제에서 주어진 직사각형을 만들기 위해서는 최소 3개의 행과 2개의 열을 방문해야 생성할 수 있다.
			 * 행의 범위는 0~N-3, 열의 범위는 1~N-2로 설정해야 직사각형을 만들기 위한 조건을 충족한다.
			 * 문제 풀이의 가장 중요한 부분
			 */
			for (int sr = 0; sr <= N - 3; sr++) {
				for (int sc = 1; sc <= N - 2; sc++) {
					dfs(sr, sc, 0, sr + 1, sc - 1, 1);
				}
			}
			
			System.out.println("#"+tc+" "+ans);

		}

	}
	
	/**
	 * nr, nc에 대한 처리를  재귀로 수행하는 함수
	 * @param r 현재 행
	 * @param c 현재 열
	 * @param dir 진행 방향
	 * @param targetR 탐색 종료 목표 행
	 * @param targetC 탐색 종료 목표 열
	 * @param cnt
	 */
	static void dfs(int r, int c, int dir, int targetR, int targetC, int cnt) {
		if (r < 0 || r >= N || c < 0 || c >= N) return;  // 범위를 벗어남
		if (visited[board[r][c]]) return;    // 이미 선택된 숫자임
		if (dir == 4) return;     // 방향을 4번 바꿈
		
		
		if (r == targetR && c == targetC) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		visited[board[r][c]] = true;
		
		int nr = r + dr[dir];
		int nc = c + dc[dir];
		
		dfs(nr, nc, dir, targetR, targetC, cnt+1);      // 이동 후 방향 변경 없음
		dfs(nr, nc, dir + 1, targetR, targetC, cnt+1);  // 이동 후 방향 변경
		
		visited[board[r][c]] = false;  // 
	}

}