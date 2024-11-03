import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static final int WARNING = 1;
    static final int DEATH = 2;
    static int[][] map = new int[501][501];
    static final int INF = 250001;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 위험한 구역
        N = Integer.parseInt(st.nextToken());
        for (int n = 0; n < N; n++) {

            st = new StringTokenizer(br.readLine());
            
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            mark(x1, y1, x2, y2, WARNING);
        }
        
        
        // 죽음의 구역
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            
            mark(x1, y1, x2, y2, DEATH);
        }
        
        /**
         * 0-1 BFS를 실행
         * 최대한 생명력을 잃지 않는 좌표를 먼저 방문하는 방식으로 탐색한다
         * 우선순위 큐를 사용
         */
        
        bfs(0, 0, 0);
        
        
    }
    
    private static void mark(int x1, int y1, int x2, int y2, int marker) {
        
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                map[x][y] = marker;
            }
        }
        
    }
    
    private static void bfs(int r, int c, int cost) {
        ArrayDeque<int[]> dq = new ArrayDeque<>();
        dq.addLast(new int[] {r, c, cost});

        int[][] dist = new int[501][501];
        boolean[][] visited = new boolean[501][501];

        // Initialize only the start point to reduce initialization memory
        for (int[] row : dist) Arrays.fill(row, INF);
        dist[0][0] = 0;
        visited[0][0] = true;

        while (!dq.isEmpty()) {
            int[] cur = dq.pollFirst();
            int x = cur[0];
            int y = cur[1];
            int curDist = cur[2];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 탐색 실패 조건 (범위 초과, 죽음 구역)
                if (nx < 0 || nx > 500 || ny < 0 || ny > 500 || map[nx][ny] == DEATH) continue;

                // 이미 방문한 곳은 더 이상 탐색하지 않음
                int newDist = curDist + (map[nx][ny] == WARNING ? 1 : 0);
                if (visited[nx][ny] && dist[nx][ny] <= newDist) continue;

                dist[nx][ny] = newDist;
                visited[nx][ny] = true;

                // Warning 지역일 경우 우선순위 낮게, 일반 지역일 경우 우선순위 높게
                if (map[nx][ny] == WARNING) dq.addLast(new int[] {nx, ny, newDist});
                else dq.addFirst(new int[] {nx, ny, newDist});
            }
        }

        System.out.println(dist[500][500] == INF ? -1 : dist[500][500]);
    }

}