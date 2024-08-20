import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 메모리:46076KB, 실행시간:684ms
 */
public class Main {

	static int N, M, R;    // N:행, M:열, R:회전 횟수
	static int[][] board;  // 2차원 격자
	
	// x,y 축 진행 방향
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	/**
	 * 배열을 회전 시키는 함수
	 * @param si x축 시작 지점
	 * @param sj y축 시작 지점
	 * @param ei x축 끝 지점
	 * @param ej y축 끝 지점
	 */
	static void rotate(int si, int sj, int ei, int ej) {
		int cx = si, cy = sj;  // 현재 좌표 업데이트
		
		int prev_value = board[cx][cy];  // 다음 좌표와 교환할 값을 미리 저장
		
		int dir = 0;  // 현재 진행 방향
		int r = 0;    // 현재 회전 횟수
		
		while (r < R) {    // R 만큼 회전
			// 다음 (x,y) 좌표 (nx,ny) 설정
			int nx = cx + dx[dir];
			int ny = cy + dy[dir];
			
			// 다음 좌표가 범위를 벗어나면 방향을 바꾸고 업데이트
			if (nx < si || nx > ei || ny < sj || ny > ej) {
				dir = (dir + 1) % 4;
				nx = cx + dx[dir];
				ny = cy + dy[dir];
			}
			
			// 다음 좌표의 값과 현재 좌표의 값을 swap
			int next_value = board[nx][ny];
			board[nx][ny] = prev_value;
			prev_value = next_value;
			
			cx = nx; cy = ny;  // 현재 좌표 업데이트
			
			// 만약 현재 좌표가 다시 시작지점으로 돌아오면, 방향과 이전 값을 초기화 후에 회전을 시작
			if (cx == si && cy == sj) {
				dir = 0;
				prev_value = board[cx][cy];
				r++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// 격자 정보 설정
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// x,y의 시작과 끝 지점을 설정
		int si = 0, ei = N-1;
		int sj = 0, ej = M-1;
		while (true) {  // 바깥쪽 라인부터 안쪽 라인까지 순회
			rotate(si, sj, ei, ej);
			si++; ei--; sj++; ej--;  // 시작과 끝 지점을 재설정
			
			if (si > ei || sj > ej) break;  // 종료 조건
		}
		
		
		// 출력부
		for (int i = 0; i < N; i++) {
			int[] line = board[i];
			for (int j = 0; j < M; j++) {
				sb.append(line[j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	}

}