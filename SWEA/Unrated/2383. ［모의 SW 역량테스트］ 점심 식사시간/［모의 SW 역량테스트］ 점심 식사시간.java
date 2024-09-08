import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;              // 배열의 크기
	static People[] people;   // 사람들의 위치
	static Stair[] stairs;   // 사람들의 위치
	static boolean[] isSelected;
	
	static int peopleLength, stairLength;
	
	static int ans;
	
	/*좌표 저장을 위한 클래스*/
	static class People {
		int r, c;
		int[] distance;

		public People(int r, int c) {
			super();
			this.r = r;
			this.c = c;
			this.distance = new int[2];
		}
		
	}
	
	static class Stair {
		int r, c;
		int height;
		
		public Stair(int r, int c, int height) {
			super();
			this.r = r;
			this.c = c;
			this.height = height;
		}
		
		public int distance(int r, int c) {
			return Math.abs(this.r - r) + Math.abs(this.c - c);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			people = new People[10];
			stairs = new Stair[2];
			
			peopleLength = 0;
			stairLength = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(st.nextToken());
					
					// 사람과 계단의 위치를 저장
					if (temp == 1) people[peopleLength++] = new People(i, j);
					if (temp >= 2) stairs[stairLength++] = new Stair(i, j, temp);
				}
			}
			
			for (int i = 0; i < peopleLength; i++) {
				People p = people[i];
				p.distance[0] = stairs[0].distance(p.r, p.c);
				p.distance[1] = stairs[1].distance(p.r, p.c);
			}
			
			ans = Integer.MAX_VALUE;
			isSelected = new boolean[peopleLength];
			subset(0);
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb);
	}
	
	/*계단을 내려갈 사람의 부분집합을 구한다*/
	private static void subset(int idx) {
		
		if (idx == peopleLength) {
			// 계단 도착 시간
			ArrayList<Integer> stair1 = new ArrayList<>();
			ArrayList<Integer> stair2 = new ArrayList<>();
			
			for (int i = 0; i < peopleLength; i++) {
				if (isSelected[i]) stair1.add(people[i].distance[0] + 1);
				else stair2.add(people[i].distance[1] + 1);
			}
			
			// 오름차순으로 정렬 (계단까지 거리가 짧은 순으로 정렬)
			stair1.sort((o1, o2) -> o1 - o2);
			stair2.sort((o1, o2) -> o1 - o2);
			
			// 각 계단을 내려가는데 걸리는 시간
			int time1 = getDownStair(stair1, 0);
			int time2 = getDownStair(stair2, 1);
			
			// time이 더 큰 쪽을 선택해야 작은 쪽의 계단을 통하는 모든 인원도 계단을 내려올 수 있음
			ans = Math.min(ans, Math.max(time1, time2));
			return;
		}
		
		isSelected[idx] = true;
		subset(idx + 1);
		
		isSelected[idx] = false;
		subset(idx + 1);
		
	}
	
	/**
	 * 계단을 내려가는 시간을 구함
	 * @param times 해당 계단에 가는데 걸리는 시간들
	 * @param s 계단 번호
	 * @return 현재 시간
	 */
	private static int getDownStair(ArrayList<Integer> times, int s) {
		if (times.isEmpty()) return 0;
		
		// 계단을 사용할 사람들 저장
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		int downTime = stairs[s].height;
		
		// arrivalTime:사람이 계단에 도착하는데 걸리는 시간
		for (int arrivalTime : times) {
			// 도착 시간이 현재 시간보다 빠른 경우, 큐에서 제거 (이미 내려감)
			while (!q.isEmpty() && q.peek() <= arrivalTime) {
				q.poll();
			}
			
			// 계단 사용자가 3명 미만인 경우
			if (q.size() < 3) {
				q.offer(arrivalTime + downTime);
			} else { // 계단 사용자가 3명 이상인 경우
				int nextTime = q.poll();
				// 가장 마지막에 있는 사람의 시간에 내려가는 시간을 더한다
				q.offer(nextTime + downTime);
			}
		}
		
		int currentTime = 0;
		
		// 큐가 비어있지 않다면, 마지막에 있는 사람의 시간이 결국 최종 시간과 같다
		while (!q.isEmpty()) {
			currentTime = q.poll();
		}
		
		return currentTime;
	}
}
