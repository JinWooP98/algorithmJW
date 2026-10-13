package algorithmJW;

import java.io.*;
import java.util.*;

public class SWEA3282 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T =Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			int[][] dp = new int[N+1][W+1];
			
			int[] weight = new int[N+1];
			int[] value = new int[N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				weight[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=1; i<=N; i++) {
				for(int w=1; w<=W; w++) {
					if(weight[i] > w) {
						dp[i][w] = dp[i-1][w];
					} else {
						dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w-weight[i]] + value[i]);
					}
				}
			}
			sb.append("#").append(t+1).append(" ").append(dp[N][W]).append("\n");
		}
		System.out.println(sb);
	}
}
