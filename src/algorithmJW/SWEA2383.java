package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA2383 {

	static int N;
	static int[][] map;

	static List<Room> rooms;
	static Stairs[] stairs;

	static boolean[] visited;
	static int answer;

	static PriorityQueue<Room> pq1 = new PriorityQueue<>();
	static PriorityQueue<Room> pq2 = new PriorityQueue<>();

	static class Room implements Comparable<Room> {
		int r;
		int c;
		int time;

		public Room(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		@Override
		public int compareTo(Room o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}

	
	}

	static class Stairs {
		int r;
		int c;
		int len;

		public Stairs(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine().trim());

			map = new int[N][N];
			stairs = new Stairs[2];
			int sIdx = 0;
			rooms = new ArrayList<>();

			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						rooms.add(new Room(i, j, 0));
					} else if (map[i][j] >= 2) {
						stairs[sIdx++] = new Stairs(i, j, map[i][j]);
					}
				}
			}

			visited = new boolean[rooms.size()];
			answer = Integer.MAX_VALUE;
			dfs(0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static public void dfs(int depth) {
		if (depth == rooms.size()) {
			// 여기에 이제 거리 구하는거 하면 될듯?
			checkTime();
			return;
		}

		visited[depth] = true;
		dfs(depth + 1);
		visited[depth] = false;
		dfs(depth + 1);

	}

	static public void checkTime() {

		int[] stair1 = new int[3];
		int[] stair2 = new int[3];

		for (int i = 0; i < rooms.size(); i++) {
			if (visited[i]) {
				int time = Math.abs(stairs[0].r - rooms.get(i).r) + Math.abs(stairs[0].c - rooms.get(i).c);
				rooms.get(i).setTime(time+1);
				pq1.offer(rooms.get(i));
			} else {
				int time = Math.abs(stairs[1].r - rooms.get(i).r) + Math.abs(stairs[1].c - rooms.get(i).c);
				rooms.get(i).setTime(time+1);
				pq2.offer(rooms.get(i));
			}
		}
		
		int time = 1;
		while (!pq1.isEmpty() || !pq2.isEmpty()) {
			if(time > answer) {
				pq1.clear();
				pq2.clear();
				break;
			}
			
			
			for(int j=0; j<3; j++) {
				if(stair1[j] == time) stair1[j] = 0;
				if(stair2[j] == time) stair2[j] = 0;
			}
			
			for (int j = 0; j < 3; j++) {
				if (!pq1.isEmpty() && pq1.peek().time <= time) {
					if (stair1[j] == 0) {
						pq1.poll();
						stair1[j] = time + stairs[0].len;
					}
				} else {
					break;
				}
			}
			for (int j = 0; j < 3; j++) {
				if (!pq2.isEmpty() && pq2.peek().time <= time) {
					if (stair2[j] == 0) {
						pq2.poll();
						stair2[j] = time + stairs[1].len;
					}
				} else {
					break;
				}
			}
			time++;
		}
		
		for(int j=0; j<3; j++) {
			if(stair1[j] > time) time = stair1[j];
			if(stair2[j] > time) time = stair2[j];
		}
		
		if(answer > time) answer = time;
	}
}
