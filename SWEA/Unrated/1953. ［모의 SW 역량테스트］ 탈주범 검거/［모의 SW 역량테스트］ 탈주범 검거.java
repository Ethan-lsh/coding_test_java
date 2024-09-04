import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, R, C, L;
	static int[][] board;
	static int cnt;

	static int[][] dxy = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static HashMap<Integer, int[]> pipe;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			// 2차원 배열 초기화
			board = new int[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 파이프 정보 초기화
			pipe = new HashMap<>(7);

			// 모양과 이동 방향을 저장 -> 이동 방향이 2개인 경우 앞의 값이 우선순위를 갖는다
			pipe.put(1, new int[] { 0, 1, 2, 3 });
			pipe.put(2, new int[] { 0, 1 });
			pipe.put(3, new int[] { 2, 3 });
			pipe.put(4, new int[] { 0, 3 });
			pipe.put(5, new int[] { 1, 3 });
			pipe.put(6, new int[] { 1, 2 });
			pipe.put(7, new int[] { 0, 2 });


			// step 1. 범인의 위치 (R, C)로 부터 BFS를 실행
			cnt = 0;
			bfs();

			System.out.println("#" + tc + " " + cnt);
		}

	}

	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();

		boolean[][] visited = new boolean[N][M];
		
		q.add(new int[] { R, C });
		visited[R][C] = true;
		cnt++;
		
		if (L == 1) return;

		int dist = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int[] rc = q.poll();
				
				int r = rc[0];
				int c = rc[1];
				
				// step 2. 현재 좌표에 있는 파이프가 갈 수 있는 경로로 이동
				// 다음 파이프와 서로 이어질 수 있는지를 체크할 필요가 있음

				int pipe_type = board[r][c]; // 파이프 타입
				int[] direction = pipe.get(pipe_type); // 현재 파이프가 이동 가능한 경로

				for (int dir : direction) {
					int nr = r + dxy[dir][0];
					int nc = c + dxy[dir][1];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;  // 범위를 벗어나는 경우
					if (visited[nr][nc]) continue;   // 이미 방문한 경우
					
					if (board[nr][nc] != 0) {
						// 다음 좌표에 있는 파이프와 연결 될 수 있는 지 체크
						int next_pipe_type = board[nr][nc];
						int[] next_dir = pipe.get(next_pipe_type); // 다음 파이프의 입구 방향 리스트

						/*
						 * 0 <-> 1; 2 <-> 3 끼리 이동이 가능하다
						 * 비트마스킹을 이용하여 지금 파이프의 출구와 다음 파이프의 입구를 비교함
						 * dir^(1<<0)은 0th 인덱스에 not을 취하기 때문에, 0->1, 1->0이 될 수 있다 
						 * */
						
						int b_dir = dir ^ (1 << 0);  // 현재 방향과 맞아야 하는 다음 파이프의 방향
						
						for (int n_dir : next_dir) {  // 다음 파이프의 모든 구멍에 대해 맞는게 있는지 확인한다
							int b_next_dir = n_dir ^ (1 << 0);

							// 비트마스킹을 취했을 때, 서로 값이 같다면 이동이 가능하다
							if ((b_dir == n_dir) & (b_next_dir == dir)) {
								q.offer(new int[] {nr, nc});
								visited[nr][nc] = true;
								cnt++;
							}

						}
					}

				}

			}
			if (++dist == L) return;
		}
	}

}