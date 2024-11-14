import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Dijkstra 문제
 *
 */
public class Main {
	
	static int N, M, X;  // 노드의 개수, 간선의 개수, 도착점   
	
	// 가는 방향의 간선, 오는 방향의 간선
	static ArrayList<ArrayList<Edge>> goEdges, reverseEdges;
	
	// 도착지로 가는 최소 가중치 배열, 돌아오는 최소 가중치 배열
	static int[] goDist, reverseDist;

	static final int INF = Integer.MAX_VALUE;
	
	static class Edge implements Comparable<Edge>{
		int vertex, weight;

		public Edge(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		goEdges = new ArrayList<>();
		reverseEdges = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			goEdges.add(new ArrayList<>());
			reverseEdges.add(new ArrayList<>());
		}
		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			/*
			 * 이 문제의 핵심은 출발지와 도착지를 통일해서 다익스트라 알고리즘을 수행하는 것이 최적화의 가장 큰 부분이다.
			 * 기존에 from -> to 까지의 간선 정보는 i번째 친구가 X로 가는 간선의 가중치를 저장한다.
			 * 반면에, to -> from 까지의 간선 정보는 X에서 i번째 친구가 있는 곳으로 가중치를 저장하는 것과 같다
			 * 예를 들어, '1 -> X = 2' 'X -> 1 = 4' 라고 가정한다면
			 * X의 관점에서 1로 갈 때 2와 4의 가중치를 더하면 왕복 운동과 동일한 효과를 내는 것이다.
			 */
			goEdges.get(from).add(new Edge(to, weight));
			reverseEdges.get(to).add(new Edge(from, weight));
		}
		
		// 최소 가중치 배열 초기화
		goDist = new int[N+1];
		reverseDist = new int[N+1];
		
		// 최소 가중치 배열 최대 값으로 정의
		Arrays.fill(goDist, INF);
		Arrays.fill(reverseDist, INF);
		
		// X의 관점에서 다익스트라 실행
		dijkstra(X, goDist, goEdges);
		dijkstra(X, reverseDist, reverseEdges);
		
		int ans = 0;
		for (int i = 1; i <= N; i++) {
			ans = Math.max(ans, goDist[i] + reverseDist[i]);
		}
		
		System.out.println(ans);
		
	}
	
	private static void dijkstra(int start, int[] minValue, ArrayList<ArrayList<Edge>> adjList) {
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		minValue[start] = 0;
		
		while (!pq.isEmpty()) {
			
			Edge cur = pq.poll();
			
			if (cur.weight > minValue[cur.vertex]) continue;
			
			for (Edge next : adjList.get(cur.vertex)) {
				
				if (minValue[next.vertex] > minValue[cur.vertex] + next.weight) {
					minValue[next.vertex] = minValue[cur.vertex] + next.weight;
					
					// PQ에 전달하는 가중치는 최소 가중치를 전달해야 한다.
					pq.offer(new Edge(next.vertex, minValue[next.vertex]));
				}
			}
			
		}
	}

}