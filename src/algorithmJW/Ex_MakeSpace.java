package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex_MakeSpace {

	static int N,white,green;
	static int[][] spaces;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine().trim());
		spaces = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			for (int j = 0; j < N; j++) {
				spaces[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		white = green = 0;
		
		cut(0,0, N);
		
		System.out.println(white);
		System.out.println(green);
	}

	static void cut(int r, int c, int size) {

		// 모두 같은 색상인지 확인
		int sum = 0;
		for (int i = 0; i < r+size; i++) {
			for(int j = c; j< c+size; j++) {
				sum += spaces[i][j];
			}
		}

		if(sum == size*size) { // 모두 1 : 초록색
			green++;
		} else if(sum == 0) {
			white++;
		} else { // 혼합 : 분할 => 재귀 유도 파트
			// 4분할
			int half = size/2;
			
			cut(r, c, half);
			cut(r, c+half, half);
			cut(r+half, c, half);
			cut(r+half, c+half, half);
			
		}
	}
}
