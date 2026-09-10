package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SWEA2805 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(in.readLine().trim());
			int half = N/2;
			int answer = 0;
			
			for(int i=0; i<N; i++) {
				String crops = in.readLine().trim();
				for(int j=0; j<N; j++) {
					int crop = crops.charAt(j) - '0';
					if((Math.abs(i-half) + Math.abs(j - half)) <= half){
						answer += crop;
					}
				}
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
}
