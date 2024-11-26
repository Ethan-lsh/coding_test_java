import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 크루스칼 알고리즘 기초 문제.
 * 유니온 파인드 기법을 사용해서 푼다
 * @author Sanghyeon Lee
 *
 */
public class Main {
	
	private static int N, M;
	private static ArrayList<Edge> edgeList;
	private static int[] parent;
	
	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
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
		
		edgeList = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(from, to, weight));
		}

		// 부모 집합이 자신을 가리키도록 초기화
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		
		// 간선의 가중치를 기준으로 오름차순 정렬
		Collections.sort(edgeList);
		
		// 마을은 모두 연결 되어 있기 때문에 최단 거리를 기준으로 탐색하다가
		// 마지막에 가장 큰 가중치를 갖는 간선을 없애면,최소 비용을 갖는 두 개의 마을로 나눌 수 있다
		// 최대 비용을 갖는 간선에 다른 마을이 추가되면 유지비 합의 최소를 보장 할 수 없다
		int ans = 0;
		int bigCost = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			
			// 사이클이 발생하는 간선은 제외한다
			if (find(edge.from) != find(edge.to)) {
				ans += edge.weight;
				union(edge.from, edge.to);
				
				bigCost = edge.weight; // 가장 큰 가중치
			}
		}
		
		System.out.println(ans-bigCost);
		
	}
	
	private static int find(int x) {
		if (x == parent[x]) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if (x != y) {
			parent[y] = x;
		}
	}

}