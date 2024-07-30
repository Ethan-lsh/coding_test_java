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
		
		// 가장 작은 창고의 넓이 -> 높이가 낮은 지붕이 많아야 한다
		// 오목한 부분이 없어야 한다 -> 증가하면 계속 증가, 감소하면 계속 감소 해야 함
		int max_height = Arrays.stream(arr).max().getAsInt();
		
		
		int left_most_index = 1000, right_most_index = 0;
		for (int i = start; i <= end; i++) {
			if (arr[i] == max_height) {
				left_most_index = Math.min(left_most_index, i);
				right_most_index = Math.max(right_most_index, i);
			}
		}
		
		int ans = max_height * (right_most_index - left_most_index + 1);
		
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
