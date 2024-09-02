import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 메모리:13364kb, 실행시간:108ms
 */
public class Main {
	
	static List<List<Integer>> graph;  // 그래프
	static boolean[] visited;          // 방문 노드
	static int ans;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		// ArrayList 중첩 구문 생성
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		int E = sc.nextInt();
		
		
		// 무방향 그래프 생성
		// (1, 2) (2, 1)은 같은 간선
		for (int e = 0; e < E; e++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		visited = new boolean[N+1];
		dfs(1);
		
		System.out.println(ans-1);
	}
	
	static void dfs(int node) {
		
		visited[node] = true;
		ans++;
		
		// 현재 노드에 연결된 모든 노드에 대해 탐색
		for (int next : graph.get(node)) {
			if (!visited[next]) dfs(next);
		}
		
		return;
		
	}

}