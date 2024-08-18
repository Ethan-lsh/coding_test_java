import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public class Solution {

	static Scanner sc = new Scanner(System.in);
	
	// W: 너비
	// D: 보호필름의 두께
	// W: 보호필름의 너비
	// K: 보호필름 합격기준
	static final int DSIZE = 13;
	static final int WSIZE = 20;
	static int D, W, K;
	
	// film: 2차원 배열로 저장
	// minChemicalCnt : 화확처리 최소 회수
	// chemical: 각 번호의 엷은 막이 어떤 화학처리를 했는지 기록 (0 -> A, 1 -> B, 2 -> 약품 처리 x)
	static int [][] film = new int[DSIZE][WSIZE];
	static int minChemicalCnt;
	static int[] chemical = new int[DSIZE];
	
	
	// solve: 재귀 함수
	// curD: d 번째 필름의 엷은 막
	// prevContinuum: 이전까지 세로방향으로 같은 특성이 연속되는 셀의 수를 저장
	// prevMaxContinuum : 이전까지 세로방향으로 같은 특성이 가장 많이 연속되는 셀의 개수를 저장
	static void solve(int curD, int chemicalCnt, int[] prevContinuum, int[] prevMaxContinuum) {
		
		// 종료조건 1
		// 현재 처리한 약품 횟수가 지금까지 찾은 횟수의 최소값보다 크면 볼 필요가 없음
		if (chemicalCnt >= minChemicalCnt) return;
		
		// 종료조건 2
		// 필름을 모두 다 보았을 때
		if (curD == D) {
			// 하나라도 합격기준에 도달하지 못하면 만족하지 않는다고 처리
			boolean isSatisfied = true;
			for (int i = 0; i < W; i++) 
			{
				if (prevMaxContinuum[i] < K) {
					isSatisfied = false;
					break;
				}
			}
			// 합격 기준에 만족하면서, 지금까지 찾은 화학처리 횟수의 최소값 보다 작으면 최소값을 갱신
			if (isSatisfied && chemicalCnt < minChemicalCnt)
				minChemicalCnt = chemicalCnt;
			return;
		}
		
		int[] continuum = new int[WSIZE];
		int[] maxCotinuum = new int[WSIZE];
		int prev, cur;
		
		// 약품처리를 하지 않았거나, B 약품을 처리하나, A 약품을 처리하는 것을 차례대로 진행
		for (int i = 2; i >= 0; i--) {
			// i번째 약품을 처리할 때 (0: A, 1: B, 2: 약품처리 x)
			chemical[curD] = i;
			
			// 모드 세로 방향을 탐색
			for (int j = 0; j < W; j++) {
				// cur: 현재 셀의 상태
				// prev: 바로 이전 셀의 상태
				cur = chemical[curD] == 2 ? film[curD][j] : chemical[curD];
				prev = chemical[curD - 1] == 2 ? film[curD - 1][j] : chemical[curD - 1];
				
				// cur과 prev가 같으면 이전 연속 셀 개수의 +1을 하고, 아니면 1을 설정
				continuum[j] = cur == prev ? prevContinuum[j] + 1 : 1;
				// 가장 많이 연속되는 셀의 개수를 갱신
				maxCotinuum[j] = continuum[j] > prevMaxContinuum[j] ? continuum[j] : prevMaxContinuum[j];
			}
			solve(curD + 1, chemicalCnt + (i == 2 ? 0 : 1), continuum, maxCotinuum);
			
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		int test_case = sc.nextInt();
		
		for (int tc = 1; tc <= test_case; tc++) {
			D = sc.nextInt(); W = sc.nextInt(); K = sc.nextInt();
			
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					film[i][j] = sc.nextInt();
				}
			}
			
			minChemicalCnt = K;
			
			// continuum : 지금 현재 필름까지 세로방향으로 같은 특성이 존재하는 셀의 수를 저장
			// maxContinuum : 지금 현재 필름까지 세로방향으로 같은 특성이 가장 많이 연속되는 셀의 개수를 저장
			
			int[] continuum = new int[WSIZE];
			int[] maxContinuum = new int[WSIZE];
			for (int i = 0; i < W; i++)
				continuum[i] = maxContinuum[i] = 1;
			
			// 0번째 엷은 필름 막에 화학약품 처리를 하지 않는다
			chemical[0] = 2;
			solve(1, 0, continuum, maxContinuum);
			
			// 0번째 엷은 필름 막에 약품 A처리를 한다
			chemical[0] = 0;
			solve(1, 1, continuum, maxContinuum);
			
			// 0번째 엷은 필름 막에 약품 B 처리를 한다
			chemical[0] = 1;
			solve(1, 1, continuum, maxContinuum);
			
			System.out.println("#"+tc+" "+minChemicalCnt);
			
		}
	}

}