import java.util.*;
import java.io.*;

public class Solution {
	// public static String target = "C:\\SSAFY\\workspace\\java\\practice\\src\\input.txt";

	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		
		StringBuilder sb = new StringBuilder();

		while ((line = br.readLine()) != null) {
			for (int i = 0; i < line.length(); i++) {
				char alphabet = line.charAt(i);
				sb.append(alphabet - 'A' + 1 + " ");
			}
		}
	
		System.out.println(sb.toString());
	}

}
