package algorithmJW;

import java.io.*;
import java.util.*;

public class SWEA1952 {
	
	static int[] price;
	static int[] schedule;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			price = new int[4];
			schedule = new int[12];
			
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			for(int i=0; i<4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(in.readLine().trim());
			for(int i=0; i<12; i++) {
				schedule[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = price[3];
			dfs(0, 0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int cost) {
		if(cost >= answer) return;
		
		if(depth >= 12) {
			answer = cost;
			return;
		}
		
		if(schedule[depth] == 0){
			dfs(depth+1, cost);
		} else {
			dfs(depth+1, cost + schedule[depth]*price[0]);
			dfs(depth+1, cost + price[1]);
			dfs(depth+3, cost + price[2]);
		}
	}
}
