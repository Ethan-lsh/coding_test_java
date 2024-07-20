import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[][] board = new int[101][101];  // 직사각형을 표시할 보드

		// 직사각형 좌표
		// 0:x시작; 1:y시작; 2:x끝; 3:y끝
		int[][] info = new int[4][4];
		for (int i = 0; i < 4; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				info[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		int ans = 0;
		for (int[] row : info) {
			int x0 = row[0];
			int y0 = row[1];
			int x1 = row[2];
			int y1 = row[3];
			
			for (int x = y0; x < y1; x++) {
				for (int y = x0; y < x1; y++) {
					if (board[x][y] == 0) {
						ans++;
						board[x][y] = 1;
					}
				}
			}
		}
		
		System.out.println(ans);

	}

}
