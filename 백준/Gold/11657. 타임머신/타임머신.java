import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 벨만 포드 알고리즘 기초
 * @author Sanghyeon Lee
 *
 */
public class Main {

	private static final long INF = Long.MAX_VALUE;
	
	private static int N, M;
	private static List<List<Edge>> adjList;
	private static long[] minArr;
	
	static class Edge {
		int v, weight;

		public Edge(int v, int weight) {
			super();
			this.v = v;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) adjList.add(new ArrayList<>());
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList.get(from).add(new Edge(to, weight));
		}
		
		boolean negative_cycle = bellman(1);
		
		StringBuffer sb = new StringBuffer();
		if (negative_cycle) {
			System.out.println(-1);
		} else {
			for (int i = 2; i <= N; i++) {
				if (minArr[i] == INF)
					sb.append(-1).append("\n");
				else
					sb.append(minArr[i]).append("\n");
			}
		}
		
		System.out.println(sb);

	}
	
	private static boolean bellman(int start) {
		// 최소 비용 테이블 초기화
		minArr = new long[N + 1];
		Arrays.fill(minArr, INF);
		
		minArr[start] = 0;
		
		for (int n = 1; n <= N; n++) { // N 번 도는 반복문
			for (int i = 1; i <= N; i++) {
				for (Edge edge : adjList.get(i)) {
					// 최소 비용 업데이트 조건은 다익스트라와 같다
					if (minArr[i] != INF && minArr[edge.v] > minArr[i] + edge.weight) {
						minArr[edge.v] = minArr[i] + edge.weight;
						// N번 순환이 발생하면 음수 사이클이 존재함
						if (n == N) return true; 
					}
				}
			}
		}
		
		return false;
	}

}