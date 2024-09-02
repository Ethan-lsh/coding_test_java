import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Meeting implements Comparable<Meeting>{
		int start, end;

		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			// 미팅 종료 시간이 다르다면, 종료 시간에 대한 오름 차순 정렬.
			// 같다면, 시작 시간에 대한 오름차순 정렬
			return this.end != o.end ? this.end - o.end : this.start - o.start;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// 미팅룸 정보 초기화
		Meeting[] meetings = new Meeting[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		/**
		 * Greedy 알고리즘을 적용한다
		 */
		
		ArrayList<Meeting> result = new ArrayList<>();  // 선택된 미팅을 담을 리스트
		
		// step 1. 미팅룸 정보 정렬
		Arrays.sort(meetings);
		result.add(meetings[0]); // 0번 미팅을 추가 -> 바로 진행이 가능한 미팅
		
		for (int i = 1; i < N; i++) {
			// 선택된 미팅 (result)의 마지막 미팅의 종료 시간이 meetings[i]의 시작 시간보다 빠르다면 추가가 가능하다
			if (result.get(result.size() - 1).end <= meetings[i].start) {
				result.add(meetings[i]);
			}
		}
		
		System.out.println(result.size());
//		for (Meeting meeting : result) {
//			System.out.println(meeting.start + " " + meeting.end);
//		}
	}

}