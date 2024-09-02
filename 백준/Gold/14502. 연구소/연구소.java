import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] board;
	static boolean[][] visited;
	static int[][] result;  // 선택한 벽의 위치
	static int ans;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 0 빈칸; 1 벽; 2 바이러스
		board = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// step 1. 벽을 세울 위치를 조합으로 설정
		result = new int[3][2];
		
		combination(0, 0);
		
		System.out.println(ans);
		
	}
	
	static void combination(int index, int cnt) {
		if (cnt == 3) {
			// step 2. 선택한 위치에 벽을 세운다
			for (int j = 0; j < 3; j++) {
				int r = result[j][0];
				int c = result[j][1];
				
				board[r][c] = 1;
			}

			// step 3. 선택한 벽의 조합에 따른, 바이러스가 퍼지는 영역을 체크
			bfs();
			
			// step 4. 벽 되돌리기
			for (int j = 0; j < 3; j++) {
                int r = result[j][0];
                int c = result[j][1];
                board[r][c] = 0;
            }
			
			return;
			
		}
		
		for (int i = index; i < N*M; i++) {
			int r = i / M;
			int c = i % M;
			
			// 만약, 빈칸이 아닌 경우에는 다음 선택지로 이동
			if (board[r][c] != 0) continue;
			
			result[cnt][0] = r;
			result[cnt][1] = c;
			
			// 다음 요소로 재귀로 넘김
			combination(i + 1, cnt + 1);
		}
		
	}
	
	
	static void bfs() {
		
		int[][] virus_map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				virus_map[i][j] = board[i][j];
			}
		}
		
		Queue<int[]> q = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (virus_map[i][j] == 2) {
					q.offer(new int[] {i, j});
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] xy = q.poll();
			
			int cx = xy[0], cy = xy[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				
				if (virus_map[nx][ny] == 0) {
					virus_map[nx][ny] = 2;  // 바이러스 퍼뜨리기
					q.offer(new int[] {nx, ny});
				}
			}
		} // end of queue
		
		countSpace(virus_map);
	}
	
	static void countSpace(int[][] virus_map) {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (virus_map[i][j] == 0) {
                    cnt++;
                }
            }
        }
        
        ans = Math.max(ans, cnt);
    }

}