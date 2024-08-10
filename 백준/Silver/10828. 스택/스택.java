
import java.util.*;
import java.io.*;

class Stack {
	private List<Integer> stack;
	private int size;

	public Stack() {
		stack = new ArrayList<>();
	}

	/**
	 * void : push, top int : pop, size, empty (반환 값을 활용하는 케이스)
	 * 
	 * @param num
	 */

	public void push(int num) {
		stack.add(num);
		size++;
	}

	public int pop() {
		if (size == 0)
			return -1;
		int last = stack.remove(--size);

		return last;
	}

	public int size() {
		return size;
	}

	public int empty() {
		if (size == 0)
			return 1;
		else
			return 0;
	}

	public int top() {
		if (size == 0)
			return -1;
		else
			return stack.get(size - 1);
	}

}

public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();

		StringBuffer sb = new StringBuffer();
		Stack stack = new Stack();
		for (int i = 1; i <= N; i++) {
			String cmd = sc.next();

			if (cmd.equals("push")) {
				stack.push(sc.nextInt());
			} else if (cmd.equals("pop")) {
				sb.append(stack.pop() + "\n");
			} else if (cmd.equals("size")) {
				sb.append(stack.size() + "\n");
			} else if (cmd.equals("empty")) {
				sb.append(stack.empty() + "\n");
			} else if (cmd.equals("top")) {
				sb.append(stack.top() + "\n");
			}

		}
		
		System.out.println(sb.toString());

	}

}
