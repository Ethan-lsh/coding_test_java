import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	/**
	 * 주사위의 윗면의 인덱스를 구하는 함수
	 * @param un 아랫면 숫자가 가리키는 주사위의 인덱스 값
	 * @return 윗면의 인덱스
	 */
	public static int check_upper(int un) {
		int up = 0;
		switch (un) {
		case 0:
			up = 5;
			break;
		case 1:
			up = 3;
			break;
		case 2:
			up = 4;
			break;
		case 3:
			up = 1;
			break;
		case 4:
			up = 2;
			break;
		case 5:
			up = 0;
			break;
		}

		return up;
	}

	/**
	 * 현재 주사위의 옆면에서 가장 큰 값을 구하는 함수
	 * @param dice 현재 주사위 List
	 * @param under 아랫면 숫자 값
	 * @param upper 윗면 숫자 값
	 * @return 옆면의 최대 값
	 */
	public static int cal_max(List<Integer> dice, int under, int upper) {
		int max_side = 0;
		for (int num : dice) {
			if (num != under && num != upper) {
				max_side = Math.max(max_side, num);
			}
		}

		return max_side;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 주사위 개수

		// 주사위를 담는 배열. 값을 이용해 인덱스 값을 불러와야 하기 때문에 List<Integer>로 설정
		List<List<Integer>> dices = new ArrayList<>();

		// 값 저장
		// List의 index:0~5는 (A, B, C, D, E, F)를 가리킨다
		// index=0='A'
		for (int i = 0; i < n; i++) {
			List<Integer> line = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
					.collect(Collectors.toList());
			dices.add(line);
		}

		int ans = 0; // 정답

		/**
		 * 문제 해석
		 * 1.주사위의 서로 마주보는 면의 합은 '7'이 아니며, 곂치는 면의 숫자는 같다
		 *   -> 아랫면이 가리키는 값을 사용해, 마주보는 윗면의 인덱스를 구하고 윗면의 값을 구해야 한다
		 * 2.주사위는 90, 180, 270도 회전이 가능하다
		 *   -> 아랫면과 윗면의 숫자를 제외한 숫자들이 옆면에 오는 위치는 자유롭다는 의미
		 *   -> 즉, 윗면과 아랫면의 숫자를 제외한 값 중 최대 값을 찾으면 된다
		 */
		int under = 0, upper = 0;
		List<Integer> dice;
		for (int k = 0; k < 6; k++) { // k=0='A', k=1='B', ...
			int temp = 0;

			dice = dices.get(0); // 첫 번째 주사위

			under = dice.get(k); // k가 가리키는 아랫면 주사위의 숫자
			upper = dice.get(check_upper(k)); // 윗면 주사위의 숫자

			temp += cal_max(dice, under, upper);

			int num = 1;
			while (num < n) {
				dice = dices.get(num); // 새로운 주사위

				// 위에 올라온 주사위의 아랫면은 아래 주사위의 윗면과 같아야 한다
				under = upper;

				// 새로운 아랫면의 숫자가 가리키는 면의 위치를 구함
				int under_face = dice.indexOf(under);
				int upper_face = check_upper(under_face);

				upper = dice.get(upper_face);

				temp += cal_max(dice, under, upper);

				num++;
			}

			ans = Math.max(ans, temp);
		}

		System.out.println(ans);

	}
}
