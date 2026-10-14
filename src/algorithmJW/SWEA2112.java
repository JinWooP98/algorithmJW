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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cells = new int[D][W];
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<W; j++) {
					cells[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			answer = K;
			
			if(!checkCell()) {
			    dfs(0, 0);
			} else {
			    answer = 0;
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int idx) {
		if(depth >= answer) return;
		
		if(idx == D) return;
		
		for(int i=idx; i<D; i++) {
			int[] temp = cells[i].clone();
			
			// A 약품 투입
	        injectMedicine(i, 0);

	        if(checkCell()) {
	            answer = Math.min(answer, depth + 1);
	        } else if(depth + 1 < answer) {
	            dfs(depth + 1, i + 1);
	        }

	        // B 약품 투입
	        injectMedicine(i, 1);

	        if(checkCell()) {
	            answer = Math.min(answer, depth + 1);
	        } else if(depth + 1 < answer) {
	            dfs(depth + 1, i + 1);
	        }
			
			cells[i] = temp;
		}
	}
	
	static public boolean checkCell() {
		
		accepted = new boolean[W];
		
		for(int i=0; i<W; i++) {
			int cnt = 0;
			int type = -1;
			for(int j=0; j<D; j++) {
				
				if(cells[j][i] == type) {
					cnt++;
				} else {
					cnt = 1;
					type = cells[j][i];
				}
				
				if(cnt == K) {
					accepted[i] = true;
					break;
				}
			}
		}
		
		boolean isAccept = true;
		
		for(int i=0; i<W; i++) {
			if(!accepted[i]) {
				isAccept = false;
				break;
			}
		}
		
		return isAccept;
	}
	
	static public void injectMedicine(int idx, int type) {
		for(int i=0; i<W; i++) {
			cells[idx][i] = type;
		}
	}
}
