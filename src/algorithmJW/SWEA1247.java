package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1247 {
	
	static int N; // 사람 수
	static int[][] temp; // 0, 1 은 회사, 집 2~ 고객들 좌표
	static int answer;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			
			N = Integer.parseInt(in.readLine().trim());
			temp = new int[N+2][2];
			
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			
			for(int i=0; i<N+2; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				temp[i][0] = x;
				temp[i][1] = y;
			}
			
			visited = new boolean[N+2];
			answer = Integer.MAX_VALUE;
			dfs(0, 0, 0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int len, int idx) {
		if(len + calcLen(idx, 1) >= answer) {
			return;
		}
		
		if(depth == N) {
			answer = len + calcLen(idx, 1);
			return;
		}
		
		for(int i=2; i<N+2; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			dfs(depth+1, len + calcLen(idx, i), i);
			visited[i] = false;
		}
	}
	
	static public int calcLen(int start, int finish) {
		
		return Math.abs(temp[start][0] - temp[finish][0]) + Math.abs(temp[start][1] - temp[finish][1]);
	}
}
