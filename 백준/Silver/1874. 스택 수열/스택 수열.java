import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		
		Stack<Integer> stack = new Stack<>();
		
		int last = 0;  // 현재까지 스택에 집어넣은 숫자의 크기
		
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int val = Integer.parseInt(st.nextToken());
			
			// 입력으로 들어오는 값이 스택의 마지막에 들어간 숫자보다 크다면 아직 스택에 숫자가 더 들어갈 수 있음
			if (val > last) {
				for (int i = last + 1; i <= val; i++) {
					stack.push(i);
					sb.append("+\n");
				}
				last = val;
			}
			
			// 스택에 마지막으로 들어간 숫자가 입력보다 크다면, 더 이상 스택에 추가로 값을 넣지는 못함
			// 스택의 끝이 입력과 다르다면 더 이상 동작 불가
			else if (stack.peek() != val) {
				System.out.println("NO");
				System.exit(0);
			}
			
			// 스택의 끝과 입력이 같다면 pop
			stack.pop();
			sb.append("-\n");
			
		}
		
		System.out.println(sb);
		
	}

}