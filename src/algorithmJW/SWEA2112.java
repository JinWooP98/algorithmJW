package algorithmJW;

import java.util.*;
import java.io.*;

public class SWEA2112 {
	
	static int D; // 두께
	static int W; // 가로크기
	static int K; // 합격기준
	
	static int[][] cells; 
	static boolean[] accepted;
	
	static int answer;
	static int[] type;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			type = new int[D];
			for(int i=0; i<D; i++) {
				type[i] = -1;
			}
			
			cells = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<W; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			answer = K;
			dfs(0,0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int cnt) {
		if(cnt > answer) return;
		if(depth == D) {
			// 여기에서 검사하기
			if(checkCells()) {
				answer = cnt;
			}
			return;
		}
		// 약품투여 X
		dfs(depth+1, cnt);
		// A약품투여
		type[depth] = 0;
		dfs(depth+1, cnt+1);
		// B약품투여
		type[depth] = 1;
		dfs(depth+1, cnt+1);
		
		type[depth] = -1;
	}
	
	static public boolean checkCells() {
		
		for(int i=0; i<W; i++) {
			
			int aCnt = 0;
			int bCnt = 0;
			boolean isPass = false;
			
			for(int j=0; j<D; j++) {
				if(type[j] == -1) {
					if(cells[j][i] == 0) {
						aCnt++;
						bCnt = 0;
					} else {
						bCnt++;
						aCnt = 0;
					}
				} else {
					if(type[j] == 0) {
						aCnt++;
						bCnt = 0;
					} else {
						bCnt++;
						aCnt = 0;
					}
				}
				
				if(aCnt == K || bCnt == K) {
					isPass = true;
					break;
				}
			}
			
			if(!isPass) return false;
		}
		
		return true;
	}
}
