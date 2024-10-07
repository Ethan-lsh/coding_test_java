import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * DP문제 (누적합을 이용한 풀이)
 * 
 * @author Sanghyeon Lee
 *
 */
public class Main {

    static int N;
    static int[][] cost;
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }
        
        // 서로 다른 색상 중 최솟값을 누적하여 더한다
        // cost는 다른 색상을 선택하며 왔을 때,비용의 최솟값을 저장하는 배열이다
        // 예)i번 집에 빨간색을 칠했을 때 최소비용은, i-1에서 초록색과 파란색 중 더 적은 비용을 갖는 애를 선택한 경우이다
        for (int i = 1; i < N; i++) {
        	cost[i][0] += Math.min(cost[i-1][1], cost[i-1][2]);
        	cost[i][1] += Math.min(cost[i-1][0], cost[i-1][2]);
        	cost[i][2] += Math.min(cost[i-1][0], cost[i-1][1]);
        }
        
        // 마지막 행에 각 색상 중 최솟값이 결국 전체 집을 최소의 비용으로 칠한 경우와 같다
        System.out.println(Arrays.stream(cost[N-1]).min().getAsInt());
        
    }

}