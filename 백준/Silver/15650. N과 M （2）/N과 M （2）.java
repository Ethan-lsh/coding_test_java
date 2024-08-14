import java.util.Scanner;

public class Main {

	static int N, M;
	static int[] arr;
	static boolean[] isSelected;

	private static void permutations(int index, int depth) {
		if (depth == M) {
			StringBuilder sb = new StringBuilder();
			for (int a : arr) {
				sb.append(a + " ");
			}
			System.out.println(sb.toString());
			return;
		}
		
		for (int i = index; i <= N; i++) {
			if (isSelected[i]) continue;  // 이미 선택된 값이면 패스
			
			arr[depth] = i;             // 배열에 값을 저장
			
			isSelected[i] = true;       // 선택 표시
			
			permutations(i+1, depth+1);  // 재귀 함수 실행 (현재 index=i+1)
			 
			isSelected[i] = false;       // 선택 표시 해제
			
		}
		
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		arr = new int[M];
		isSelected = new boolean[N+1];

		permutations(1, 0);

	}

}