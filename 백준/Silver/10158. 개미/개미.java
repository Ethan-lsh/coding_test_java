import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int p = Integer.parseInt(st.nextToken());  // x 좌표 (width)
		int q = Integer.parseInt(st.nextToken());  // y 좌표 (height)
		
		int t = Integer.parseInt(br.readLine());
		
		/**
		 * (dx, dy) 를 사용하면 시간 초과가 나는 문제.
		 * 개미의 움직임의 주기를 파악하여 수학적으로 접근해야 풀 수 있다.
		 * 
		 * x 좌표 -> 개미의 x 좌표는 다시 똑같은 x 좌표로 복귀하기 까지 'w * 2' 의 주기가 걸린다
		 * y 좌표 -> 개미의 y 좌표는 다시 똑같은 y 좌표로 복귀하기 까지 'h * 2' 의 주기가 걸린다.
		 * 
		 * 따라서, t 만큼을 좌표에 더하고 주기로 나누면 t 시간 후의 위치를 알 수 있다.
		 * 이때, '~ * 2' 모듈러 연산을 수행하기 때문에 'w < p <= w*2' or 'h < q <= q*2'의 초과 범위가 나오는데
		 * 2배 연산에 현재 x 좌표 만큼 빼면 된다. 
		 */
		int x = (p + t) % (w * 2);
		int y = (q + t) % (h * 2);
		
		if (x > w) {
			x = 2 * w - x;
		}
		
		if (y > h) {
			y = 2 * h - y;
		}
		
		System.out.printf("%d %d", x, y);
	}

}
