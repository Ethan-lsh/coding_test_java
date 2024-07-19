import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for (int n = 0; n < N; n++) {
			int[] A_list = new int[5];
			int[] B_list = new int[5];

			String[] A = br.readLine().split(" ");

			int a_num = Integer.parseInt(A[0]);
			for (int i = 1; i <= a_num; i++) {
				int a = Integer.parseInt(A[i]);
				A_list[a]++;
			}

			String[] B = br.readLine().split(" ");

			int b_num = Integer.parseInt(B[0]);
			for (int i = 1; i <= b_num; i++) {
				int b = Integer.parseInt(B[i]);
				B_list[b]++;
			}


			// 도형 개수 비교
			for (int i = 4; i >= 1; i--) {
				if (A_list[i] > B_list[i]) {
					System.out.println("A");
					break;
				}
				if (A_list[i] < B_list[i]) {
					System.out.println("B");
					break;
				}
			}
			
			if (Arrays.equals(A_list, B_list)) {
				System.out.println("D");
			}
		}

	}

}
