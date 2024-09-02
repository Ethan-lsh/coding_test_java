import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, M, V;
	static List<List<Integer>> graph;
	static boolean[] visited;
	
	static int ans;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		V = sc.nextInt();
		
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
		
		for (int j = 0; j < M; j++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		visited = new boolean[N+1];
		dfs(V, 0);
		sb.append("\n");
		
		Arrays.fill(visited, false);
		bfs(V);
		System.out.println(sb);
	}
	
	static void dfs(int x, int depth) {
		if (depth == N) {
			return;
		}
		
		visited[x] = true;
		sb.append(x + " ");
		
		List<Integer> adjNodes = graph.get(x);
		Collections.sort(adjNodes);
		
		for (int next : adjNodes) {
			if (!visited[next]) dfs(next, depth+1);
		}
		
		return;
	}
	
	static void bfs(int x) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(x);
		visited[x] = true;
		while (!q.isEmpty()) {
			int node = q.poll();

			sb.append(node + " ");
			
			List<Integer> adjNodes = graph.get(node);
			Collections.sort(adjNodes);
			
			for (int next : adjNodes) {
				if (!visited[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
			
		}
	}
	

}