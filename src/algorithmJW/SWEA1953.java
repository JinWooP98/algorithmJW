package algorithmJW;

import java.io.*;
import java.util.*;

public class SWEA1953 {
	// A -> 
	static int N; // 맵의 가로길이
	static int M; // 맵의 세로길이
	static int[] start; // 시작좌표
	static int[][] map;
	static int time;
	static int answer;
	static boolean[][] visited;
	// 상하좌우 // 이동할때 바뀌는 좌표 x, y + 가는 방향
	static int[][] type1 = {{-1,0,1}, {1,0,2}, {0,-1,3}, {0,1,4}}; 
	// 상하
	static int[][] type2 = {{-1,0,1}, {1,0,2}};
	// 좌우
	static int[][] type3 = {{0,-1,3}, {0,1,4}};
	// 상우
	static int[][] type4 = {{-1,0,1}, {0,1,4}};
	// 하우
	static int[][] type5 = {{1,0,2}, {0,1,4}};
	// 하좌
	static int[][] type6 = {{1,0,2}, {0,-1,3}};
	// 상좌
	static int[][] type7 = {{-1,0,1}, {0,-1,3}};
	
	static int[][][] types = {null,type1,type2,type3,type4,type5,type6,type7};
	
	static class nowLocation {
		int x;
		int y;
		int type;
		
		nowLocation(int x, int y, int type){
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	static Deque<nowLocation> dqA;
	static Deque<nowLocation> dqB;
	
	// bfs로 순회하면 될듯?? 시간만큼 반복? q로는 안되는건가..? 그냥 q 2개 써야할듯..?
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			start = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			time = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dqA = new ArrayDeque<>();
			dqB = new ArrayDeque<>();
			
			dqA.offer(new nowLocation(start[0], start[1], map[start[0]][start[1]]));
			answer = 1;
			visited[start[0]][start[1]] = true;
			for(int i=1; i<time; i++) {
				bfs();
			}
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs() {
		if(!dqA.isEmpty()) {
			while(!dqA.isEmpty()) {
				nowLocation n = dqA.poll();
				
				int[][] type = types[n.type];
				int x = n.x;
				int y = n.y;
				
				for(int i=0; i<type.length; i++) {
					int nx = x + type[i][0];
					int ny = y + type[i][1];
					
					if(!inRange(nx, ny)) continue;
					if(visited[nx][ny]) continue;
					if(map[nx][ny] == 0) continue;
					if(!checkMove(type[i][2], map[nx][ny])) continue;
					visited[nx][ny] = true;
					dqB.offer(new nowLocation(nx, ny, map[nx][ny]));
					answer++;
				}
			}
		} else {
			while(!dqB.isEmpty()) {
				nowLocation n = dqB.poll();
				
				int[][] type = types[n.type];
				int x = n.x;
				int y = n.y;
				
				for(int i=0; i<type.length; i++) {
					int nx = x + type[i][0];
					int ny = y + type[i][1];
					
					if(!inRange(nx, ny)) continue;
					if(visited[nx][ny]) continue;
					if(map[nx][ny] == 0) continue;
					if(!checkMove(type[i][2], map[nx][ny])) continue;
					visited[nx][ny] = true;
					dqA.offer(new nowLocation(nx, ny, map[nx][ny]));
					answer++;
				}
			}
		}
	}
	
	public static boolean inRange(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
	public static boolean checkMove(int dir, int type) {
		if(dir == 1) {
			if(type == 1 || type == 2 || type == 5 || type == 6) return true;
		} else if (dir == 2) {
			if(type == 1 || type == 2 || type == 4 || type == 7) return true;
		} else if (dir == 3) {
			if(type == 1 || type == 3 || type == 4 || type == 5) return true;
		} else if (dir == 4) {
			if(type == 1 || type == 3 || type == 6 || type == 7) return true;
		}
		
		return false;
	}
}
