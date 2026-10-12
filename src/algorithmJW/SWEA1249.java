package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA1249 {
	
	static int N; // 지도 크기
	static int[][] map;
	static int answer;
	static int[][] dist;
	static PriorityQueue<Robot> pq;
	
	// 우 하 좌 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	
	static class Robot implements Comparable<Robot>{
		int x;
		int y;
		int time;
		
		Robot(int x, int y, int time){
			this.x = x;
			this.y = y;
			this.time = time;
		}
		
		@Override
		public int compareTo(Robot o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			map = new int[N][N];
			dist = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String arr = in.readLine().trim();
				for(int j=0; j<N; j++) {
					map[i][j] = arr.charAt(j) - '0';
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			dist[0][0] = 0;
			pq = new PriorityQueue<>();
			pq.offer(new Robot(0, 0, 0));
			
			bfs();
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void bfs() {
		while(!pq.isEmpty()) {
			Robot r = pq.poll();
			
			if(r.x == N-1 && r.y == N-1) {
				answer = r.time;
				break;
			}
			
			for(int i=0; i<4; i++) {
				int nx = r.x + dx[i];
				int ny = r.y + dy[i];
				
				if(!inRange(nx, ny)) continue;
				if(dist[nx][ny] <= dist[r.x][r.y] + map[nx][ny]) {
					continue;
				} else {
					dist[nx][ny] = dist[r.x][r.y] + map[nx][ny];
					pq.offer(new Robot(nx, ny, dist[nx][ny]));
				}
			}
		}
	}
	
	static public boolean inRange(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
