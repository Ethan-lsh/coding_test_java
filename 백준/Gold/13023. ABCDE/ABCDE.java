import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	static int N, M;
	static int cnt;
	static ArrayList<ArrayList<Integer>> adjMatrix;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		adjMatrix = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			adjMatrix.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			adjMatrix.get(from).add(to);
			adjMatrix.get(to).add(from);
		}
		
		visited = new boolean[N];
		for (int n = 0; n < N; n++) {
			dfs(n, 1);
		}
		
		System.out.println(0);
	}
	
	
	
	private static void dfs(int curr, int cnt) {

		if (cnt == 5) {
			System.out.println(1);
			System.exit(0);  // ABCDE를 찾으면 프로그램을 종료
		}
		
		visited[curr] = true;  // 현재  node 방문 처리
		
		for (int adj : adjMatrix.get(curr)) {
			if (visited[adj]) continue;  
			
			dfs(adj, cnt+1);
		}
		
		visited[curr] = false;  // 방문 처리 해제
	}

}