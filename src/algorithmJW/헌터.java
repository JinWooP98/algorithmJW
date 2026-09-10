package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 음수  = 고객, 양수 = 몬스터
public class 헌터 {
	
	static int N;
	static int[][] map;
	static List<Creature> creatures;
	static boolean[] catched;
	static boolean[] visited;
	static int answer;
	
	static class Creature {
		int r;
		int c;
		int type;
		
		public Creature(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			
			map = new int[N][N];
			creatures = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					
					if(map[i][j] != 0) {
						creatures.add(new Creature(i, j, map[i][j]));
					}
				}
			}
			
			catched = new boolean[creatures.size()/2 + 1];
			visited = new boolean[creatures.size()];
			answer = 1000000;
			
			dfs(0, 0, 0, 0);
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth, int time, int r, int c) {
		
		if(time >= answer) return;
		
		if(depth == creatures.size()) {
			if(time < answer) {
				answer = time;
			}
			return;
		}
		
		for(int i=0; i<creatures.size(); i++) {
			
			if(visited[i]) continue;
			
			Creature target = creatures.get(i);
			
			if(target.type < 0) {
				if(!catched[Math.abs(target.type)]) continue;
				
				visited[i] = true;
				
				dfs(depth+1, time + getMoveTime(target, r, c), target.r, target.c);
				
				visited[i] = false;
			} else {
				
				visited[i] = true;
				catched[target.type] = true;
				
				dfs(depth+1, time + getMoveTime(target, r, c), target.r, target.c);
				
				visited[i] = false;
				catched[target.type] = false;
			}
		}
	}
	
	static int getMoveTime(Creature target, int r, int c) {
		return (Math.abs(target.r - r) + Math.abs(target.c - c));
	}
}
