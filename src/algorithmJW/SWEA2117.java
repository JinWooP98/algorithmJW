package algorithmJW;

import java.io.*;
import java.util.*;
/*
 * 맵 전체를 돌며 멘허튼거리에 들어오는 집 갯수를 찾기
 * 입력받을때 집 개수를 구하고, 최대 서비스 영역 구하기
 * 가장 큰 서비스 영역부터 1개씩 줄여나가며 답 도출하기
 */
public class SWEA2117 {
	
	static int N; // 맵 크기
	static List<int[]> houses;
	static int cost; // 한 집이 내는 비용
	static int houseNum;
	static int K; // 서비스영역
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			houses = new ArrayList<>();
			houseNum = 0;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<N; j++) {
					if(Integer.parseInt(st.nextToken()) == 1) {
						houseNum++;
						houses.add(new int[] {i,j});
					}
				}
			}
			K = 1;
			while(true) {
				if(cost * houseNum <= (K*K + (K-1)*(K-1))) {
					K--;
					break;
				}
				K++;
			}
			answer = 0;
			for(int i=K; i>=1; i--) {
				if(answer != 0) break;
				clacService(i);
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void clacService(int k) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int inAreaNum = 0;
				
				for(int c=0; c<houses.size(); c++) {
					int[] house = houses.get(c);
					
					if(k > (Math.abs(i - house[0]) + Math.abs(j - house[1]))){
						inAreaNum++;
					}
				}
				
				if(cost * inAreaNum >= (k*k + (k-1)*(k-1))) {
					if(answer < inAreaNum) answer = inAreaNum;
				}
				
				if(answer == houseNum) return;
			}
		}
	}
}
