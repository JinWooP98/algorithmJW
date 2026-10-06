package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2382 {
	
	static int N; // 셀 개수
	static int M; // 격리 시간
	static int K; // 미생물개수
	static int[][] cells;
	static Microorganism[] microorganisms;
	// 우 상 하 좌
	static int[] dx = {0, -1, 1, 0};
	static int[] dy = {1, 0, 0, -1};
	
	static class Microorganism implements Comparable<Microorganism>{
		int x;
		int y;
		int num;
		int dir;
		int time;
		int idx;
		
		public Microorganism(int x, int y, int num, int dir, int time, int idx) {
			this.x = x;
			this.y = y;
			this.num = num;
			this.dir = dir;
			this.time = time;
			this.idx = idx;
		}
		// 우선순위 큐에 담기 위해 정렬 방식 설정
		// 시간순으로 정렬
		// 시간이 같다면 num이 작은것 부터
		@Override
		public int compareTo(Microorganism o) {
			// TODO Auto-generated method stub
			if(this.time == o.time) {
				return this.num - o.num;
			}
			
			return this.time - o.time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			cells = new int[N][N];
			microorganisms = new Microorganism[K+1];
			
			PriorityQueue<Microorganism> pq = new PriorityQueue<>();
			
			for(int i=1; i<=K; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				Microorganism m = new Microorganism(x,y,num,dir%4,0,i);
				pq.offer(m);
				microorganisms[i] = m;
				cells[x][y] = i;
			}
			
			int answer = 0;
			
			for(int i=1; i<=M; i++) {
				int zero = 0;
				for(int j=1; j<=K; j++) {
					Microorganism m = pq.poll();
					if(m.num == 0) {
						zero++;
						continue;
					}
					
					int nx = m.x + dx[m.dir];
					int ny = m.y + dy[m.dir];
					
					if(cells[nx][ny] != 0) {
						if(microorganisms[cells[nx][ny]].time == i) {
							microorganisms[m.idx].num = microorganisms[cells[nx][ny]].num + m.num;
							microorganisms[cells[nx][ny]].num = 0;
						} 	
					}
					
					if(cells[m.x][m.y] == m.idx) {
						cells[m.x][m.y] = 0;
						cells[nx][ny] = m.idx;
					} else {
						cells[nx][ny] = m.idx;
					}
					
					if(nx == 0 || nx == N-1 || ny == 0 || ny == N-1) {
						microorganisms[m.idx].dir = reverseDir(m.dir);
						microorganisms[m.idx].num = m.num/2;
					}
					
					microorganisms[m.idx].x = nx;
					microorganisms[m.idx].y = ny;
					microorganisms[m.idx].time = i;
					
					pq.offer(microorganisms[m.idx]);
				}
				K -= zero;
				if(i == M) {
					int size = pq.size();

					for (int j = 0; j < size; j++) {
					    Microorganism m = pq.poll();
					    answer += m.num;
					}
				}
				
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public int reverseDir(int dir) {
		if(dir == 0) return 3;
		if(dir == 1) return 2;
		if(dir == 2) return 1;
		if(dir == 3) return 0;
		
		return 0;
	}
}
