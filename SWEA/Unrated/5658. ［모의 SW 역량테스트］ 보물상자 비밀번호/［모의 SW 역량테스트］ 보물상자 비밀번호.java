import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 메모리:28,100kb, 실행시간:207ms
 * @author Sanghyeon Lee
 */
public class Solution {

	static int N, K, len;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			len = N / 4;  // 각 암호의 길이 구하기
					
			char[] ch = br.readLine().toCharArray();
			
			// 암호를 저장하기 위한 TreeSet(내림차순 정렬을 위해 사용)
			TreeSet<Integer> set = new TreeSet<>((o1, o2) -> Integer.compare(o2, o1));
			
			// 회전이 처음 상태로 돌아올 때 까지
			for (int l = 0; l < len; l++) {
				// 문자열의 회전 (오른쪽 쉬프트연산)
				char temp = ch[N-1];  // 임시 문자 저장 배열
				System.arraycopy(ch, 0, ch, 1, N-1);
				ch[0] = temp;
				
				// 각 암호의 길이 만큼의 16진수 숫자를 구하여 set에 저장
				for (int i = 0; i < N; i+=len) {
					String hex = "";
					for (int j = 0; j < len; j++) {
						hex += String.valueOf(ch[i+j]);
					}
					set.add(Integer.parseInt(hex, 16));
				}
			}
			
			// set의 Iterator 접근을 피하고 인덱스로 접근하기 위해 List 생성
			List<Integer> result = new ArrayList<>(set);
			System.out.println("#" + tc + " " + result.get(K-1));
			
		}
	}

}