import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * DFS를 활용한 그래프 탐색 문제
 * 최단 경로가 아닌 가중치가 있는 최대 길이를 구하는 문제이기 때문에 MST,BFS 모두 안됌
 * @author Sanghyeon Lee
 *
 */
public class Main {

	static int n;
	static List<List<Edge>> edges;
	static boolean[] visited;
	static int ans;
	
	static class Edge {
		int num;
		int weight;
		
		Edge (int num, int weight){
			this.num = num;
			this.weight = weight;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
		for (int i = 0; i <= n; i++)
			edges.add(new ArrayList<>());
		
		
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			// NOTE:양방향 그래프인점을 유의한다
			edges.get(parent).add(new Edge(child, dist));
			edges.get(child).add(new Edge(parent, dist));
		}
		
		// 모든 정점에 대해 탐색
		// 후에 최적화 할 수 있는지 보기
		for (int i = 1; i <= n; i++) {
			visited = new boolean[n+1];
			dfs(i, 0);
		}
		
		System.out.println(ans);
		
	}
	
	private static void dfs(int node, int cost) {
		visited[node] = true;
		
		for (Edge next : edges.get(node)) {
			if (!visited[next.num]) {
				dfs(next.num, cost + next.weight);
			}
		}
		
		ans = Math.max(ans, cost);
	}

}