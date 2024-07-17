import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] students = new int[6][2];
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			String[] info = br.readLine().split(" ");
			
			// 성별
			int gender = Integer.parseInt(info[0]);
			
			// 학년
			int grade = Integer.parseInt(info[1]);
			
			students[grade-1][gender]++;
		}
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 2; j++) {
				answer += Math.ceil((double)students[i][j] / K);
			}
		}
		
		System.out.println(answer);
	}

}
