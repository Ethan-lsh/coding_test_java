import java.util.Scanner;

public class Solution {

	static int N;          // 산의 범위
	static int[] mountain; // 산 높이
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int test_case = sc.nextInt();
		for (int tc = 1; tc <= test_case; tc++) {
			ans = 0;
			
			N = sc.nextInt();
			
			mountain = new int[N];

			// 산 정보 입력
			for (int i = 0; i < N; i++)
				mountain[i] = sc.nextInt();
			
			// 처음 산 부터 탐색
			for (int i = 1; i <= N-2; i++) {
				if (mountain[i-1] < mountain[i] && mountain[i] > mountain[i+1]) {  // 꼭대기를 찾았다면
					// 꼭대기 왼쪽을 탐색
					int leftCnt = 1;
					for (int j=i-2; j >=0; j--) {
						// 조건을 만족하지 못하는 구간이 나오면 break
						if (mountain[j] > mountain[j+1]) break;
						
						leftCnt++;
					}
					
					// 꼭대기 오른쪽을 탐색
					int rightCnt = 1;
					for (int j=i+2; j < N; j++) {
						// j < j+1 로 비교하면 범위를 벗어나기 때문에 이전 값에 대해 비교를 수행
						if (mountain[j-1] < mountain[j]) break;
						
						rightCnt++;
					}
					
					
					// i 지점을 기준으로 높이 솟아오른 산을 만들기 위한 경우의 수는
					// 왼쪽 = leftCnt, 오른쪽=rightCnt
					// 위 두 값은, 양옆에 놓일 수 있는 경우의 수와 같기 때문에 서로 곱하면 전체 경우의 수와 같아진다
					ans +=(leftCnt*rightCnt);
				}
				
			}
			
			System.out.println("#"+tc+" "+ans);
			
		}

	}

}
