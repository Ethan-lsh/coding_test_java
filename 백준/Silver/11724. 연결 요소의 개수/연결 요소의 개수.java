import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static List<List<Integer>> adjList;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1]; // 방문배열 초기화
		
		// 인접리스트 생성
		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adjList.add(new ArrayList<>());
		
		
		// 간선 정보 초기화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 무방향 그래프이므로, from <-> to 는 서로 연결됨
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		int ans = 0;
		for (int n = 1; n <= N; n++) {
			if (visited[n]) continue;  // 이미 방문한 정점은 패스
			bfs(n);  // bfs 탐색 실행
			ans++;
		}
		
		System.out.println(ans);
	}
	
	/**
	 * 연결된 노드를 순회하기 위한 BFS
	 * @param s 시작 노드
	 */
	private static void bfs(int s) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.add(s);
		visited[s] = true;
		
		while (!q.isEmpty()) {
			int node = q.poll();
			
			// node의 인접리스트를 순회, 방문하지 않은 정점들에 대한 방문처리
			for (int next_node : adjList.get(node)) {
				if (!visited[next_node]) {
					visited[next_node] = true;
					q.add(next_node);
				}
			}
		}
		return;
	}

}