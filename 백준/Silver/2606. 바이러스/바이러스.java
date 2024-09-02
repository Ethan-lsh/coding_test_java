import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<List<Integer>> graph;
	static boolean[] visited;
	static int ans;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		int E = sc.nextInt();
		
		
		for (int e = 0; e < E; e++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		visited = new boolean[N+1];
		bfs(1);
		
		System.out.println(ans-1);
	}
	
	static void bfs(int node) {
		
		visited[node] = true;
		ans++;
		
		for (int next : graph.get(node)) {
			if (!visited[next]) bfs(next);
		}
		
		return;
		
	}

}