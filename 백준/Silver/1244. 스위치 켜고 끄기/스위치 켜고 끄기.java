import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());

			int gender = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			// 남학생
			// num 의 배수만큼 인자를 증가시켜서 탐색
			if (gender == 1) {
				for (int m = num; m <= N; m += num) {

					if (arr[m] == 0) {
						arr[m] = 1;
					}
					else {
						arr[m] = 0;
					}
				}
			}

			// 여학생
			if (gender == 2) {
				int left = num - 1;
				int right = num + 1;
				// 왼쪽, 오른쪽의 범위 한계를 구한다
				while (left > 0 && right <= N) {
					if (arr[left] != arr[right]) {
						break;
					}
					left--;
					right++;
				}
				// 왼쪽과 오른쪽의 끝점의 범위를 순회하면서 값들을 반전시킴
				for (int k = left + 1; k < right; k++) {
					if (arr[k] == 1) {
						arr[k] = 0;
					} else {
						arr[k] = 1;
					}
				}

			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(arr[i]).append(" ");
			if ((i % 20) == 0)
				sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
