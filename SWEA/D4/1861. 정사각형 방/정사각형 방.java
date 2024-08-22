import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Solution {

	static int N, board[][];

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	static int maxCnt, maxRoom; // maxCnt: 최대 이동 횟수, maxRoom: 최대 이동 값을 갖는 출발 지점 

	private static int dfs(int i, int j, int cnt) {
		for (int d = 0; d < 4; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];

			if (ni < 0 || ni >= N || nj < 0 || nj >= N) continue; // 격자 범위를 벗어나면 다른 방향을 탐색
			else if (board[i][j] + 1 != board[ni][nj]) continue;  // +1 만큼 크지 않으면 다른 방향을 탐색

			// +1 차이의 방을 찾았다면, 'cnt+1'을 하고 해당 좌표에서 다시 방을 탐색
			// 만약, 4방향 모두에서 방을 찾지 못했다면, (ni, nj)까지 가는데 걸린 cnt를 return으로 돌아온다
			cnt = dfs(ni, nj, cnt+1);
			
			
			// 모든 재귀를 마치고 복귀하면, 현재 maxCnt와 maxRoom과 값을 비교한다
			if (maxCnt < cnt) {
				maxCnt = cnt;
				maxRoom = board[i][j];
			} else if (maxCnt == cnt && maxRoom > board[i][j]) {
				maxRoom = board[i][j];
			}
			
			break;
		}
		
		return cnt;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			maxCnt = 0; maxRoom = 0;

			N = Integer.parseInt(br.readLine());

			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 1);
				}
			}

			System.out.println("#" + tc + " " + maxRoom + " " + maxCnt);
		}
	}

}