package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class SWEA1227 {
	
	static int[] start;
	static int[] finish;
	static int answer;
	static int[][] map;
	static boolean[][] visited;
	static Deque<int[]> dq = new ArrayDeque<>();
	//우하좌상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<10; t++) {
			String T = in.readLine().trim();
			
			map = new int[100][100];
			visited = new boolean[100][100];
			answer = 0;
			
			for(int i=0; i<100; i++) {
				String arr = in.readLine().trim();
				for(int j=0; j<100; j++) {
					map[i][j] = arr.charAt(j) - '0';
					if(map[i][j] == 1) map[i][j] = -1;
					if(map[i][j] == 2) {
						start = new int[]{i,j};
					} else if(map[i][j] == 3) {
						finish = new int[] {i,j};
					}
				}
			}
			dq.offer(start);
			visited[start[0]][start[1]] = true;
			bfs();
			
			if(map[finish[0]][finish[1]] != 3) {
				answer = 1;
			} 
			
			sb.append("#").append(T).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void bfs() {
		while(!dq.isEmpty()) {
			int[] loc = dq.poll();
			for(int i=0; i<4; i++) {
				int nx = loc[0] + dx[i];
				int ny = loc[1] + dy[i];
				
				if(!inRange(nx, ny)) continue;
				if(map[nx][ny] == -1) continue;
				if(visited[nx][ny]) continue;
				
				visited[nx][ny] = true;
				dq.offer(new int[] {nx,ny});
				map[nx][ny] = map[loc[0]][loc[1]] + 1;
				
				if(nx == finish[0] && ny == finish[1]) break;
			}
		}
	}
	
	static public boolean inRange(int r, int c) {
		return (r>=0 && r<100 && c>=0 && c<100);
	}
}
