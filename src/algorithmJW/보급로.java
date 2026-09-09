package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 보급로 {
	
	// 우 하 좌 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	static int[][] map;
	static PriorityQueue<RestoreCost> pq;
	static int N;
	static boolean[][] visited;
	
	static class RestoreCost implements Comparable<RestoreCost>{
		int r;
		int c;
		int cost;
		
		public RestoreCost(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(RestoreCost o) {
			return this.cost - o.cost;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				String r = in.readLine().trim();
				for(int j=0; j<N; j++) {
					map[i][j] = r.charAt(j) - '0';
				}
			}
			
			pq = new PriorityQueue<>();
			
			pq.offer(new RestoreCost(0, 0, 0));
			
			bfs();
			
			int answer = map[N-1][N-1];
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void bfs() {
		while(!pq.isEmpty()) {
			RestoreCost rc = pq.poll();
			
			int r = rc.r;
			int c = rc.c;
			
			for(int i=0; i<4; i++) {
				int nx = r + dx[i];
				int ny = c + dy[i];
				
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				
				map[nx][ny] += rc.cost;
				
				if(nx == N-1 && ny == N-1) {
					pq.clear();
					break;
				}
				
				pq.offer(new RestoreCost(nx, ny, map[nx][ny]));
			}
		}
	}
	
	static public boolean inRange(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}
}
