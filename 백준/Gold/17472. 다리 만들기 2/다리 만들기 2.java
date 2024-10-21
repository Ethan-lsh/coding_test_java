import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * MST (Kruskal 알고리즘 문제) 
 * @author Sanghyeon Lee
 *
 */

public class Main {

	static int N, M;                   // 격자의 크기
	static int[][] map;                // 땅과 바다의 정보
	static int[][] marker;             // 섬의 위치를 표시할 격자
	static List<List<int[]>> island;   // 각 섬을 구성하는 좌표
	
	static int V;               // 섬의 개수
	static List<Edge> edges;    // 다리(=간선)의 개수
	static int[] parents;       // 집합
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int ans;
	
	/**
	 * 비교 가능한 간선 클래스 
	 */
	static class Edge implements Comparable<Edge> {
		int start, end, weight;
		
		public Edge (int start, int end, int weight) {
			this.start = start;
			this.end = end;
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
		
		// 땅과 바다의 정보 저장
		map = new int[N][M];
		marker = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int val = Integer.parseInt(st.nextToken());
				map[i][j] = val;
			}
		}
		
		
		// 1. 섬 찾기
		// 섬을 구성하는 땅들의 좌표를 찾아 List에 저장
		// BFS를 사용
		island = new ArrayList<>();
		island.add(null);  // 0번 인덱스는 제로 처리
		int cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 땅이면서 좌표 값이 지정되지 않은 (-> 아직 방문하지 않은 땅) 경우
				if (map[i][j] == 1 && marker[i][j] == 0) {
					find_island(i, j, cnt++);
				}
			}
		}
		
		// 2.섬 사이에 다리를 연결하기
		// 하나의 섬에서 놓을 수 있는 모든 다리의 데이터를 구한다
		V = island.size();  // 섬의 개수
		edges = new ArrayList<>();
		build_bridge();
		
		
		// 3.Kruskal로 놓여진 다리들 중 최소 가중치를 갖는 경로를 찾는다
		Collections.sort(edges);
		
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = -1;
		}
		
		int n = 0, cost = 0;
		for (Edge edge : edges) {
			if (union(edge.start, edge.end)) {
				cost += edge.weight;
				++n;
//				if (++n == V-1) break;
			}
		}
		
		ans = (n == V-2) ? cost : -1;
		
		System.out.println(ans);
		
	}
	
	/**
	 * 섬을 찾는 함수. 일반적인 BFS를 통해 찾는다
	 * @param x x좌표
	 * @param y y좌표
	 * @param cnt 섬의 번호
	 */
	private static void find_island(int x, int y, int cnt) {
		List<int[]> i = new ArrayList<>();
		Queue<int[]> q = new ArrayDeque<>();  
		
		i.add(new int[] {x, y});
		q.add(new int[] {x, y});
		marker[x][y] = cnt;
		
		while (!q.isEmpty()) {
			
			int[] pos = q.poll();
			
			for (int d = 0; d < 4; d++) {
				
				int nx = pos[0] + dx[d];
				int ny = pos[1] + dy[d];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (marker[nx][ny] != 0) continue;
				
				if (map[nx][ny] == 1) {
					marker[nx][ny] = cnt;
					q.add(new int[] {nx, ny});
					i.add(new int[] {nx, ny});
				}
			}
		}
		
		island.add(i);  // 찾은 섬의 정보를 List<int[]> 형태로 저장
	}
	
	/**
	 * 다리를 건설하는 함수
	 */
	private static void build_bridge() {
		// 섬들을 하나씩 골라서, 섬을 이루는 땅에 대해 다리를 놓을 수 있는지 체크한다
		for (int v = 1; v <= V-1; v++) {
			List<int[]> piece = island.get(v);  // 하나의 섬
			// 하나의 섬을 이루는 모든 땅에 대해서, 한 방향으로 쭉 나아가 다른 섬이 있으면 다리를 놓을 수 있다고 판단한다
			for (int l = 0; l < piece.size(); l++) {
				int[] pos = piece.get(l);
				
				for (int d = 0; d < 4; d++) {
					int len = 0;
					
					int nx = pos[0];
					int ny = pos[1];
					while (true) { 
						nx += dx[d];
						ny += dy[d];
						
						if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
						if (marker[nx][ny] == v) break;  // 같은 섬의 땅이면 안됌
						
						// 다른 섬의 땅인 경우
						if (marker[nx][ny] != v && marker[nx][ny] > 0) {
							if (len <= 1) break;  // 다리 길이가 1이하이면 안됌
							edges.add(new Edge(v, marker[nx][ny], len));
							break;
						}
						
						len++;
					}
					
				}
			}
		}
	}
	
	private static int findSet(int a) {
		if (parents[a] < 0) return a;
		else return parents[a] = findSet(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return false;
		
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		
		return true;
	}
}