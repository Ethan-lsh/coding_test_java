import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[100][100];
		for (int n = 0; n < N; n++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (board[x+i][y+j] == 0) {
						board[x+i][y+j] = 1;
					}
				}
			}
		}
		
		int ans = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(board[i][j] == 1) ans++;
			}
		}
		
		System.out.println(ans);
	}

}
