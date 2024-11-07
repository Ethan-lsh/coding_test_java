import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[][] map;
	
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][N+1];
		
		for (int[] row : map) Arrays.fill(row, INF);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			map[from][to] = 1;
			map[to][from] = 1;
		}
			
		// 플로이드 워셜 알고리즘
		for (int n = 1; n <= N; n++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][n] != INF && map[n][j] != INF) {
						map[i][j] = Math.min(map[i][j], map[i][n] + map[n][j]);
					}
				}
			}
		}
		
		// 베이컨 수가 가장 작은 사람을 계산
		int[] result = new int[N+1];
		for (int n = 1; n <= N; n++) {
			for (int i = 1; i <= N; i++) {
				if (n != i) result[n] += map[n][i];
			}
		}
		
//		System.out.println(Arrays.toString(result));
		int ans = 0;
		int minVal = Integer.MAX_VALUE;
		for (int n = 1; n <= N; n++) {
			if (result[n] < minVal) {
				ans = n;
				minVal = result[n];
			}
		}
		
		System.out.println(ans);
		
	}

}