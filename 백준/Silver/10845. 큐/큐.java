import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static List<Integer> q = new ArrayList<>();
	public static int size;
	
	public static void push(int n) {
		q.add(n);
		size++;
	}
	
	public static int pop() {
		if (size == 0) return -1;
		size--;
		return q.remove(0);
	}
	
	public static int size() {
		return size;
	}
	
	public static int empty() {
		if (q.isEmpty()) return 1;
		else return 0;
	}
	
	public static int front() {
		if (q.isEmpty()) return -1;
		return q.get(0);
	}
	
	public static int back() {
		if (q.isEmpty()) return -1;
		return q.get(size - 1);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String cmd = sc.next();
			
			if (cmd.equals("push")) {
				int num = sc.nextInt();
				push(num);
			} else if (cmd.equals("pop")) {
				sb.append(pop() + "\n");
			} else if (cmd.equals("size")) {
				sb.append(size() + "\n");
			} else if (cmd.equals("empty")) {
				sb.append(empty() + "\n");
			} else if (cmd.equals("front")) {
				sb.append(front() + "\n");
			} else if (cmd.equals("back")) {
				sb.append(back() + "\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}
