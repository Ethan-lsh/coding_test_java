import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * DFS 문제
 * 트리의 지름 1967 문제와 동일
 * @author SSAFY
 *
 */
public class Main {

	static int V;
	static List<List<Edge>> adjList;
	static boolean[] visited;
	
	static int start_node;
	
	static int ans;
	
	
	static class Edge {
		int vertex;
		int weight;
		
		Edge (int vertex, int weight){
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		for (int i = 0; i <= V; i++)
			adjList.add(new ArrayList<>());
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			
			int v = Integer.parseInt(st.nextToken());
			
			while (st.hasMoreTokens()) {
				
				int _next = Integer.parseInt(st.nextToken());
				
				if (_next == -1) break;
				
				int _weight = Integer.parseInt(st.nextToken());
				
				adjList.get(v).add(new Edge(_next, _weight));

			}
			
		}
		
		/**
		 * 리프 노드 만을 찾아서 탐색하는 방법 -> 시간초과
		 */
//		List<Integer> leaf_nodes = new ArrayList<>();
//		for (int v = 0; v < V; v++) {
//			// 간선의 개수가 하나라면 '리프 노드'
//			if (adjList.get(v).size() == 1) {
//				leaf_nodes.add(v);
//			}
//		}
//		
//		// 리프 노드 만을 찾아서 거리를 탐색하기
//		for (int n : leaf_nodes) {
//			visited = new boolean[V+1];
//			dfs(n, 0);
//		}
		
		/**
		 * 최장 거리를 갖는 임의의 정점 하나를 찾아서 기준 점으로 잡고 탐색하기
		 * 어떤 정점을 임의의 정점으로 잡아도 된다.
		 * -> DFS를 수행하면 해당 정점에서 가장 거리가 먼 정점으로 가기 때문에, 
		 * 결국 도달하는 마지막 정점이 트리의 지름을 구성하는 두 개의 정점 중 하나가 되는 것과 같다.
		 */
		// 임의의 정점  먼저 구하기
		visited = new boolean[V+1];
		dfs(1, 0);
		
		// 최장 거리 구하기
		visited = new boolean[V+1];
		dfs(start_node, 0);
		
		System.out.println(ans);
		
	}
	
	private static void dfs(int node, int cost) {

		if (cost > ans) {
			ans = cost;
			start_node = node;
		}
		
		visited[node] = true;
		
		for (Edge edge : adjList.get(node)) {
			if (!visited[edge.vertex]) {
				dfs(edge.vertex, cost + edge.weight);
			}
		}
		
	}

}