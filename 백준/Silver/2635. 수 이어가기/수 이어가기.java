import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
	static int MAX_SIZE = 30000;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt(); // 주어진 양의 정수 (N-2)

		int ans = 0;

		List<Integer> arr = new ArrayList<>();
		
		for (int i = N; i > 0; i--) {
			// while 문 종료 후, first_num; second_num 초기화
			int first_num = N;  
			int second_num = i; 

			int cnt = 2; // 현재까지 구한 수의 개수

			List<Integer> temp = new ArrayList<>(); // 반환할 배열을 임시 저장
			temp.add(first_num);
			temp.add(second_num);

			while (true) {
				int third_num = first_num - second_num;

				if (third_num < 0)
					break; // 음의 정수가 생기면 아웃

				temp.add(third_num);
				cnt++;

				// 숫자 교체
				first_num = second_num;
				second_num = third_num;
			}

			if (ans <= cnt) {
				ans = cnt;
				arr = temp.stream().collect(Collectors.toList());
			} else
				break;
		}
		
		System.out.println(ans);
		for (int n : arr) {
			System.out.printf("%d ", n);
		}
	}
}
