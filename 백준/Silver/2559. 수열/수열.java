import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int cnt = 0;
		for (String s : br.readLine().split(" ")) {
			arr[cnt++] = Integer.parseInt(s);
		}
		
		
		int sum = Arrays.stream(arr).limit(K).sum();
		
		int ans = sum;
		int comp_sum = 0;
		
		for (int i = 1; i <= N-K; i++) {
			int begin = arr[i - 1];
			int end = arr[i + K - 1];
			
			comp_sum = sum - begin + end;
			
			ans = Math.max(comp_sum, ans);
			
			sum = comp_sum;
		}
		System.out.println(ans);
	}

}
