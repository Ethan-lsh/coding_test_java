import java.io.*;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N, M, C;
    static int[][] board;
    static boolean[][] visited;
     
    static int[][] places;
     
    static int ans;
     
    static int[][] dxy = {{0, -1}, {0, 1}};  // 좌우 가로방향으로 이동 가능
     
    public static void main(String[] args) throws IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int TC = Integer.parseInt(br.readLine());
        for (int tc=1; tc <= TC; tc++) {
            ans = 0;
             
            StringTokenizer st = new StringTokenizer(br.readLine());
             
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
             
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            // step 1. 일꾼의 시작점을 설정
            // 일꾼은 두명이고 서로 겹치지 않는 행에서 벌꿀을 채취해야 한다 (조합, nC2)
            places = new int[2][2];
            combination(0, 0);
             
            sb.append("#" + tc + " " + ans + "\n");
        }
 
        System.out.println(sb);
    }
     
    static void combination(int index, int cnt) {
        if (cnt == 2) {
            int[] first = places[0];
            int[] second = places[1];
            
            // 벌꿀 채취
            int first_honey_amount = getHoney(first[0], first[1], 0, 0, 0);
            int second_honey_amount = getHoney(second[0], second[1], 0, 0, 0);
             
            ans = Math.max(ans, first_honey_amount+second_honey_amount);
             
            return;
        }
         
        // 2차원 배열을 원소 순회 방식으로 접근
        for (int i = index; i < N*N; i++) { 
            int r = i / N;
            int c = i % N;
            if (c > N - M) continue;  // 열 시작점으로부터 M을 했을 때, 범위를 벗어남
             
            places[cnt][0] = r;
            places[cnt][1] = c;
             
            combination(i+M, cnt+1);
        }
         
    }
     
    /**
     * 벌꿀 채취 함수
     * @param sr 시작 행
     * @param sc 시작 열
     * @param box 채취한 벌꿀 통 갯수
     * @param sum 벌꿀 양의 합
     * @param revenue 이익
     * @return
     */
    static int getHoney(int sr, int sc, int box, int sum, int revenue) {
        if (sum > C) return 0;         // 합이 C보다 크면 채취가 불가능하기 때문에 0을 반환
        if (box == M) return revenue;  // M개의 벌꿀을 채취하면 이익을 반환한다 
         
        int curHoney = board[sr][sc+box];  // 현재 좌표의 벌꿀 양
        int A = getHoney(sr, sc, box+1, sum + curHoney, revenue + curHoney*curHoney);  // 꿀을 채취하는 경우
        int B = getHoney(sr, sc, box+1, sum, revenue);                      // 미채취
         
        return Math.max(A, B); // 채취를 했을 때와 하지 않았을 때 중 큰 이익을 반환
         
    }
}