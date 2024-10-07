import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Tree 순회 문제
 * 트리 자료구조를 직접 설계하여 전위, 중위, 후위 순회를 구현하는 문제
 * 트리 자료구조를 직접 만들 줄 아는 능력이 중요
 * @author Sanghyeon Lee
 *
 */
public class Main {

	static int N;
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		private char val;
		Node left;
		Node right;
		
		Node (char val){
			super();
			this.val = val;
			this.left = null;
			this.right = null;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		Node[] tree = new Node[N+1];  // 트리의 노드를 담을 배열
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			char root = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			// 만약 루트 값이 있다면, 새로운 노드를 생성
			if (tree[root - 'A'] == null) {
				tree[root - 'A'] = new Node(root);
			}
			
			// 루트의 left 값이 있다면, 먼저 left를 root로 하는 노드를 생성
			// root노드의 left에 연결해준다
			if (left != '.') {
				tree[left - 'A'] = new Node(left);
				tree[root - 'A'].left = tree[left - 'A'];
			}
			
			// 루트의 right 값이 있다면, 먼저 right를 root로 하는 노드를 생성
			// root 노드의 right에 연결해준다
			if (right != '.') {
				tree[right - 'A'] = new Node(right);
				tree[root - 'A'].right = tree[right - 'A'];
			}
		}
		
		preOrder(tree[0]);
		sb.append("\n");
		
		inOrder(tree[0]);
		sb.append("\n");
		
		postOrder(tree[0]);
		sb.append("\n");
		
		System.out.println(sb);
	}
	
	private static void preOrder(Node node) {
		if (node == null) return;
		
		sb.append(node.val);
		preOrder(node.left);
		preOrder(node.right);
		
	}
	
	private static void inOrder(Node node) {
		if (node == null) return;
		
		inOrder(node.left);
		sb.append(node.val);
		inOrder(node.right);
	}
	
	private static void postOrder(Node node) {
		if (node == null) return;
		
		postOrder(node.left);
		postOrder(node.right);
		sb.append(node.val);
	}

}