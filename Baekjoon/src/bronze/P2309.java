package bronze;

import java.util.*;
import java.io.*;

public class P2309 {

	public static int fake1;
	public static int fake2;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		// 아홉 난장이
		int[] small = new int[9];
		
		for (int i = 0; i < small.length; i++) {
			small[i] = sc.nextInt();
		}
		
		int sum = 0;
		for (int s : small) {
			sum+=s;
		}
		
		for (int i = 0; i < small.length; i++) {
			for (int j = i + 1; j < small.length; j++) {
				if(sum - small[i] - small[j] == 100) {
					fake1 = small[i];
					fake2 = small[j];
					break;
				}
			}
		}
		
		Arrays.sort(small);
		for (int i = 0; i < small.length; i++) {
			if (small[i] == fake1 || small[i] == fake2) continue;
			System.out.println(small[i]);
		}
		
	}

}
