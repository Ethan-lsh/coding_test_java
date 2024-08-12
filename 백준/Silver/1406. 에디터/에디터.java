import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Stack<String> left_stack = new Stack<>();   // 왼쪽 커서를 가리킬 stack
		Stack<String> right_stack = new Stack<>();  // 오른쪽 커서를 가리키는 stack
		
		Scanner sc = new Scanner(System.in);
		
		StringBuilder sb = new StringBuilder();
		
		String[] str = sc.nextLine().split("");
		for (String s : str) {
			left_stack.add(s);
		}
		
		int M = sc.nextInt();
		
		/**
		 * 커서가 왼쪽으로 이동하여 제거되는 요소는 left_stack에서 pop하여 'right_stack'에 저장.
		 * 커서가 오른쪽으로 이동하여 새로 들어오는 요소는 right_stack에서 pop하여 left_stack에 저장
		 * 즉,커서가 움직임에 따라 일시적으로 분리되는 배열 요소를 각각 left와 right에 옮겨가며 저장하는 방식.
		 * 단, 최종 결과값은 right_stack에 저장한다. -> right_stack은 오른쪽 커서를 가리킨다 = 나중에 들어오는 요소를 저장 (LIFO)
		 */
		for (int i = 0; i < M; i++) {
			String cmd = sc.next();
			
			if (cmd.equals("L")) {
				if (!left_stack.empty()) right_stack.push(left_stack.pop());
			} else if (cmd.equals("D")) {
				if (!right_stack.empty()) left_stack.push(right_stack.pop());
			} else if (cmd.equals("B")) {
				if (!left_stack.empty()) {
					left_stack.pop();
					continue;
				}
			} else if (cmd.equals("P")) {
				String s = sc.next();
				left_stack.push(s);
				continue;
			}
		}
		
		while (!left_stack.empty())
			right_stack.push(left_stack.pop());
		
		while (!right_stack.empty())
			sb.append(right_stack.pop());
		
		System.out.println(sb.toString());
		
	}

}
