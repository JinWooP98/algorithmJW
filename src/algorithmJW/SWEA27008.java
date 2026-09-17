package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA27008 {
	
	static int N;
	static int[] numbers;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T= Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			
			N = Integer.parseInt(in.readLine().trim());
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			
			numbers = new int[N];
			
			for(int i=0; i<N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i < N; i++) {
			    for (int j = 0; j < i; j++) {
			        int value = (i - j) * (numbers[i] + numbers[j]);
			        answer = Math.max(answer, value);
			    }
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
}
