import java.util.Arrays;
import java.util.Scanner;

/**
 * 메모리:21,764 kb, 실행시간: 214ms
 * @author SSAFY
 *
 */
public class Solution {

	static int N, B, ans;   // N:점원 수, B:선반 높이, ans:정답
	static int[] heights;  // 점원들의 키 배열
	static boolean[] isSeleceted;

	/**
	 * 부분 조합을 생성
	 * @param cnt 탐색한 원소의 개수
	 */
	static void set(int cnt) {
		// 모든 원소를 탐색했다면, 선택 배열을 참조하여 점원들의 키의 합을 구함
		if (cnt == N) {
			int sum = 0;
			for (int i = 0; i < isSeleceted.length; i++) {
				if (isSeleceted[i]) {
					sum += heights[i];
				}
			}
			
			// 합이 선반 높이보다 같거나 크다면 ans를 업데이트
			if (sum >= B)
				ans = Math.min(ans, sum - B);
			return;
		}
		
		isSeleceted[cnt] = true;
		set(cnt + 1);
		
		isSeleceted[cnt] = false;
		set(cnt + 1);
		
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int test_case = sc.nextInt();
		for (int tc = 1; tc <= test_case; tc++) {
			ans = 10000;
			
			N = sc.nextInt();
			B = sc.nextInt();

			isSeleceted = new boolean[N];
			heights = new int[N];
			for (int i = 0; i < N; i++) {
				heights[i] = sc.nextInt();
			}
			
			set(0);
			
			System.out.println("#"+tc+ " "+ans);
		}

	}

}