package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA6782 {
	
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			long N = Long.parseLong(in.readLine().trim());
			
			answer = Integer.MAX_VALUE;
			dfs(N, 0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(long N,int cnt) {
		
		if(cnt >= answer) return;
		
		if(N == 2) {
			if(answer > cnt) answer = cnt;
		}
		
		long rootN = (long) Math.sqrt(N);
		
		if(rootN * rootN == N) {
			dfs(rootN, cnt+1);
		} else {
			// 만약 수가 20이라면
			// rootN = 4
			// 25까지 N+1을 해주어야 하므로
			// cnt에 (25-20)을 더해줌
			long next = rootN + 1;
			long nextNum = next * next;
			
			dfs(nextNum, cnt + (int)(nextNum - N));
		}
	}
}
