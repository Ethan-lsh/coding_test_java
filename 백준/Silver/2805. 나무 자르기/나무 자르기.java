import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] trees;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		trees = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			trees[i] = Integer.parseInt(st.nextToken());
		
		
		int min = 0, max = Arrays.stream(trees).max().getAsInt();
		while (min < max) {
			
			int mid = (max + min) / 2;
			
			long sum = 0;
			
			for (int height : trees) {
				if (height - mid > 0) {
					sum += (height - mid);
				}
			}
	
			/**
			 * 합이 기준치 보다 작은 경우
			 * 최대 값을 mid 만큼 끌어내려서, 새로 만드는 mid 값을 더 낮춰야 한다.
			 * mid 값이 낮아질 수록 얻을 수 있는 통나무의 길이가 늘어나기 때문.
			 */
			if (sum < M) {
				max = mid;
			}
			
			/**
			 * 합이 기준치 보다 큰 경우
			 * 최소값을 중간값+1 만큼 올려야 한다.
			 * 최소값을 1씩 증가시키는 방법도 있지만, 
			 * 현재 중간값으로 나무를 더 많이 벴다는 것은, mid 값은 무조건 현재 mid 값 보다 커야 한다는 것을 의미
			 * 최소값을 1씩 증가시키면, 더 많은 탐색 시간이 걸린다. 
			 * 현재 중간값 
			 */
			else {
				min = mid + 1;
			}
		}
		
		System.out.println(min-1);

	}
	

}