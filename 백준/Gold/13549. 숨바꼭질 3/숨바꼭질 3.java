import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int ans = Integer.MAX_VALUE;
	static final int max = 100000;
	
	static class Node {
		int x, time;

		public Node(int x, int time) {
			super();
			this.x = x;
			this.time = time;
		}
		
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		boolean[] visited = new boolean[max+1];
				
		// BFS 탐색
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(N, 0));
		
		while (!q.isEmpty()) {
			
			Node n = q.poll();
			
			int x = n.x;
			int time = n.time;
			visited[x] = true;
			
			if (x == K) {
				// !!중요
				// K 지점에 도달하는 것은 걷기 or 순간이동 모두 가능하기 때문에
				// 모든 경우의 수 중, 가장 적은 시간을 소요한 쪽을 업데이트
				ans = Math.min(ans, time);
			}
			
			if (x * 2 <= max && !visited[x*2]) q.offer(new Node(x*2, time));
			if (x + 1 <= max && !visited[x+1]) q.offer(new Node(x+1, time+1));
			if (x - 1 >= 0 && !visited[x-1]) q.offer(new Node(x-1, time+1));
		}
		
		System.out.println(ans);
		
	}

}