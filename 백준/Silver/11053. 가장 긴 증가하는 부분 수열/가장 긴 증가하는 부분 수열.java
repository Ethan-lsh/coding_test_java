import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	static int[] Lis;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Lis = new int[N];
		for (int i = 0; i < N; i++) {
			Lis[i] = 1;
			for (int j = 0; j <= i - 1; j++) {
				if (arr[j] < arr[i] & Lis[i] < Lis[j] + 1) {
					Lis[i] = Lis[j] + 1;
				}
			}
		}
		
		System.out.println(Arrays.stream(Lis).max().getAsInt());
		
	}

}