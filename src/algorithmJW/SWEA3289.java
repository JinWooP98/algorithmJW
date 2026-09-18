package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA3289 {
	
	static int N;
	static int M;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			boolean[][] graph = new boolean[N+1][N+1]; 
			
			sb.append("#").append(t+1).append(" ");
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int type = Integer.parseInt(st.nextToken());
				
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				if(type == 0) {
					graph[num1][num2] = true;
					graph[num2][num1] = true;
				} else {
					if(graph[num1][num2]) {
						sb.append(1);
					} else {
						sb.append(0);
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
