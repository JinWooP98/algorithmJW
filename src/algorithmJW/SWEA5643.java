package algorithmJW;

import java.io.*;
import java.util.*;

public class SWEA5643 {
	
	static int N;
	static int M;
	
	static List<Integer>[] shorter;
	static List<Integer>[] taller;
	static boolean[] visited;
	
	static Deque<Integer> dq = new ArrayDeque<>();
	
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			
			N = Integer.parseInt(in.readLine().trim());
			M = Integer.parseInt(in.readLine().trim());
			
			shorter = new List[N+1];
			taller = new List[N+1];
			
			for(int i=1; i<=N; i++) {
				shorter[i] = new ArrayList<>();
				taller[i] = new ArrayList<>();
			}
			
			StringTokenizer st;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int sm = Integer.parseInt(st.nextToken());
				int ta = Integer.parseInt(st.nextToken());
				
				shorter[ta].add(sm);
				taller[sm].add(ta);
			}
			
			answer = 0;
			
			for(int i=1; i<=N; i++) {
				visited = new boolean[N+1];
				int sCnt = bfs(false, i);
				visited = new boolean[N+1];
				int tCnt = bfs(true, i);
				
				if(sCnt + tCnt == N -1) {
					answer++;
				}
			}
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public int bfs(boolean type, int n) {
		dq.clear();
		dq.offer(n);
		
		int cnt = 0;
		
		List<Integer>[] target = (type) ? taller : shorter;
		visited[n] = true;
		while(!dq.isEmpty()) {
			int idx = dq.poll();
			
			for(int i=0; i<target[idx].size(); i++) {
				
				if(visited[target[idx].get(i)]) continue;
				
				visited[target[idx].get(i)] = true;
				cnt++;
				dq.offer(target[idx].get(i));
			}
		}
		
		return cnt;
	}
}
