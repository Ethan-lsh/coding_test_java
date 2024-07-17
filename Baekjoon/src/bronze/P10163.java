package bronze;

import java.util.*;
import java.io.*;

public class P10163 {

	public static int[] check(int[][] arr, int n) {
		int[] ans = new int[n + 1]; // 각 색종이가 보이는 영역을 저장할 변수

		for (int i = 0; i < 1001; i++) {
			for (int j = 0; j < 1001; j++) {
				if (arr[i][j] > 0) {
					ans[arr[i][j]] += 1;
				}
			}
		}

		return ans;
	}

	public static void print(int[] a) {
		for (int i = 1; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] board = new int[1001][1001]; // 격자

		for (int n = 1; n < N + 1; n++) {
			String[] line = br.readLine().split(" ");

			// 색종이 정보
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			int width = Integer.parseInt(line[2]);
			int height = Integer.parseInt(line[3]);

			/*
			 * 좌하단이 (0,0)으로 시작하는 것에 함정이 있는 것 같다.
			 * 입력 (x, y)를 기존의 행렬 좌표 방식으로 맞춘 후, 색종이를 칠하려 했으나
			 * 격자 범위가 커서 그런가, 다들 그냥 쓴다..
			 */
			for (int j = x; j < (x + width); j++) {
				for (int k = y; k < (y + height); k++) {
					board[j][k] = n;
				}
			}
		}

		int[] ans = check(board, N);

		print(ans);
	}

}
