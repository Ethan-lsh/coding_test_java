import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:138204kb, 실행시간:432ms
 */
public class Main {

	static int N, M;             // N:가로; M:세로
	static int[][] board;        // 2차원 배열 정보
	static int[][] result;       // 선택한 벽의 위치
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
	
	/**
	 * 3개의 벽을 세울 위치를 조합으로 구한다
	 * @param index 선택할 수 있는 배열의 첫번째 인덱스
	 * @param cnt 선택한 값의 개수
	 */
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
		
		// NxM 2차원 배열을 1차원 배열처럼 사용하는 방법
		// 배열의 범위에 있는 숫자를 '열로 나누면 행의 위치', '열로 나머지를 구하면 열의 위치' 
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
	
	/**
	 * 바이러스가 퍼지는 것을 구현
	 */
	static void bfs() {
		
		//!!중요
		// 바이러스가 퍼지는 것을 board에 표현한다.
		// 이때, 다른 조합의 경우의 수에 영향이 가지 않도록, 복사본을 사용하여 탐색을 진행한다
		int[][] virus_map = new int[N][M];
		
		// 복사본 생성
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				virus_map[i][j] = board[i][j];
			}
		}
		
		// 모든 바이러스의 위치를 큐에 저장
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (virus_map[i][j] == 2) {
					q.offer(new int[] {i, j});
				}
			}
		}
		
		// 큐가 빌때 까지, bfs를 실행
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
		
		// 바이러스가 모두 퍼졌을 때, 안전지대 개수를 구함
		countSpace(virus_map);
	}
	
	/**
	 * 안전지대 개수를 구하는 함수
	 * @param virus_map 복사본 배열
	 */
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