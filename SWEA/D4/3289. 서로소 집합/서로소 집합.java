import java.util.Scanner;

public class Solution {

	static int n, m;
	static int[] parents;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int test_case = sc.nextInt();
		for (int tc=1; tc <= test_case; tc++) {
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(tc).append(" ");
			
			
			n = sc.nextInt();
			m = sc.nextInt();
			
			// 서로소 집합 초기화
			parents = new int[n+1];
			for (int i = 1; i <= n; i++)
				parents[i] = i;
			
			/**
			 * 집합 연산을 처리
			 * 1.합집합: (0, a, b)
			 * 2.교집합: (1, a, b)
			 */
			for (int i = 0; i < m; i++) {
				int cmd = sc.nextInt();  // 연산 번호
				
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				if (cmd == 0) union(a, b);
				else {
					if (findParent(a) == findParent(b)) sb.append(1);
					else sb.append(0);
				}
				
			}
			
			System.out.println(sb.toString());
			
		}
		
	}
	
	private static int findParent(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findParent(parents[a]);
		
	}
	
	/**
	 * 집합 b를 집합 a에 포함시키는 함수
	 * @param a a집합
	 * @param b b집합
	 */
	private static void union(int a, int b) {
		int aParent = findParent(a);
		int bParent = findParent(b);
		
		parents[bParent] = aParent;  // b가 a집합에 포함되게 설정
	}
	

}