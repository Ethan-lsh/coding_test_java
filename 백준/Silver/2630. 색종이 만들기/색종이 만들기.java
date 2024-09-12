import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] board;
	
	static int blue, white;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recursion(0, 0, N);
		
		System.out.println(white + "\n" + blue);
		
	}
	
	/**
	 * 4등분 조각 후, 색종이 개수 세기
	 * @param x 좌상단 x꼭지점
	 * @param y 좌상단 y꼭지점
	 * @param nx 우하단 x꼭지점
	 * @param ny 우하단 y꼭지점
	 * @param size 현재 색종이 크기
	 */
	private static void recursion(int x, int y, int size) {
		
		// resize가 1이되면 더 이상 자를 수 없음
		if (size == 1) {
			if (board[x][y] == 1) blue++;
			else if (board[x][y] == 0) white++;
			return;
		}
		
		// 영역이 모두 같은 색으로 칠해졌는지 확인
		int sum = 0;
		for (int i = x; i < x+size; i++) {
			for (int j = y; j < y + size; j++) {
				sum += board[i][j];
			}
		}
		
		// 범위의 합이 현재 사이즈의 곱과 같다면 모두 1로 채워짐
		// 합이 0이라면 모두 0으로 채워짐
		if (sum == size*size) {
			blue++;
			return;
		} else if (sum == 0) {
			white++;
			return;
		}
		
		int resize = size / 2;
		
		// 4등분 재귀 함수 체크
		// 좌상단
		recursion(x, y, resize);
		
		// 우상단
		recursion(x, y+resize, resize);
		
		// 좌하단
		recursion(x+resize, y, resize);
		
		// 우하단
		recursion(x+resize, y+resize, resize);
		
		
	}

}