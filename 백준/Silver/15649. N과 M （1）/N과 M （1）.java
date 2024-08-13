import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int N, M; // 자연수 N과 M
	static int[] arr; // 수열
	static boolean[] select; // 선택된 자연수를 체크

	static void permutations(int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			for (int n : arr) {
				sb.append(n + " ");
			}
			
			System.out.println(sb.toString());

			return;
		}

		for (int num = 1; num <= N; num++) {

			if (select[num])
				continue; // 이미 선택된 값이면 패스

			arr[depth] = num;       // depth 만큼의 인덱스에 값을 저장

			select[num] = true;     // 선택

			permutations(depth + 1);  // 재귀 함수 호출

			select[num] = false;      // 선택된 값 해제

		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		select = new boolean[N + 1];

		// 수열 생성
		arr = new int[M];
		permutations(0);

	}
}
