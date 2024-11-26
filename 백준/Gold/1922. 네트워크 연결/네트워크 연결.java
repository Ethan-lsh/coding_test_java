import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 크루스칼 알고리즘 기초
 * @author Sanghyeon Lee
 *
 */
public class Main {

	static int N;
	static int M;
	
	static List<Edge> edgeList;
	static int[] parent;
	
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;

		Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return weight - o.weight;
		}

	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
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
		
		int ans = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			
			// 사이클이 발생하는 간선은 제외한다
			if (find(edge.start) != find(edge.end)) {
				ans += edge.weight;
				union(edge.start, edge.end);
			}
		}
		
		System.out.println(ans);
		
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