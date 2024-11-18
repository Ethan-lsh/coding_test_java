import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 벨만 포드 알고리즘.
 * 음의 사이클이 있는지 확인하는 문제
 * @author Sanghyeon Lee
 *
 */
public class Main {

	private static int N, M, W;
	private static List<List<Edge>> graph;
	private static long[] minArr;

	static class Edge {
		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				graph.add(new ArrayList<>());
			}

			// 양방향 도로
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());

				graph.get(from).add(new Edge(to, weight));
				graph.get(to).add(new Edge(from, weight));
			}

			// 웜홀
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());

				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = -Integer.parseInt(st.nextToken()); // 웜홀의 가중치는 음수로 변경

				graph.get(from).add(new Edge(to, weight));
			}

			if (bellman()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}

		}
	}

	private static boolean bellman() {
		// 최소 비용 그래프 업데이트
		minArr = new long[N + 1];
		Arrays.fill(minArr, 0);

		for (int n = 1; n <= N; n++) {
			for (int cur = 1; cur <= N; cur++) {
				for (Edge edge : graph.get(cur)) {
					if (minArr[edge.to] > minArr[cur] + edge.weight) {
						// 음수 사이클이 발생했다면 시간이 되돌아있게 된다
						if (n == N) return true;
						minArr[edge.to] = minArr[cur] + edge.weight;
					}
				}

			}
		}
		return false;
	}

}