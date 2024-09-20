import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static int N, K;
	static int[] visited = new int[100001];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		K = sc.nextInt();

		bfs(N);

	}

	private static void bfs(int pos) {
		Queue<Integer> q = new ArrayDeque<>();

		q.add(pos);
		visited[pos] = 1;

		while (!q.isEmpty()) {
			int x = q.poll();

			if (x == K) {
				System.out.println(visited[x] - 1);
				return;
			}

			if (x - 1 >= 0 && visited[x - 1] == 0 ) {
				visited[x - 1] = visited[x]+1;
				q.add(x - 1);
			}

			if (x + 1 <= 100000 && visited[x + 1] == 0) {
				visited[x + 1] = visited[x] + 1;
				q.add(x + 1);
			}

			if (x*2 <= 100000 && visited[x*2] == 0) {
				visited[x*2] = visited[x] + 1;
				q.add(x*2);
			}

		}

	}

}