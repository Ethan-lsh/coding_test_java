import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, R;
	static int[][] board;
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static void rotate(int si, int sj, int ei, int ej) {
		int cx = si, cy = sj;
		
		int prev_value = board[cx][cy];
		int dir = 0;
		int r = 0;
		while (r < R) {
			int nx = cx + dx[dir];
			int ny = cy + dy[dir];
			
			if (nx < si || nx > ei || ny < sj || ny > ej) {
				dir = (dir + 1) % 4;
				nx = cx + dx[dir];
				ny = cy + dy[dir];
			}
			
			int next_value = board[nx][ny];
			board[nx][ny] = prev_value;
			prev_value = next_value;
			
			cx = nx; cy = ny;
			
			if (cx == si && cy == sj) {
//				board[cx][cy] = prev_value;
				dir = 0;
				prev_value = board[cx][cy];
				r++;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		System.out.println("1============");
//		for (int[] l : board) {
//			System.out.println(Arrays.toString(l));
//		}
		
		int si = 0, ei = N-1;
		int sj = 0, ej = M-1;
		while (true) {
			rotate(si, sj, ei, ej);
			si++; ei--; sj++; ej--;
			
			if (si > ei || sj > ej) break;
		}
		
		
//		System.out.println("2============");
		for (int i = 0; i < N; i++) {
			int[] line = board[i];
			for (int j = 0; j < M; j++) {
				sb.append(line[j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
		
	}

}