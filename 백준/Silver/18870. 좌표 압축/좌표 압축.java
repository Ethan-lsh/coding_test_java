import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		
		int[] origin = new int[N];  // 원본 배열
		int[] sorted = new int[N];  // 정렬된 배열
		HashMap<Integer, Integer> rankMap = new HashMap<>();  // 정렬된 배열값의 순위를 저장하는 Map
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			origin[i] = sorted[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sorted); // 정렬된 배열 정렬
		
		/**
		 * HashMap은 정렬된 배열의 키 값에 순위를 저장하는 역할
		 * 주어진 숫자들의 순위를 출력하는 것이 문제의 핵심
		 * 숫자가 작을 수록 더 높은 랭크 값을 가진다 (예. -10->0, -9->1)
		 */
		int rank = 0;
		for (int num : sorted) {
			if (!rankMap.containsKey(num)) { // 중복을 피한다
				rankMap.put(num, rank);
				rank++;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int o : origin) {
			int ranking = rankMap.get(o);
			sb.append(ranking).append(' ');
		}
			
		System.out.println(sb);
			
	}

}