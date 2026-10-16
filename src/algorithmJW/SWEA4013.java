package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 각 자석 입력받기
 * 2. int[] 에 넣기
 * 3. 회전 후 기준을 int idx 로 설정... 0 기준으로 하기 정방향 회전이면 -1, 역방향이면 +1
 * 4. 회전할때 비교할것은 2, 6
 * 5. 
 */

public class SWEA4013 {	
	static int[][] temp;
	static int[] idxArr;
	
	static int K;
	static int[][] rotationDir;
	static int answer;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			K = Integer.parseInt(in.readLine().trim());
			temp = new int[4][8];
			idxArr = new int[4];
			
			StringTokenizer st;
			for(int i=0; i<4; i++) {
				temp[i] = new int[8];
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<8; j++) {
					temp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			rotationDir = new int[K][2];
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(in.readLine().trim());
				rotationDir[i][0] = Integer.parseInt(st.nextToken());
				rotationDir[i][1] = Integer.parseInt(st.nextToken());
			}
			
			for(int k=0; k<K; k++) {
				rotation((rotationDir[k][0] - 1),0,rotationDir[k][1]);
			}
			
			answer = 0;
			
			for(int i=0; i<4; i++) {
				if(temp[i][idxArr[i]] == 1) {
					if(i==0) answer += 1;
					if(i==1) answer += 2;
					if(i==2) answer += 4;
					if(i==3) answer += 8;
				}
			}
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void rotation(int idx, int type, int dir) {
		int targetIdx = idxArr[idx];
		
		if(dir == -1) {
			idxArr[idx] = (idxArr[idx] + 1) % 8;
		} else {
			if(idxArr[idx] > 0) {
				idxArr[idx]--;
			} else {
				idxArr[idx] = 7;
			}
			
		}
			if(idx + 1 < 4 && type != 1 && temp[idx][(targetIdx + 2)%8] != temp[(idx+1)%8][(idxArr[idx+1] + 6)%8]) {
				rotation(idx+1, -1, dir*(-1));
			}
				
			if(idx == 0) {
				if(idx - 1 >= 0 && type != -1 && temp[idx][(targetIdx + 6)%8] != temp[3][(idxArr[idx-1] + 2)%8]) {
					rotation(idx-1, 1, dir*(-1));
				}
			} else {
				if(idx - 1 >= 0 && type != -1 && temp[idx][(targetIdx + 6)%8] != temp[idx-1][(idxArr[idx-1] + 2)%8]) {
					rotation(idx-1, 1, dir*(-1));
				}
			}
			
			
		
	}
}
