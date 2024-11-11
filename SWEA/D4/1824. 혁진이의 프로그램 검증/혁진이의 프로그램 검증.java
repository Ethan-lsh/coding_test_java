import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

    static int R, C;
    static char[][] map;

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static HashMap<Character, Integer> hash = new HashMap<>();

    static String ans;

    public static void main(String[] args) throws IOException {

        // 해쉬 맵 정의
        hash.put('<', 2);
        hash.put('>', 3);
        hash.put('^', 0);
        hash.put('v', 1);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());

            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new char[R][C];

            for (int i = 0; i < R; i++) {
                char[] ch = br.readLine().toCharArray();
                for (int j = 0; j < C; j++) {
                    map[i][j] = ch[j];
                }
            }

            ans = solve() ? "YES" : "NO";
            System.out.println("#" + tc + " " + ans);
        }
    }

    private static boolean solve() {

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[R][C][4][16];

        // 초기 위치와 방향, 메모리 초기화
        dq.offer(new int[] {0, 0, 3, 0});
        visited[0][0][3][0] = true;

        while (!dq.isEmpty()) {

            int[] cur = dq.poll();

            int x = cur[0], y = cur[1], d = cur[2], mem = cur[3];
            char val = map[x][y];

            if (val == '@') {
                return true;
            }

            // 방향을 결정하는 부분
            if (val == '?') {
                for (int dir = 0; dir < 4; dir++) {
                	// 격자 범위를 벗어나는 이동을 처리하기 위한 부분
                	// 공식으로 외워서 사용하자
                    int nx = (x + dx[dir] + R) % R;
                    int ny = (y + dy[dir] + C) % C;
                    if (!visited[nx][ny][dir][mem]) {
                        visited[nx][ny][dir][mem] = true;
                        dq.offer(new int[] {nx, ny, dir, mem});
                    }
                }
                continue;
            }

            if (val == '<' || val == '^' || val == '>' || val == 'v') {
                d = hash.get(val);
            } else if (val == '_') {
                d = (mem == 0) ? 3 : 2;
            } else if (val == '|') {
                d = (mem == 0) ? 1 : 0;
            } else if (val == '+') {
                mem = (mem + 1) % 16;
            } else if (val == '-') {
                mem = (mem == 0) ? 15 : mem - 1;
            } else if ('0' <= val && val <= '9') {
                mem = val - '0';
            }

            // 격자 범위를 벗어나는 이동을 처리하기 위한 부분
            int nx = (x + dx[d] + R) % R;
            int ny = (y + dy[d] + C) % C;

            // 이미 방문한 경우 넘어감
            if (!visited[nx][ny][d][mem]) {
                visited[nx][ny][d][mem] = true;
                dq.offer(new int[] {nx, ny, d, mem});
            }
        }

        return false;
    }
}