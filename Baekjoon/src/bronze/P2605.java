package bronze;

import java.util.*;
import java.util.stream.IntStream;
import java.io.*;

public class P2605 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 학생들이 뽑은 번호
		int[] vote = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			vote[i] = Integer.parseInt(st.nextToken());
		}

		// 줄을 선 학생들
		int[] original_line = {1};
		
		for (int i = 1; i < N; i++) {
			int pos = original_line.length - vote[i]; // 삽입할 위치
			
			int[] new_line = new int[original_line.length + 1];
			
			for (int m = 0; m < pos; m++) {
				new_line[m] = original_line[m];
			}
			
			new_line[pos] = i + 1;
			
			for (int n = pos; n < original_line.length; n++) {
				new_line[n + 1] = original_line[n];
			}
			
			original_line = new_line;
		}

		for (int i : original_line) {
			System.out.printf("%d ", i);
		}
//		System.out.println(Arrays.toString(original_line));
		
	}

}
