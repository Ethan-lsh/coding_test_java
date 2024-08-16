import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int P, S;
	static String str;       // 문자열 
	static int[] checkChar;  // 부분 문자열에서 'A', 'C', 'G', 'T'의 개수
	
	static final int A = 0, B = 1, C = 2, D = 3;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		P = Integer.parseInt(st.nextToken()); // 부분문자열 길이
		
		// DNA 문자열
		str = br.readLine();

		checkChar = new int[4];  // 최소 만족 조건
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			checkChar[i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		
		
		// 첫 P부분 문자열에 대한 조건 탐색
		// 'ACGT' 중 만족하는 문자가 있으면 최소 문자 개수를 감소시킨다
		for (int i = 0; i < P; i++) {
			char ch = str.charAt(i);
			if (ch == 'A') checkChar[0]--;
			else if (ch == 'C') checkChar[1]--;
			else if (ch == 'G') checkChar[2]--;
			else if (ch == 'T') checkChar[3]--;
		}

		if (checkAvailable()) ans++;  // 가장 처음 주어진 부분 문자열의 가능 여부 체크
		
		/**
		 * 슬라이딩 윈도우
		 * P부분 문자열의 가장 앞에 있는 요소(front)는 부분 문자열에서 제외하고
		 * P부분 문자열의 다음에 있는 문자(back)를 부분 문자열에 추가한다.
		 * 
		 * !중요
		 * 이때,front는 최소 개수에 포함되지 않기 때문에 checkChar++
		 * back은 새로 부분 문자열에 추가되었기 때문에 'ACGT'중 하나라도 해당되면 최소 조건을 감소시킨다.
		 */
		for (int i = 0; i < S-P; i++) {
			
			char front = str.charAt(i);
			if (front == 'A') checkChar[0]++;
			else if (front == 'C') checkChar[1]++;
			else if (front == 'G') checkChar[2]++;
			else if (front == 'T') checkChar[3]++;
			
			char back = str.charAt(i+P);
			if (back == 'A') checkChar[0]--;
			else if (back == 'C') checkChar[1]--;
			else if (back == 'G') checkChar[2]--;
			else if (back == 'T') checkChar[3]--;
			
			// 한번더 적합성 검사
			if (checkAvailable()) ans++;
		}
		
		System.out.println(ans);
	}

	private static boolean checkAvailable() {
		// 만약 남아있는 최소 문자 개수가 0 보다 크다면 조건을 만족하지 않았기 때문에 false를 return
		for (int i = 0; i < 4; i++) {
			if (checkChar[i] > 0)
				return false;
		}
		return true;
	}

	
}