import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int sum; // 아홉 난장이 숫자의 합
	static int[] arr; // 아홉 난장이의 값을 담는 배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		arr = new int[9];

		sum = 0;
		for (int i = 0; i < 9; i++) {
			sum += arr[i] = Integer.parseInt(br.readLine());
		}

		/**
		 * 'i, j=i+1'을 시작범위로 배열을 순회. i, j 난쟁이의 숫자를 총합에서 뺐을 때, 100이 된다면 i와j는 일곱난쟁이가 아니다.
		 */
		loop: for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (arr[i] + arr[j] == sum - 100) {
					// 값을 0으로 변경
					arr[i] = arr[j] = 0;
                    break loop;
				}
			}
		}

		for (int i = 0; i < 9; i++)
			if (arr[i] > 0)
				sb.append(arr[i]).append('\n');

		System.out.print(sb);

	}
}