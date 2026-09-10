package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게리맨더링 {
	
	static int N;
	static int[] area;
	static int[][] graph;
	static boolean[] selected;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine().trim());
		area = new int[N+1];
		
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		for(int i=1; i<=N; i++) {
			area[i] = Integer.parseInt(st.nextToken());
		}
		
		graph = new int[N+1][];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine().trim());
			int connectedNum = Integer.parseInt(st.nextToken());
			
			graph[i] = new int[connectedNum];
			
			for(int j=0; j<connectedNum; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		selected = new boolean[N+1];
		
		dfs(1);
	}
	
	static public void dfs(int depth) {
		if(depth == N+1) {
			// 각 구역들이 연결되어있는지 확인
			return;
		}
		
		
		selected[depth] = true;
		dfs(depth+1);
		selected[depth] = false;
		dfs(depth+1);
	}
}
