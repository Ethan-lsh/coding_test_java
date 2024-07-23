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
				while (left > 0 && right <= N) {
					if (arr[left] != arr[right]) {
						break;
					}
					left--;
					right++;
				}
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
