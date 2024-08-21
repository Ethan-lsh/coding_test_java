import java.io.*;

public class Main {

	static int N, board[][];
	static StringBuilder sb = new StringBuilder();
	
	static void partition(int x, int y, int size) {
		
		int sum = 0;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				sum += board[i][j];
			}
		}
		
		if (sum == 0) {
			sb.append("0");
		} else if (sum == size*size) {
			sb.append("1");
		} else {
			int half = size/2;
			sb.append("(");
			partition(x, y, half);
			partition(x, y+half, half);
			partition(x+half, y, half);
			partition(x+half, y+half, half);
			sb.append(")");
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = line.charAt(j) - '0';
			}
		}
		
		partition(0, 0, N);
		
		System.out.println(sb.toString());
		
	}

}