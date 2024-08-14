import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] arr, arr2;
	static boolean[] isSelected;
	static int ans;
	
	static void permutation(int depth) {
		if (depth == 3) {
			int sum = Arrays.stream(arr2).sum();
			
			if (sum <= M) {
				ans = Math.max(ans, sum);
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue;
			
			arr2[depth] = arr[i];
			
			isSelected[i] = true;
			
			permutation(depth+1);
			
			isSelected[i] = false;
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isSelected = new boolean[N];

		arr = new int[N];
		arr2 = new int[3];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		permutation(0);
		
		System.out.println(ans);
		
	}

}