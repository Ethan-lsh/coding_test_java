import java.io.*;
import java.util.StringTokenizer;

public class Solution {

	static int N, board[][];
	static String cmd;

	static void rotate(int rotateCnt) {
		for (int i = 0; i < rotateCnt; i++) {
			int[][] temp = new int[N][N];
			// 시계방향 90도 회전
			for (int r=0;  r<N; r++) {
				for (int c=0; c < N; c++) {
					temp[c][N-r-1] = board[r][c];
				}
			}
			board = temp;
		}
		
	}
	
	
	static void up() {
		int[][] temp = new int[N][N];   // 타일의 움직임을 저장하기 위한 2차원 배열
		for (int c = 0; c < N; c++) {
			int idx = 0;  // 요소를 넣을 인덱스
			for (int r = 0; r < N; r++) {
				if (board[r][c] == 0) continue;  // 0이라면 건너뜀
				
				// board[r][c]에 값이 있고, temp[idx][c] 자리가 0인 경우, 바로 요소를 배치
				if (temp[idx][c] == 0) {
					temp[idx][c] = board[r][c];
				}
				// temp[idx][c]가 0이 아니면, 이미 값이 존재하고 있음
				else {
					// 값이 서로 같다면
					if (temp[idx][c] == board[r][c]) {
						temp[idx++][c] *= 2;
					} // 값이 다르다면 다음 위치에 요소를 놓음
					else {
						temp[++idx][c] = board[r][c];
					}
				}
			}
		}
		board = temp;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= test_case; tc++) {
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			cmd = st.nextToken();

			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int rotateCnt = 0;  // up 으로만 처리할 수 있도록 배열을 시계방향 회전 시킨다
			switch(cmd) {
			case "down":rotateCnt=2;break;
			case "left":rotateCnt=1;break;
			case "right":rotateCnt=3;break;
			}
			
			rotate(rotateCnt);  // 회전 횟수에 따른 회전
			
			up();  // 타일 밀기
			
			rotate((4-rotateCnt)%4);  // 복구 회전

			for (int i = 0; i < N; i++) {
				int[] line = board[i];
				for (int j = 0; j < N; j++) {
					sb.append(line[j] + " ");
				}
				sb.append("\n");
			}

			System.out.println("#"+tc);
			System.out.printf(sb.toString());

		}
	}

}