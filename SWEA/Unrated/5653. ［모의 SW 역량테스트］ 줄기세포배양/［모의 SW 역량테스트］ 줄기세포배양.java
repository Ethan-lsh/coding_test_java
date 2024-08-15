import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;;

public class Solution {

	static int N, M, K, T;

	static final int DEAD = 0, ACTIVE = 1, INACTIVE = 2;

	static final int dy[]= {0,0,1,-1};
	static final int dx[]= {1,-1,0,0};
	
	static List<Cell> cell; // 줄기세포 List
	static PriorityQueue<Cell> pq; // 셀의 우선 순위를 저장하는 큐
	static boolean[][] visited; // 셀이 위치한 곳인지 확인하는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			cell = new ArrayList<>();
			pq = new PriorityQueue<>((p1, p2) -> p2.power - p1.power);
			visited = new boolean[N + 2 * K][M + 2 * K];
			
			for (int i = 0; i < N; i++) { // 줄기세포 초기 좌표 입력
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int num = Integer.parseInt(st.nextToken());

					if (num != 0) { // 0이 아닌 값 만을 관리
						cell.add(new Cell(i+K, j + K, num, num));
						visited[i + K][j + K] = true;
					}
				}
			}

			// K 시간의 움직임 구현
			for (int k=1; k<=K; k++) {

				// 이전 시간에 INACTIVE -> ACTIVE 변경
				while (!pq.isEmpty()) {
					Cell c = pq.poll();
					int x = c.x;
					int y = c.y;

					if (!visited[y][x]) {
						visited[y][x] = true;
						cell.add(c);
					}
				}

				for (int i = 0; i < cell.size(); i++) {
					if (cell.get(i).state == DEAD)
						continue;
					else if (cell.get(i).state == INACTIVE && cell.get(i).time == k) {
						cell.get(i).state = ACTIVE; // 활성화 상태로 변경
						cell.get(i).time = k + cell.get(i).power; // 현재 시간보다 X 만큼 시간이 지나면 죽도록 설정

						for (int d = 0; d < 4; d++) {
							// time+1 후에 상태 변경
							int nx = cell.get(i).x + dx[d];
							int ny = cell.get(i).y + dy[d];

							// 다음 시간에 체크 하도록 'time+1' 하여 추가
							pq.add(new Cell(ny, nx, k + 1 + cell.get(i).power, cell.get(i).power));
						}

					} else if (cell.get(i).state == ACTIVE && cell.get(i).time == k) { // 죽음
						cell.get(i).state = DEAD;
						cell.get(i).time = 0;
						cell.get(i).power = 0;
					}

				}
			}
			System.out.println("#" + t + " " + count());

		} // end test-case

	}

	static int count() {
		int cnt = 0;
		for (int i = 0; i < cell.size(); i++) {
			if (cell.get(i).state == ACTIVE || cell.get(i).state == INACTIVE)
				cnt++;
		}
		return cnt;
	}

	static class Cell {
		int x, y, time, state, power;

		Cell(int y, int x, int time, int power) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.power = power;
			this.state = INACTIVE;
		}
	} // end Cell class
}