import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

    static int N, M;
    static char[][] map;
    static int[][] result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            
            String[] st = br.readLine().split(" ");
            N = Integer.parseInt(st[0]);
            M = Integer.parseInt(st[1]);
            
            map = new char[N][M];
            result = new int[N][M];
            Queue<int[]> queue = new ArrayDeque<>();

            // 입력 처리 및 초기화
            for (int i = 0; i < N; i++) {
                char[] row = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    map[i][j] = row[j];
                    if (map[i][j] == 'W') {
                        result[i][j] = 0;  // 물의 위치는 거리 0
                        queue.add(new int[] {i, j});  // BFS 시작점으로 추가
                    } else {
                        result[i][j] = Integer.MAX_VALUE;  // 육지는 최대 거리로 초기화
                    }
                }
            }

            // BFS 시작
            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int x = current[0];
                int y = current[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (result[nx][ny] > result[x][y] + 1) {
                            result[nx][ny] = result[x][y] + 1;
                            queue.add(new int[] {nx, ny});
                        }
                    }
                }
            }

            // 결과 계산
            int ans = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 'L') {
                        ans += result[i][j];
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);
        }
    }
}