import java.util.Scanner;

public class Main {
	static int N, M;

	public static int findLen(int dir, int distance) {
		// 각 지점의 거리는 왼쪽 상단을 (0,0)의 원점을 기준으로 했을 때를 상정한다

		// 북쪽은 distance와 같다
		if (dir == 1)
			return distance;
		// 남쪽은 '가로 + 세로 + 블록의 왼쪽 경계로부터 거리'
		else if (dir == 2)
			return N + M + (N - distance);
		// 서쪽은 '가로*2 + 블록의 위쪽 경계로부터 거리'
		else if (dir == 3)
			return 2 * N + 2 * M - distance;
		// 동쪽은 '가로 + 길이'
		else if (dir == 4)
			return N + distance;

		return 0;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt(); // 가로의 길이
		M = sc.nextInt(); // 세로의 길이

		int n_market = sc.nextInt();

		// 상점들의 위치
		int[] p_markets = new int[n_market + 1];
		int ans = 0; // 최단 길이

		for (int i = 0; i < n_market + 1; i++) {
			int dir = sc.nextInt(); // 위치
			int distance = sc.nextInt(); // 떨어진 거리

			p_markets[i] = findLen(dir, distance);
		}

		/**
		 * 각 지점과 경비원 사이의 거리를 구한다. 
		 * 이때, p_markets에 저장된 거리는 시계방향으로 탐색했을 때 거리이므로 시계반대방향으로
		 * 탐색했을 때의 거리와 최소 값을 비교해야 한다 
		 * 시계반대 방향의 거리는 블록의 전체 길이에서 시계방향 거리(=p_markets)를 빼면 된다
		 */
		for (int f = 0; f < n_market; f++) { // 시계 방향 거리
			// p_markets[n]=경비원의 거리
			int gap_with_forward = Math.abs(p_markets[f] - p_markets[n_market]);
			int gap_with_backward = (2 * N + 2 * M) - gap_with_forward;

			ans += Math.min(gap_with_forward, gap_with_backward);
		}

		System.out.println(ans);

	}
}
