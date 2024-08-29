import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M; // N:도시 크기, M:선택할 치킨집 수

	static List<int[]> houses = new ArrayList<>(); // 가정집의 위치 저장 리스트
	static List<int[]> chickens = new ArrayList<>(); // 치킨집 위치 저장 리스트

	static int[][] isSelected; // 선택된 치킨 집

	static int ans; // 최단 거리

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		ans = Integer.MAX_VALUE;

		isSelected = new int[M][];
		
		// 가정집과 치킨집의 위치를 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if (temp == 1)
					houses.add(new int[] { i, j });
				else if (temp == 2)
					chickens.add(new int[] { i, j });
			}
		}

		combination(0, 0);

		System.out.println(ans);
	}

	static void combination(int index, int cnt) {
		if (cnt == M) { // M개의 치킨집을 모두 고름
			int result = checkDistance();
			
			ans = Math.min(ans, result);
			
			return;
		}

		for (int i = index; i < chickens.size(); i++) {
			isSelected[cnt] = chickens.get(i);
			combination(i + 1, cnt + 1);
		}
	}

	// 최단 거리
	static int checkDistance() {
		int city_distance = 0;

		for (int[] house : houses) {
			int chicken_distance = Integer.MAX_VALUE;
			for (int[] chicken : isSelected) {   // 선택된 치킨집 만을 순회해야 한다
				chicken_distance = Math.min(chicken_distance,
						Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]));
			}
			city_distance += chicken_distance;
		}
		return city_distance;
	}

}