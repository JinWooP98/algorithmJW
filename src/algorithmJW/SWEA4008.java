package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA4008 {
	
	static int N; // 숫자 개수
	static int[] numbers;
	static int[] oper;
	static boolean[] selected;
	static int minNum;
	static int maxNum;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			
			numbers = new int[N];
			oper = new int[N-1];
			selected = new boolean[N-1];
			
			
			int idx = 0;
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			for(int i=0; i<4; i++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0) continue;
				for(int j=0; j<num; j++) {
					oper[idx++] = i;
				}
			}
			
			st = new StringTokenizer(in.readLine().trim());
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			minNum = Integer.MAX_VALUE;
			maxNum = -(100000001);
			
			dfs(0, numbers[0]);
			
			answer = maxNum - minNum;
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int num) {
		if(depth == N-1) {
			
			if(num > maxNum) maxNum = num;
			if(num < minNum) minNum = num;
			
			return;
		}
		int operator = -1;
		for(int i=0; i<N-1; i++) {
			if(selected[i]) continue;
			if(operator == oper[i])continue;
			int pre = numbers[depth+1];
			operator = oper[i];
			selected[i] = true;
			int n = calc(num, pre, operator);
			dfs(depth+1, n);
			selected[i] = false;
		}
		
	}
	
	static public int calc(int num, int pre, int operator) {
		if(operator == 0) {
			return num + pre;
		} else if (operator == 1) {
			return num - pre;
		} else if (operator == 2) {
			return num * pre;
		} else if (operator == 3) {
			return num / pre;
		}
		
		return -1;
	}
}
