package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class SWEA4796 {
	
	static int N;
	static int[] mountain;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		
		StreamTokenizer st = new StreamTokenizer(
		        new BufferedReader(new InputStreamReader(System.in))
		);

		st.nextToken();
		int T = (int) st.nval;

		for (int t = 0; t < T; t++) {

		    st.nextToken();
		    N = (int) st.nval;

		    mountain = new int[N];

		    for (int i = 0; i < N; i++) {
		        st.nextToken();
		        mountain[i] = (int) st.nval;
		    }
			answer = 0;
			check();
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void check() {
		int up = 0;
		int down = 0;
		
		for(int i=0; i<N-1; i++) {
			if(mountain[i] < mountain[i+1]) {
				if(down != 0) {
					answer += up * down;
					up = 0;
					down = 0;
				}
				up++;
			} else {
				down++;
			}
		}
		answer += up * down;
	}
}
