import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 4방탐색과 공기청정기 구현이 핵심인 문제
 * 공기청정기의 바람을 역으로 생각해야 함
 * @author Sanghyeon Lee
 *
 */
public class Main {

	static int R, C, T;
	static int[][] board;
	static int[][] getBoard;
	static List<int[]> machine;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		
		machine = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int val = Integer.parseInt(st.nextToken());
				if (val == -1) machine.add(new int[] {i, j});
				board[i][j] = val;
			}
		}
		
		for (int t = 0; t < T; t++) {
			spread();
			up_wind();
			down_wind();
		}
		
		// 남아있는 먼지 합
		int sum = 0;
		for (int i = 0; i < R; i++) {
			sum += Arrays.stream(board[i]).sum();
		}
		System.out.println(sum + 2);
		
	}
	
	private static void spread() {
		
		getBoard = new int[R][C];  // 움직인 모래의 양을 저장할 배열

		// 배열을 순회하면서 모래가 있는 곳을 찾으면, 모래 변화량을 계산하여 getBoard에 저장
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] > 0) {
					calDiff(i, j);
				}
			}
		}
		
		// 모든 모래에 대해 계산이 끝나면, 기존 배열을 업데이트
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				board[i][j] += getBoard[i][j];
			}
		}
		
	}

	/**
	 * 모래 변화량을 계산하는 함수
	 * @param x 모래의 x좌표
	 * @param y 모래의 y좌표
	 */
	private static void calDiff(int x, int y) {
		int move_amount = (int) board[x][y] / 5;
		int cnt = 0;  // 확산된 방향 개수
		
		for (int d = 0; d < 4; d++) {
			
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
			
			if (board[nx][ny] == -1) continue;
			
			getBoard[nx][ny] += move_amount;
			getBoard[x][y] -= move_amount;
		}
	}
	
	/**
	 * 공기청정기 윗부분 바람
	 */
	private static void up_wind() {
		int[] up = machine.get(0);
		
		// 공기청정기 바로 위 좌표
		int x = up[0]-1, y = up[1];
		
		// 위 공기청정
		// 반대로 탐색해 나감
		int[] dir = {0, 3, 1, 2};
		
		int d = 0;
		
		while (true) {

			// 반대 방향 다음 좌표
			int nx = x + dx[dir[d]];
			int ny = y + dy[dir[d]];
			
			// 범위를 벗어나면 방향 전환과 방향 전환 횟수 증가
			if (nx < 0 || nx > up[0] || ny < 0 || ny >= C) {
				d++;
				continue;
			}
			
			// 다음 좌표가 공기청정기면 끝
			if (board[nx][ny] == -1) {
				board[x][y] = 0;
				break;
			}
			
			// 다음 좌표는 이전 좌표에 넣는다
			board[x][y] = board[nx][ny];
			
			// 좌표 업데이트
			x = nx;
			y = ny;
			
		}
		
	}
	
	/**
	 * 공기청정기 아래 바람
	 */
	private static void down_wind() {
		int[] down = machine.get(1);
		
		int x = down[0]+1, y = down[1];
		
		// 아래 방향 바람
		int[] dir = {1, 3, 0, 2};
		
		int d = 0;
		while (true) {
			
			int nx = x + dx[dir[d]];
			int ny = y + dy[dir[d]];
			
			// 범위를 벗어나면 방향 전환과 방향 전환 횟수 증가
			if (nx < down[0] || nx >= R || ny < 0 || ny >= C) {
				d++;
				continue;
			}
			
			if (board[nx][ny] == -1) {
				board[x][y] = 0;
				break;
			}
			
			// 다음 좌표는 이전 좌표에 넣는다
			board[x][y] = board[nx][ny];
			
			// 좌표 업데이트
			x = nx;
			y = ny;
			
		}
		
	}
	
}