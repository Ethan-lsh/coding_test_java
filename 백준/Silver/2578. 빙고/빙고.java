import java.util.*;
import java.io.*;

/**
 * 빙고게임 구현 문제
 * 
 * <초기 아이디어> 시간 복잡도를 고려하여, (행, 열, 대각선)에 선언되지 않고 남아있는 숫자의 갯수를 저장한 배열을 사용하여 문제를 접근
 * -> 구현이 복잡하고 시간 면에서 이득이 없었음
 * 
 * <기본 풀이법> 각 줄을 탐색하는 함수를 선언하고,숫자가 호출 될 때마다 실행
 *
 */
public class Main {
	static int[][] bingo;
	static int cnt;

	// 행 체크
	public static void rCheck() {
		for (int i = 0; i < 5; i++) {
			int zeroCount = 0;
			for (int j = 0; j < 5; j++) {
				if (bingo[i][j] == 0)
					zeroCount++;
			}
			if (zeroCount == 5)
				cnt++;
		}
	}

	// 열 체크
	public static void cCheck() {
		for (int i = 0; i < 5; i++) {
			int zeroCount = 0;
			for (int j = 0; j < 5; j++) {
				if (bingo[j][i] == 0)
					zeroCount++;
			}
			if (zeroCount == 5)
				cnt++;
		}
	}

	// 오른쪽 -> 왼쪽 아래 대각선
	public static void rlCheck() {
		int zeroCount = 0;
		for (int i = 0; i < 5; i++) {
			if (bingo[i][4 - i] == 0)
				zeroCount++;
		}
		if (zeroCount == 5)
			cnt++;
	}

	// 왼쪽 -> 오른쪽 아래 대각선
	public static void lrCheck() {
		int zeroCount = 0;
		for (int i = 0; i < 5; i++) {
			if (bingo[i][i] == 0)
				zeroCount++;
		}
		if (zeroCount == 5)
			cnt++;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		bingo = new int[5][5];
		cnt = 0;

		// 빙고판 입력
		for (int i = 0; i < 5; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < 5; j++) {
				bingo[i][j] = Integer.parseInt(line[j]);
			}
		} // end for

		// 사회자가 숫자를 부름
		int turn = 1;
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int num = Integer.parseInt(st.nextToken());
				// 사회자가 부른 수와 같다면 0으로 변경

				outer: for (int k = 0; k < 5; k++) {
					for (int l = 0; l < 5; l++) {
						if (bingo[k][l] == num) {
							bingo[k][l] = 0;
							break outer;
						}
					}
				}

				rCheck();
				cCheck();
				lrCheck();
				rlCheck();

				if (cnt >= 3) {
					System.out.println(turn);
					System.exit(0);
				}

				cnt = 0;
				turn++;
			}

		}

	}

}
