import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

public static int check_upper(int un) {
    int up = 0;
    // 주사위의 윗면을 정하기 위한 switch-case
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

    List<List<Integer>> dices = new ArrayList<>();

    for (int i = 0; i < n; i++) {
        List<Integer> line = Arrays.stream(br.readLine().split(" ")).map(Integer::parseInt)
                .collect(Collectors.toList());
        dices.add(line);
    }
//        System.out.println(dices.toString());

    int ans = 0;

    // 첫 주사위의 아래면을 정하는 for 문
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
