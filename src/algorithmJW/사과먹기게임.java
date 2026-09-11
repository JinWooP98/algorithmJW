package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 사과먹기게임 {
	
	/**
	 * 전략
	 * 1. 규칙 설정 (머리방향, 각 위치)에 따른 턴 횟수와 그 동작 시 머리 방향
	 * 2. 사과를 우선순위 큐에 담아 순서대로 순회
	 * 
	 */
	static int N; // board 크기
	static int[][][] rule; // 규칙 : int[머리방향 : mode 0,1,2,3 (우 하 좌 상)][다른사과위치(0: -+, 1: ++, 2: +-, 3: --)]
	// [ 회전 횟수, 회전 후 머리 방향]
	static PriorityQueue<Apple> pq; // 사과를 담는 우선순위 큐(사과 번호 기준 오름차순)
	static int answer; // 회전 횟수 담기
	
	static class Apple implements Comparable<Apple>{
		int r;
		int c;
		int num;
		
		public Apple(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}

		@Override
		public int compareTo(Apple o) {
			return this.num - o.num;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		rule = new int[4][4][];
		
		makeRule();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			
			pq = new PriorityQueue<>();
			
			for(int i=0; i<N; i++) {
				String arr = in.readLine().trim();
				for(int j=0; j<N; j++) {
					if((arr.charAt(j) - '0') != 0) {
						pq.offer(new Apple(i,j,arr.charAt(j) - '0'));
					}
				}
			}
			answer = 0;
			eatAppleGame();
			System.out.println(answer);
		}
		
	}
	
	static public void eatAppleGame() {
		int mode = 0;
		int r = 0;
		int c = 0;
		while(!pq.isEmpty()) {
			Apple apple = pq.poll();
			
			int aR = apple.r;
			int aC = apple.c;
			
			int dir = checkDirection(aR - r, aC - c);
			
			int[] result = rule[mode][dir];
			
			mode = result[1];
			answer += result[0];
			
			r = aR;
			c = aC;
			
		}
	}
	
	static public int checkDirection(int r, int c) {
		if(r < 0 && c > 0) return 0;
		if(r > 0 && c > 0) return 1;
		if(r > 0 && c < 0) return 2;
		if(r < 0 && c < 0) return 3;
		
		return -1;
	}
	
	static public void makeRule() {
		rule[0][0] =new int[]{3, 3};
		rule[0][1] =new int[]{1, 1};
		rule[0][2] =new int[]{2, 2};
		rule[0][3] =new int[]{3, 3};
		
		rule[1][0] =new int[]{3, 0};
		rule[1][1] =new int[]{3, 0};
		rule[1][2] =new int[]{1, 2};
		rule[1][3] =new int[]{2, 3};
		
		rule[2][0] =new int[]{2, 0};
		rule[2][1] =new int[]{3, 1};
		rule[2][2] =new int[]{3, 1};
		rule[2][3] =new int[]{1, 3};
		
		rule[3][0] =new int[]{1, 0};
		rule[3][1] =new int[]{2, 1};
		rule[3][2] =new int[]{3, 2};
		rule[3][3] =new int[]{3, 2};
	}
}
