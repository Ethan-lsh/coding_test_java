import java.util.*;
import java.io.*;

/**
 * 두 개의 직사각형의 좌하단, 우상단의 좌표가 주어졌을 때 서로 곂치는 영역의 모양을 확인하는 문제
 * 수학적 이해를 요구하는 문제이다.
 * 각 경우의 수를 성립하게 하는 조건을 모두 고려해야 한다. 편법은 없고 조건을 잘 생각해야 하는 꽤 까다로운 문제
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] box_a = new int[4];
		int[] box_b = new int[4];
		
		for (int t = 0; t < 4; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// x, y, p, q, x', y', p', q'
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int p1 = Integer.parseInt(st.nextToken());
			int q1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			int q2 = Integer.parseInt(st.nextToken());
			
			
			if (p1 < x2 || p2 < x1 || q1 < y2 || q2 < y1) {
				System.out.println('d');
			} else if ((p1 == x2 && q1 == y2) || (p1==x2 && y1 == q2) || 
					(x1 == p2 && q1 == y2) || (x1 == p2 && y1 == q2)) {
				System.out.println('c');
			} else if ((p1 == x2 || x1 == p2) && (y1 <= q2 && q1 >= y2)) {
				System.out.println('b');
			} else if ((q1 == y2 || y1 == q2) && (x1 <= p2 && p1 >= x2)) {
				System.out.println('b');
			} else {
				System.out.println('a');
			} // end if
			
		} // end for
	}

}
