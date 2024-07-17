package bronze;

import java.util.*;
import java.io.*;

public class P10163 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for (int n = 0; n < N; n++) {
			String[] line = br.readLine().split(" ");
			
			// 색종이 정보
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			int width = Integer.parseInt(line[2]);
			int height = Integer.parseInt(line[3]);
		}
		
		int[][] board = new int[1001][1001];
		
		
		// 2. 색종이를 추가할 때 마다 체크하기.
		// 이미 칠해진 좌표는 개수로 카운트 하지 않는다.

	}

}
