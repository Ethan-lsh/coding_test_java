import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N, X;
	static int ans;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TC = Integer.parseInt(st.nextToken());
		for (int test_case = 1; test_case <= TC; test_case++) {
			
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 활주로를 행 별로 검사하는 함수
			buildRail(map);
			
			// 지형을 반시계 방향 90도 회전시켜서 행 별로 검사하는 함수를 재실행
			// 열 검사
			int[][] rotated_map = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					rotated_map[N-1-j][i] = map[i][j];
				}
			}
			
			buildRail(rotated_map);
			
			System.out.println("#"+test_case + " " + ans);
		}
		
	}
	
	private static void buildRail(int[][] arr) {
		// 행 별로 돌아가면서 활주로 건설 여부를 확인
		L: for (int r = 0; r < N; r++) {
			int[] row = arr[r];
			
			int cnt = 1;
			boolean down = false;
			
			for (int c = 1; c < N; c++) {
				if (row[c-1] == row[c]) cnt++;
				else if (row[c-1] + 1 == row[c]) { // 오르막
					// 이전까지 내리막이었으면 같은 높이의 땅은 2X 이상이어야 함
					if (down && cnt < 2*X) continue L;
					// 오르막인데 X만큼 같은 크기의 땅이 없으면
					if (!down && cnt < X) continue L;
					// 다음 열을 위한 변수 초기화
					down = false;
					cnt = 1;
				}
				else if (row[c-1] - 1 == row[c]) {  // 내리막
					// 내리막인데 X만큼 같은 크기의 땅이 없으면
					if (down && cnt < X) continue L;
					// 다음 열을 위한 초기화
					down = true;
					cnt = 1;
				}
				else continue L;
			}
			
			if (!down || down && cnt >= X) ans++;
			
		}
		
	}

}