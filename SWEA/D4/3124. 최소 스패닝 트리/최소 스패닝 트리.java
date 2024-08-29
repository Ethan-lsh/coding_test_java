import java.util.Arrays;
import java.util.Scanner;

/**
 * 메모리:188,052kb, 실행시간:6,585ms
 */
public class Solution {

	static class Edge implements Comparable<Edge> {
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}

	}

	static int V;
	static int[] parents;

	/**
	 * 집합의 부모 노드를 찾는다
	 * @param a 선택된 노드
	 * @return 선택된 노드가 가리키는 부모 노드
	 */
	static int findSet(int a) {
		if (parents[a] < 0)  // 값이 초기값 -1이면 연결된 부모 노드가 없음
			return a;
		else return parents[a] = findSet(parents[a]);
	}

	/**
	 * 합집합 연산. 노드 a와 b가 상관관계에 있는지 확인
	 * @param a 노드 a
	 * @param b 노드 b
	 * @return 합집합 여부
	 */
	static boolean union(int a, int b) {
		int aRoot = findSet(a);   // a의 부모
		int bRoot = findSet(b);   // b의 부모

		if (aRoot == bRoot)       // 만약 같으면 이미 합집합임
			return false;

		parents[aRoot] += parents[bRoot];  // a에 b의 가중치를 더함
		parents[bRoot] = aRoot;            // b에 a의 가중를 할당
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		/**
		 * Kruskal algorithm을 사용한 풀이
		 */
		int test_case = sc.nextInt();
		for (int tc = 1; tc <= test_case; tc++) {

			V = sc.nextInt();      // 정점의 수
			
			int E = sc.nextInt();  // 간선의 수

			// 간선 정보 초기화
			// Edge 객체를 만들어, 간선의 시작점, 끝점, 가중치를 관리한다
			Edge[] edges = new Edge[E]; 
			for (int i = 0; i < E; i++) {
				edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
			}
			
			Arrays.sort(edges);  // 알고리즘 수행을 위해, 가중치는 무조건 오름차순 정렬
			
			// 정점의 부모 노드를 모두 가중치 -1로 초기화
			parents = new int[V+1];
			for (int i = 0; i < V; i++)
				parents[i] = -1;
			
			long cnt = 0, cost = 0;                  // cnt=선택한 노드의 수; cost=최소 스패닝 가중치
			for (Edge edge : edges) {               // 모든 간선을 순회
				if (union(edge.start, edge.end)) {  // 간선의 시작과 끝에 연결된 정점이 서로 연결되 있다면 합집합
					cost += edge.weight;            // 그래프 가중치 증가
					if (++cnt == V - 1) break;      // 최소 신장트리 완성
				}
			}

			System.out.println("#"+tc+" "+cost);

		}

	}

}