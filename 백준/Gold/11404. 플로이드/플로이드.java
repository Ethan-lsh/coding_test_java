import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] minDistance;
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		
		minDistance = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) minDistance[i][j] = 0;
				else minDistance[i][j] = INF;
			}
		}
		
		for (int i = 0; i < m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if (minDistance[from-1][to-1] > dist)
				minDistance[from-1][to-1] = dist;
			
//			minDistance[from-1][to-1] = dist;
		}
		

		// 플로이드-워셜 알고리즘
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (minDistance[i][k] != INF && minDistance[k][j] != INF) {
						minDistance[i][j] = Math.min(minDistance[i][j], minDistance[i][k] + minDistance[k][j]);
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(minDistance[i][j] == INF ? 0 : minDistance[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}