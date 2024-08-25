import java.io.*;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {

	static int R, C;          // 격자 크기
	static char[][] board;  // 2차원 격자
	static int ans;           // 최대 파이프 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// 격자 초기화
		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			board[i] = br.readLine().toCharArray();
		}
		
		
		/**
		 * 재귀 함수 풀이
		 * 0번 행부터 시작하여 열 인덱스를 증가시키면서 파이프가 배치 가능한지 탐색한다.
		 * 마지막 열에 도착하면 파이프 개수를 증가시키고, 범위를 벗어나거나 빌딩을 만나 이동이 불가능한 경우 다음 행을 탐색한다.
		 * 
		 * #이동불가능 조건
		 * 1.다른 파이프가 이미 존재
		 * 2.빌딩이 있다
		 * 3.범위를 벗어난다
		 */
		for (int i = 0; i < R; i++) {
			if (recursion(i, 0)) ans++;
		}
		
		System.out.println(ans);
		
	}
	
	private static boolean recursion(int x, int y) {
		board[x][y] = '-';
		
		if (y == C-1)
			return true;
		
		// 오른쪽 위
		if (x > 0 && board[x-1][y+1] == '.')
			if (recursion(x-1, y+1)) return true;
		
		// 오른쪽
		if (board[x][y+1] == '.')
			if (recursion(x, y+1)) return true;
		
		// 오른쪽 아래
		if (x+1 < R && board[x+1][y+1] == '.')
			if (recursion(x+1, y+1)) return true;
		
		return false;
		
	}
	

}