import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// 듣도 못한 사람의 집합
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		
		// 보도 못한 사람을 순회 하면서, 곂치는게 있으면 저장
		ArrayList<String> result = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			if (set.contains(str)) {
				result.add(str);
			}
		}
		
		System.out.println(result.size());
		Collections.sort(result);
		for (String s : result) {
			System.out.println(s);
		}
		
	}

}