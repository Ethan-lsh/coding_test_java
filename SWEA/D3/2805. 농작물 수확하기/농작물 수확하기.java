import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;           // 농장의 크기
	static int[][] board;   // 농장
	static int ans;         // 정답
	static int span;        // 범위 확장 크기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int test_case = Integer.parseInt(br.readLine());
		for (int tc=1; tc <= test_case; tc++) {
			ans = 0;
			span = 1;
			
			N = Integer.parseInt(br.readLine());
			
			board = new int[N][N];
			
			for (int i = 0; i < N; i++) {  // 2차원 배열 생성
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = line.charAt(j) - '0';
				}
			}
			
			int middle = N / 2;                     // 격자의 중심 인덱스
			int start = middle; int end = middle;   // 처음 시작과 끝은 중심 인덱스로 설정
			
			for (int i = 0; i < N; i++) {
				if (i == middle) span = -1;           // 행 좌표가 중앙에 도달하면 범위 확장 값을 -1로 설정 
				for (int j = start; j <= end; j++) {  // 범위 안의 값을 더한다
					ans += board[i][j];
				}
				// 범위 재설정
				start -= span;
				end += span;
			}
			
			System.out.println("#"+tc+" "+ans);
			
		}
	}
}