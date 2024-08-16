import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] sourArr;
    static int[] bitterArr;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        sourArr = new int[N];
        bitterArr = new int[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sourArr[i] = Integer.parseInt(st.nextToken());
            bitterArr[i] = Integer.parseInt(st.nextToken());
            if( i == 0 ) {
                answer = Math.abs(sourArr[0] - bitterArr[0]);
            }
        }

        for(int i = 1; i <= N; i++) {
            combination(0, 0, i, 1, 0);
        }

        System.out.println(answer);

    }

    public static void combination(int start, int cnt, int r, int sour, int bitter) {
        if(cnt == r) {
            answer = Math.min(answer, Math.abs(sour - bitter));
        } else {
            for(int i = start; i < N; i++) {
                sour *= sourArr[i];
                bitter += bitterArr[i];
                combination(i + 1, cnt + 1, r, sour, bitter);
                sour /= sourArr[i];
                bitter -= bitterArr[i];
            }
        }
    }
    
}