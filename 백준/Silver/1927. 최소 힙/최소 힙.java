import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// minHeap은 PriorityQueue로 구현 가능하다
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			int val = Integer.parseInt(br.readLine());
			
			if (val == 0) {
				if (minHeap.isEmpty()) sb.append(0 + "\n");
				else sb.append(minHeap.poll() + "\n");
			} else {
				minHeap.add(val);
			}
		}
		
		System.out.println(sb);
		
		
	}

}