import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 빌딩의 위치(index)와 높이 저장
		int[] arr = new int[1001];
		
		int start = 1000;
		int end = 0;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int h = Integer.parseInt(line[1]);
			arr[x] = h; // 높이 저장
			
			// 지붕의 범위 측정
			if (x <= start) {
				start = x;
			} 
			if (x >= end) {
				end = x;
			}
		}
		
		/**
		 * 최고 높이의 지붕을 기준으로 좌측 지붕은 항상 증가하고 우측편 지붕들은 모두 감소하는 형태가 나와야 한다.
		 * 단,이 과정에서 움푹 파이는 구조가 생기면 안된다.
		 * 1. 좌측편 지붕들은 이전 지붕과 높이를 비교했을 때,더 높으면 기준 높이를 증가시키고 아니면 유지한다.
		 * 2. 우측편 지붕들은 가장 끝에 있는 지붕부터 시작하여 가장 높은 지붕까지 자신보다 높이가 높으면 기준 높이를 증가시키고 아니면 유지한다.
		 * 이때, 같은 최고 높이를 가지는 지붕들이 존재할 수 있으며 해당하는 지붕들 사이에 있는 지붕들은 높이를 신경쓸 필요가 없다.
		 */
		int max_height = Arrays.stream(arr).max().getAsInt();
		
		// 최고 높이를 갖는 지붕 사이의 넓이를 구하기
		int left_most_index = 1000, right_most_index = 0;
		for (int i = start; i <= end; i++) {
			if (arr[i] == max_height) {
				left_most_index = Math.min(left_most_index, i);
				right_most_index = Math.max(right_most_index, i);
			}
		}
		int ans = max_height * (right_most_index - left_most_index + 1);
		
		// 좌측편 지붕듣
		int _prev = arr[start]; // 이전 높이
		for (int i = start; i <= end; i++) {
			int left_height = arr[i];
			
			// 최대 높이에 도달하면 탈출
			if (left_height == max_height) {
				break;
			}
			
			if (left_height > _prev) {
				_prev = left_height;
				ans += _prev;
			} else if (left_height <= _prev) {
				ans += _prev;
			}
			
		}
		
		// 우측편 지붕들
		_prev = arr[end];
		for (int i = end; i >= start; i--) {
			int right_height = arr[i];
			
			// 최대 높이에 도달하면 탈출
			if (right_height == max_height) {
				break;
			}
			
			if (right_height > _prev) {
				_prev = right_height;
				ans += _prev;
			} else if (right_height <= _prev) {
				ans += _prev;
			}
			
		}
		
		System.out.println(ans);
		
	}

}
