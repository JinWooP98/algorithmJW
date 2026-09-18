package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA5648 {
	
	static int N;
	static Atom[] atoms;
	static boolean[] crashed;
	static PriorityQueue<Crash> pq = new PriorityQueue<>();
	static int answer;
	static int[] crashTime;
	
	
	static class Crash implements Comparable<Crash>{
		int a;
		int b;
		int time;
		
		Crash(int a, int b, int time){
			this.a = a;
			this.b = b;
			this.time = time;
		}

		@Override
		public int compareTo(Crash o) {
			// TODO Auto-generated method stub
			return this.time - o.time;
		}
	}
	
	static class Atom{
		int x;
		int y;
		int dir;
		int power;
		
		Atom(int x, int y, int dir, int power){
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.power = power;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			atoms = new Atom[N+1];
			crashed = new boolean[N+1];
			crashTime = new int[N+1];
			
			StringTokenizer st;
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				atoms[i] = new Atom(
							Integer.parseInt(st.nextToken()) * 2, // x좌표
							Integer.parseInt(st.nextToken()) * 2, // y좌표
							Integer.parseInt(st.nextToken()), // 이동방향
							Integer.parseInt(st.nextToken()) // 보유에너지
						);
			}
			answer = 0;
			dfs(1);
			
			while(!pq.isEmpty()) {
				Crash crash = pq.poll();
				
				if(!crashed[crash.a] && !crashed[crash.b]) {
					crashed[crash.a] = true;
					crashed[crash.b] = true;
					
					crashTime[crash.a] = crash.time;
					crashTime[crash.b] = crash.time;
				
					answer += (atoms[crash.a].power + atoms[crash.b].power);
				} else if(crashed[crash.a] || crashed[crash.b]) {
					if(crashed[crash.a]) {
						if(crashTime[crash.a] == crash.time && !crashed[crash.b]) {
							crashed[crash.b] = true;
							crashTime[crash.b] = crash.time;
							answer += atoms[crash.b].power;
						}
					} else if(crashed[crash.b]) {
						if(crashTime[crash.b] == crash.time && !crashed[crash.a]) {
							crashed[crash.a] = true;
							crashTime[crash.a] = crash.time;
							answer += atoms[crash.a].power;
						}
					}
				}
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int depth) {
		if(depth == N) {
			return;
		}
		Atom atom1 = atoms[depth];
		for(int i=depth+1; i<=N; i++) {
			Atom atom2 = atoms[i];
			
			if(atom1.dir == atom2.dir) continue;
			
			if(atom1.dir == 0) {
				if(atom1.y > atom2.y) continue;
			} else if(atom1.dir == 1) {
				if(atom1.y < atom2.y) continue;
			} else if(atom1.dir == 2) {
				if(atom1.x < atom2.x) continue;
			} else if(atom1.dir == 3) {
				if(atom1.x > atom2.x) continue;
			}
			
			if(atom1.dir == 0 || atom1.dir == 1) {
				if(atom1.x > atom2.x) {
					if(atom2.dir != 3)continue;
				} else if (atom1.x < atom2.x) {
					if(atom2.dir != 2)continue;
				} else {
					if(atom1.dir == 0) {
						if(atom2.dir != 1) continue;
					} else {
						if(atom2.dir != 0) continue;
					}
				}
				
				int[] crashLoc = {atom1.x, atom2.y};
				if(atom1.x == atom2.x) crashLoc = new int[]{atom1.x, (atom1.y+atom2.y)/2};
				int time1 = getDistance(atom1, crashLoc);
				int time2 = getDistance(atom2, crashLoc);
				
				if(time1 == time2) {
					pq.offer(new Crash(depth, i, time1));
				}
				
			} else {
				if(atom1.y > atom2.y) {
					if(atom2.dir != 0) continue;
				} else if (atom1.y < atom2.y) {
					if(atom2.dir != 1) continue;
				} else {
					if(atom1.dir == 2) {
						if(atom2.dir != 3) continue;
					} else {
						if(atom2.dir != 2) continue;
					}
				}
				
				int[] crashLoc = {atom2.x, atom1.y};
				if(atom1.y == atom2.y) crashLoc = new int[]{(atom1.x+atom2.x)/2, atom1.y};
				int time1 = getDistance(atom1, crashLoc);
				int time2 = getDistance(atom2, crashLoc);
				
				if(time1 == time2) {
					pq.offer(new Crash(depth, i, time1));
				}
			}
		}
		dfs(depth+1);
	}
	
	static public int getDistance(Atom atom, int[] crashLoc) {
		return (Math.abs(crashLoc[0] - atom.x) + Math.abs(crashLoc[1] - atom.y));
	}
	
}











