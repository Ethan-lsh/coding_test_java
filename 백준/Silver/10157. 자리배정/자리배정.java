import java.util.*;
import java.io.*;

public class Main {

	// 상 -> 우 -> 하 -> 좌
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int C = sc.nextInt();
		int R = sc.nextInt();
		int K = sc.nextInt();
		
		if (K > C * R) {
			System.out.println(0);
			System.exit(0);
		}

		boolean[][] visited = new boolean[R][C];

		int x = R - 1;
		int y = 0;

		// 회전 방향
		int dir = 0;

		int cnt = 0;
		while (++cnt != K) {
			visited[x][y] = true;

			// 다음 좌표
			int nx = x + dx[dir];
			int ny = y + dy[dir];

			// 다음 좌표가 격자 범위를 벗어나거나, 이미 방문한 곳이라면
			// 방향을 즉시 바꾸고, 변경한 방향에 맞춰 다음 좌표를 업데이트
			if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny]) {
				dir = ++dir % 4;
				nx = x + dx[dir];
				ny = y + dy[dir];
			}
			
			x = nx;
			y = ny;
			
		}

		// 범위를 행, 열 한칸씩 줄여서 시작했기 때문에
		// y = y + 1
		// x = R - x (행은 역순)
		System.out.println((y + 1) + " " + (R - x));

	}

}
