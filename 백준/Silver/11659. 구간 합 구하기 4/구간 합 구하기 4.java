import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int[] sum;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N+1];
		sum = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
			sum[i] += sum[i-1] + arr[i];
		}
		
//		System.out.println(Arrays.toString(sum));
		
		for (int m = 0; m < M; m++) {
			
			st = new StringTokenizer(br.readLine());
			
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			int result = sum[j] - sum[i-1];
			
			sb.append(result + "\n");
		}
		
		System.out.println(sb.toString());
		
	}

}