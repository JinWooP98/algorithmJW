package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1267 {
	
	static int N; // 작업수
	static int M; // 조건 수
	static int[] works;
	static List<Integer>[] graph;
	static int[] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<10; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			works = new int[N+1];
			graph = new List[N+1];
			
			for(int i=1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			st = new StringTokenizer(in.readLine().trim());
			for(int i=0; i<M; i++) {
				int work1 = Integer.parseInt(st.nextToken());
				int work2 = Integer.parseInt(st.nextToken());
				
				graph[work1].add(work2);
				works[work2]++;
			}
			
			Deque<Integer> dq = new ArrayDeque<>();
			
			answer = new int[N];
			int index = 0;
			
			for(int i=1; i<=N; i++) {
				if(works[i] == 0) dq.offer(i);
			}
			
			while(!dq.isEmpty()) {
				int work = dq.poll();
				
				answer[index++] = work;
				
				for(int i=0; i<graph[work].size(); i++) {
					works[graph[work].get(i)]--;
					if(works[graph[work].get(i)] == 0) {
						dq.offer(graph[work].get(i));
					}
				}
				
			}
			
			sb.append("#").append(t+1).append(" ");
			
			for(int i=0; i<N; i++) {
				sb.append(answer[i]).append(" ");
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
