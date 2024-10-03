import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * BFS, DFS 탐색 문제
 * 인접리스트를 활용하여 부모 노드를 찾아 저장하는 방식을 사용
 * @author Sanghyeon Lee
 *
 */
public class Main {

	static int N;
	static List<List<Integer>> adjList = new ArrayList<>();
	static boolean[] isSelected;
	static int[] parents;

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		
		for (int i = 0; i <= N; i++)
			adjList.add(new ArrayList<Integer>());
		
		parents = new int[N+1];
		isSelected = new boolean[N+1];
		
		for (int i = 0; i < N-1; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
		
			adjList.get(a).add(b);
			adjList.get(b).add(a);
		}
		
		/**
		 * 1번 노드가 조상 노드로 정해져 있기 때문에
		 * 1번 노드부터 인접 노드를 찾아 나가면 현재 노드가 조상 노드가 됨
		 */
		bfs(1);
		
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		
		q.offer(start);
		isSelected[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			// 현재 노드와 인접한 모든 노드들을 순회
			for (int next : adjList.get(cur)) {
				// 방문하지 않은 곳이면 해당 노드의 부모는 현재 노드
				if (!isSelected[next]) {
					isSelected[next] = true;
					parents[next] = cur;
					q.offer(next);
				}
			}
			
		}
		
	}
}