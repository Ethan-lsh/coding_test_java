import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	static int N, M;
	static List<Integer> perm = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		permutation(1, 0);
		
		System.out.println(sb);
	}
	
	private static void permutation(int index, int cnt) {
		if (cnt == M) {
			for (int p : perm) {
				sb.append(p + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 맨 앞자리 숫자가 바뀔 때, 바로 뒤에 오는 숫자는 맨 앞의 숫자부터 시작해야 한다
		// -> index 값을 증가
		for (int i = index; i <= N; i++) {
			perm.add(i);
			permutation(i, cnt+1);
			perm.remove(perm.size()-1);
		}
		
		return;
	}

}