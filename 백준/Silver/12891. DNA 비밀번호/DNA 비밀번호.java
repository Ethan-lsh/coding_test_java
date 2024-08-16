import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int S;
    static int P;
    static char[] dnaArray;
    static int[] condition;
    static int answer;
    static Map<Character, Integer> dnaMap = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        dnaArray = br.readLine().toCharArray();
        condition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        answer = 0;

        dnaMap.put('A', 0);
        dnaMap.put('C', 0);
        dnaMap.put('G', 0);
        dnaMap.put('T', 0);

        for(int i = 0; i < P; i++) {
            dnaMap.put(dnaArray[i], dnaMap.get(dnaArray[i]) + 1);
        }

        countArr();

        for(int i = 1; i < S - P + 1; i++) {
            dnaMap.put(dnaArray[i - 1], dnaMap.get(dnaArray[i - 1]) - 1);
            dnaMap.put(dnaArray[i + P - 1], dnaMap.get(dnaArray[i + P - 1]) + 1);
            countArr();
        }

        System.out.print(answer);

    }

    public static void countArr() {
        if(dnaMap.get('A') >= condition[0] && dnaMap.get('C') >= condition[1] &&dnaMap.get('G') >= condition[2] &&dnaMap.get('T') >= condition[3] ) {
            answer++;
        }
    }
}