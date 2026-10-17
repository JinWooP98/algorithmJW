package algorithmJW;

import java.io.*;
import java.util.*;

public class SWEA14510 {
	
	static int odd;
	static int even;
	static int[] trees;
	static int maxHeight;
	static int answer;
	static int N; // 나무 수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			
			odd = 0;
			even = 0;
			answer = 0;
			maxHeight = 0;
			
			trees = new int[N];
			
			StringTokenizer st =new StringTokenizer(in.readLine().trim());
			for(int i=0; i<N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				if(trees[i] > maxHeight) maxHeight = trees[i];
			}
			
			
			
			for(int i=0; i<N; i++) {
				int h = maxHeight - trees[i];
				even += h / 2;
				odd += h % 2;
			}
			
			while(even > odd+1) {
				even--;
				odd += 2;
			}
			
			if(even == odd) {
				answer = even * 2;
			} else if(even > odd) {
				answer = even * 2;
			} else {
				answer = odd * 2 - 1;
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
