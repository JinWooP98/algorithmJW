package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1486 {
	
	static int N;
	static int minHeight;
	static int[] employees;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			minHeight = Integer.parseInt(st.nextToken());
			
			employees = new int[N];
			answer = Integer.MAX_VALUE;
			
			st = new StringTokenizer(in.readLine().trim());
			for(int i=0; i<N; i++) {
				employees[i] = Integer.parseInt(st.nextToken());
				if(employees[i] == minHeight) answer = minHeight;
			}
			
			if(answer != minHeight) {
				dfs(0,0);
			}
			
			sb.append("#").append(t+1).append(" ").append(answer - minHeight).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int height) {
		
		if(height >= answer) return;
		
		if(depth == N) {
			if(answer > height && height >= minHeight) answer = height;
			return;
		}
		
		dfs(depth+1, height + employees[depth]);
		dfs(depth+1, height);
	}
}