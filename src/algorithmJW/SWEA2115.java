package algorithmJW;

import java.util.*;
import java.io.*;

public class SWEA2115 {
	
	static int N; //벌통크기
	static int M; //채취할 벌통 수
	static int C; //채취가능한 꿀 최대 양
	
	static int[][] cave;
	static int answer;
	
	static int[][] selectCave = new int[2][2];
	
	static int[] ap;
	static int[] bp;

	static int maxCnt;
	
	static int aHoney;
	static int bHoney;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			cave = new int[N][N];
			ap = new int[M];
			bp = new int[M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<N; j++) {
					cave[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			dfs(0,0,0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int r, int c) {
		if(depth == 2) {
			int honey = harvestHoney();
			if(honey > answer) answer = honey;
			return;
		}
		
		for(int i=r; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i==r) {
					if(depth != 0 && j < c+M) continue;
				}
				if(j+M-1>=N)continue;
				selectCave[depth] = new int[]{i,j};
				dfs(depth+1, i, j);
			}
		}
	}
	
	static public int harvestHoney() {
		
		for(int i=0; i<M; i++) {
			ap[i] = cave[selectCave[0][0]][selectCave[0][1] + i*1];
			bp[i] = cave[selectCave[1][0]][selectCave[1][1] + i*1];
		}
		
		aHoney = 0;
		bHoney = 0;
	
		maxCnt = 0;
		calcHoney(ap, 0, 0, 0, true);
		maxCnt = 0;
		calcHoney(bp, 0, 0, 0, false);
		
		return aHoney + bHoney;
	}
	
	static public void calcHoney(int[] p, int depth, int cnt, int honey, boolean type) {
		
		if(cnt > C) return;
		
		if(depth == p.length) {
			if(maxCnt < honey) {
				maxCnt = honey;
				if(type) {
					aHoney = honey;
				} else {
					bHoney = honey;
				}
			}
			return;
		}
		
		calcHoney(p, depth+1, cnt, honey, type);
		calcHoney(p, depth+1, cnt+p[depth], honey + (p[depth]*p[depth]), type);
	}
}
