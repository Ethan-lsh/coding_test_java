import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리:28,272kb, 실행시간: 210ms
 */
public class Solution {

	static int size, start;
	static int[] arr;
	static int[][] board;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_case = 10;
		for (int tc = 1; tc <= test_case; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			size = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			board = new int[101][101]; // 인접행렬 초기화
			visited = new boolean[101]; // 방문행렬 초기화

			// 숫자 데이터 입력
			arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			// 인접행렬 초기화
			for (int i = 0; i < arr.length; i += 2) {
				int from = arr[i];
				int to = arr[i + 1];

				board[from][to] = 1;
			}

			bfs(start);

			System.out.println("#" + tc + " " + ans);

		}
	}


	private static void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();  // 큐 생성

		// 시작점에 대한 초기화 작업
		q.add(s); 
		visited[s] = true;

		while (!q.isEmpty()) { // while -> 계속 연락이 진행 중
			int q_size = q.size();

			ans = 0;

			for (int i = 0; i < q_size; i++) {  // 큐의 사이즈 만큼 순회
				int node = q.poll();   // 현재 선택된 노드

				// node의 인접 노드
				int[] adjNodes = board[node];

				// 인접 노드를 순회하며 방문하지 않은 노드를 체크
				for (int j = 1; j <= 100; j++) {
					if (adjNodes[j] == 1 & !visited[j]) {
						q.add(j);
						visited[j] = true; // 다음 방문하는 곳은 현재 노드 시간 +1
					}
				}

				ans = Math.max(ans, node);  // 현재 선택된 노드가 정답보다 크다면 수정
			}

		}

	}

}