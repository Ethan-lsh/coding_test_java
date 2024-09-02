import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Set{
		List<Integer> set;

		public Set(List<Integer> set) {
			super();
			this.set = set;
		}
		
		public void add(int x) {
			set.add(x);
		}
		
		public void remove(int x) {
			for (int i = 0; i < set.size(); i++) {
				if (set.get(i) == x) {
					set.remove(i);
					break;
				}
			}
		}
		
		public boolean check(int x) {
			if (set.contains(Integer.valueOf(x))) return true;
			return false;
		}
		
		public void toggle(int x) {
			if (set.contains(Integer.valueOf(x))) set.remove(Integer.valueOf(x));
			else set.add(x);
		}
		
		public void all() {
			set.clear();
			for (int i = 1; i <= 20; i++) set.add(i);
		}
		
		public void empty() {
			set.clear();
		}
		
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int M = Integer.parseInt(br.readLine());

		Set s = new Set(new ArrayList<>());
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			switch (cmd) {
			case "add":
				s.add(Integer.parseInt(st.nextToken()));
				break;
			case "check":
				sb.append(s.check(Integer.parseInt(st.nextToken())) ? 1 : 0);
				sb.append("\n");
				break;
			case "remove":
				s.remove(Integer.parseInt(st.nextToken()));
				break;
			case "toggle":
				s.toggle(Integer.parseInt(st.nextToken()));
				break;
			case "all":
				s.all();
				break;
			case "empty":
				s.empty();
				break;
			}
			
		}
		
		System.out.println(sb.toString());
		
	}

}