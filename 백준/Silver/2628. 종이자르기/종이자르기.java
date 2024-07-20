import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int C = Integer.parseInt(st.nextToken());  // 가로 길이 (=column)
		int R = Integer.parseInt(st.nextToken());  // 세로 길이 (=width)
		
		// 행과 열이 각각 잘린 점을 표시하기 위한 1차원 배열
		int[] cutR = new int[R + 1];
		int[] cutC = new int[C + 1];
		
		// 행과 열이 잘린 점을 저장
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int mode = Integer.parseInt(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
			
			if (mode == 0) cutR[point] = 1;
			else cutC[point] = 1;
		}
		
		// 잘린 점을 기준으로 가로와 세로의 최대 길이를 구함
		int temp = 0;
		int max_width = 0;
		for (int i = 1; i <= R; i++) {
			temp++;
			if (cutR[i] == 1 || i == R) {
				max_width = Math.max(max_width, temp);
				temp = 0;
			}
		}
		
		temp = 0;
		int max_height = 0;
		for (int j = 1; j <= C; j++) {
			temp++;
			if (cutC[j] == 1 || j == C) {
				max_height = Math.max(max_height, temp);
				temp = 0;
			}
		}
		
		System.out.println(max_width * max_height);
	}

}
