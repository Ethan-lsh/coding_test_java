import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, X;
	static ArrayList<ArrayList<Edge>> adjList;
	
	static int[] goDist;
	static int[] backDist;

	static int[] ans;
	
	static final int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge>{
		int vertex, weight;

		public Edge(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++) adjList.add(new ArrayList<>());
		
		goDist = new int[N+1];
		Arrays.fill(goDist, INF);
		
		backDist = new int[N+1];
		Arrays.fill(backDist, INF);
		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList.get(from).add(new Edge(to, weight));
		}
		
		ans = new int[N+1];
		
		for (int n = 1; n <= N; n++)
			dijkstra(n, X, goDist, true);
		
		for (int n = 1; n <= N; n++)
			dijkstra(X, n, backDist, false);
			
		
//		System.out.println(Arrays.toString(goDist));
//		System.out.println(Arrays.toString(backDist));
//		System.out.println(Arrays.toString(ans));
		
		System.out.println(Arrays.stream(ans).max().getAsInt());
		
		
		
	}
	
	private static void dijkstra(int start, int goal, int[] result, boolean go) {
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		int[] minValue = new int[N+1];
		Arrays.fill(minValue, INF);
		
		for (Edge edge : adjList.get(start)) {
			
			boolean[] visited = new boolean[N+1];
			visited[start] = true;
			
			pq.offer(edge);
			
			minValue[start] = 0;
			minValue[edge.vertex] = edge.weight;
			
			int first_weight = edge.weight;
			while (!pq.isEmpty()) {
				
				Edge cur = pq.poll();
				
				if (cur.vertex == goal) {
					if (minValue[goal] == INF)
						minValue[goal] = first_weight;
					if (go) {
						result[start] = Math.min(result[start], minValue[goal]);  // 갈 때
					}
					else result[goal] = Math.min(result[goal], minValue[goal]);  // 올 때
					break;
				}
				
				visited[cur.vertex] = true;
				
				for (Edge next : adjList.get(cur.vertex)) {
					if (!visited[next.vertex] && minValue[next.vertex] > minValue[cur.vertex] + next.weight) {
						minValue[next.vertex] = minValue[cur.vertex] + next.weight;
						pq.add(new Edge(next.vertex, minValue[next.vertex]));
					}
				}
			}
			
		}
		
		if (go) ans[start] += result[start];
		else ans[goal] += result[goal];
	}

}