import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * DP문제
 * @author Sanghyeon Lee
 *
 */
public class Main {

	static int N;
	static int[] arr;  // 입력 수열
	static int[] Lis;  // 증가하는 부분 수열의 길이를 저장하는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Lis = new int[N];
		// 수열의 0~N-1 까지 임시 최장 길이 값 1을 설정
		// 선택된 i번 숫자의 이전에 있는 수열들 중 자신보다 값이 작다면 
		// Lis[i]는 Lis[i-1] 즉 이전 최장 길이 값에 +1을 한것과 같다
		// => 현재 선택한 숫자를 기점으로 뒤를 보면서 길이를 구하는 방식
		for (int i = 0; i < N; i++) {
			Lis[i] = 1;
			for (int j = 0; j <= i - 1; j++) {
				if (arr[j] < arr[i] & Lis[i] < Lis[j] + 1) {
					Lis[i] = Lis[j] + 1;
				}
			}
		}
		
		System.out.println(Arrays.stream(Lis).max().getAsInt());
		
	}

}