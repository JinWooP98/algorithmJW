package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무높이 {
	
	static int treeNum;
	static int[] trees;
	static int maxHeight;
	static int odd;
	static int even;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			
			treeNum = Integer.parseInt(in.readLine().trim());
			StringTokenizer st = new StringTokenizer(in.readLine());
			
			maxHeight = 0;
			trees = new int[treeNum];
			
			for(int i=0; i<treeNum; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				if(maxHeight < trees[i]) {
					maxHeight = trees[i];
				}
			}
			
			odd = 0; even = 0;
			
			for(int i=0; i<treeNum; i++) {
				even += (maxHeight - trees[i]) / 2;
				odd += (maxHeight - trees[i]) % 2;
			}
			
			while(even >= odd + 2) {
				even--;
				odd += 2;
			}
			
			if(even > odd) {
				answer = even * 2;
			} else if(odd>even) {
				answer = odd * 2 - 1;
			} else {
				answer = even * 2;
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
