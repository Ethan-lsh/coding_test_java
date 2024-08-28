import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static final int MAX_TASTE = 100;
	public static int N;
	public static int ans, result;  // ans: 최대값, result: taste별 값
	public static int[][] board;
	public static boolean[][] visited;

	public static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 상하좌우

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine()); // x의 값
		for (int tc = 1; tc <= test_case; tc++) {
			ans = 0;
			
			N = Integer.parseInt(br.readLine()); // 한 변의 길이

			// 2차원 배열의 정보
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken()); // (i,j) 값 설정
				}
			} // end for

			/**
			 * - 요정은 X날에 X의 값을 갖는 치즈만을 먹는다 
			 * -> 2차원 배열을 순회하며 X 값을 갖는 위치에서 BFS실행 
			 * -> 더 이상 확장이 불가능하면 다른 좌표로 이동하여 BFS 실행 
			 * - 치즈 덩어리는 상하좌우로 인접한 하나의 칸들을 묶어둔것
			 */
			int taste = 0;  // X의 맛
			while (taste <= MAX_TASTE) {      // 100일 동안 반복
				visited = new boolean[N][N];  // 매일 방문 배열을 초기화
				
				result = 0;  // 각 맛에 따른 블록의 개수
				
				for (int i = 0; i < N; i++) { // 2차원 배열 순회
					for (int j = 0; j < N; j++) {
						// 배열 값이 최대 맛 보다 큰 경우에만 bfs를 실행
						if (board[i][j] > taste && !visited[i][j]) {
							bfs(i, j, taste);
						}
					}
				}
				
				ans = Math.max(ans, result);   // 현재 최대값과 result를 비교
				
				taste++;    // 맛을 증가시킴
			}
			
			System.out.println("#"+tc+" "+ans);
			
		}

	} // end main

	/**
	 * X의 값보다 작은 값을 방문표시 하는 것이 아닌, X의 값보다 큰 값만을 방문한다
	 * -> 블록의 개수를 세는 함수 작성을 하지 않아도 됌
	 * @param x x좌표
	 * @param y y좌표
	 * @param taste 현재 맛, X
	 */
	private static void bfs(int x, int y, int taste) {
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { x, y }); // 시작 정점 큐 삽입

		visited[x][y] = true; // 시작 정점 방문 표시

		while (!q.isEmpty()) {
			int[] xy = q.poll();
			int cx = xy[0], cy = xy[1];
			for (int d = 0; d < 4; d++) {  // 4방탐색
				int nx = cx + dxy[d][0];
				int ny = cy + dxy[d][1];
  
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {         // 2차원 배열 범위 안이고
					if (!visited[nx][ny] && board[nx][ny] > taste) {  // 방문하지 않았으며 맛이 X보다 클때만
						visited[nx][ny] = true; // 새 좌표를 방문 처리
						q.add(new int[] { nx, ny }); // 큐에 삽입
					}
				}
			}
		}
		
		result++;  // 덩어리를 찾지 못하면 ans증가
	}
}