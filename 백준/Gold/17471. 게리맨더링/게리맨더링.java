import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] population;
	static List<List<Integer>> adjList = new ArrayList<>();
	static int ans = Integer.MAX_VALUE;
	
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		population = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			population[i] = Integer.parseInt(st.nextToken());

		/**
		 * 인접리스트 초기화
		 */
		for (int i = 0; i <= N; i++)
			adjList.add(new ArrayList<Integer>());
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int val = Integer.parseInt(st.nextToken());
				adjList.get(i).add(val);
			}
		}

//		System.out.println(adjList.get(1).toString());

		isSelected = new boolean[N+1];
		set(1, 0);
		
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}

	/**
	 * 집합을 구하는 함수
	 * @param index 시작 인덱스
	 * @param cnt 선택한  노드의 개수
	 */
	private static void set(int index, int cnt) {
		if (index == N) {
			// 선거구는 반드시 1개의 구역을 가져야 하는 조건
			if (cnt == N || cnt == 0) return;
			
			List<Integer> graphA = new ArrayList<>();
			List<Integer> graphB = new ArrayList<>();
			
			// 방문여부를 기준으로 선거구를 구분
			for (int k = 1; k <= N; k++) {
				if (isSelected[k])
					graphA.add(k);
				else graphB.add(k);
			}

			// 선거구 그래프의 유효성 검사
			if (!isGraph(graphA)) return;
			if (!isGraph(graphB)) return;
			
			// 각 선거구의 인구수 합을 계산
			int sumA = 0;
			for (int a : graphA) {
				sumA += population[a];
			}
			
			int sumB = 0;
			for (int b : graphB) {
				sumB += population[b];
			}
			
			// 정답 계산
			ans = Math.min(ans, Math.abs(sumA - sumB));
			return;
		}
		
		isSelected[index] = true;
		set(index + 1, cnt + 1);
		
		isSelected[index] = false;
		set(index + 1, cnt);
		
	}
	
	/**
	 * 선택된 노드들이 유효한 그래프인지 체크하는 함수
	 * @param nodes 선택된 노드들
	 * @return 참/거짓 여부
	 */
	private static boolean isGraph(List<Integer> nodes) {
		
		boolean[] visited = new boolean[N+1];

		Queue<Integer> q = new ArrayDeque<>();
		q.add(nodes.get(0));
		visited[nodes.get(0)] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			// 현재 노드의 인접 노드들을 순회
			// 인접 노드가 선택된 노드들에 포함되는지 또는 방문한 곳인지를 체크
			for (int next : adjList.get(cur)) {
				if (!nodes.contains(next)) continue;
				if (visited[next]) continue;
				
				visited[next] = true;
				q.add(next);
			}
		}
		
		// 하나라도 방문하지 않은 노드가 있다면 연결성이 없음
		for (int node : nodes) {
			if (!visited[node]) return false;
		}
		
		return true;
	}
	
}