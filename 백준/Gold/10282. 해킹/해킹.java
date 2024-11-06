import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, d, c;
	static List<List<Edge>> adjList;
	
	static class Edge implements Comparable<Edge> {
		
		int to;
		int time;
		
		public Edge(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.time, o.time);
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int TEST_CASE = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= TEST_CASE; tc++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adjList = new ArrayList<>();
			for (int i = 0; i <= n; i++)
				adjList.add(new ArrayList<>());
			
			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				
				int to = Integer.parseInt(st.nextToken());
				int from = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				// 의존성은 단방향이다
				adjList.get(from).add(new Edge(to, time));
			}
			
			dijkstra(c, 0);
		}
		
	}
	
	private static void dijkstra(int start, int time) {
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		int cnt = 0;  // 감염된 컴퓨터의 개수
		int maxTime = 0;  // 최대 시간
		while (!pq.isEmpty()) {
			
			Edge cur = pq.poll();

			// pq의 후순위에 있던 cur을 처내기 위한 조건문
			if (cur.time > dist[cur.to]) continue;
			
			cnt++;
			maxTime = cur.time;
			
			// 인접 노드로 감염
			for (Edge next : adjList.get(cur.to)) {
				int newTime = cur.time + next.time;
				
				if (newTime < dist[next.to]) {
					dist[next.to] = newTime;
					pq.add(new Edge(next.to, newTime));
				}
			}
		}
		
		System.out.println(cnt + " " + maxTime);
		
	}

}