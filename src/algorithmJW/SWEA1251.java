package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import jdk.internal.org.jline.utils.InputStreamReader;

public class SWEA1251 {
	
	static int N; // 섬 개수
	static int[][] islands;
	static double tex;
	static int[] nodes;
	
	static public class Bridge implements Comparable<Bridge>{
		int a; // 섬 1번
		int b; // 섬 2번
		double cost; // 다리 건설 비용
		
		Bridge(int a, int b, double cost){
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Bridge o) {
			// TODO Auto-generated method stub
			return Double.compare(this.cost, o.cost);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			
			nodes = new int[N+1];
			
			islands = new int[N+1][];
			
			StringTokenizer x = new StringTokenizer(in.readLine().trim());
			StringTokenizer y = new StringTokenizer(in.readLine().trim());
			
			tex = Double.parseDouble(in.readLine().trim());
			
			for(int i=1; i<=N; i++) {
				islands[i] = new int[] {Integer.parseInt(x.nextToken()), Integer.parseInt(y.nextToken())};
				nodes[i] = i;
			}
			
			PriorityQueue<Bridge> pq = new PriorityQueue<>();
			
			for(int i=1; i<N; i++) {
				for(int j=i+1; j<=N; j++) {
					int dx = islands[i][0] - islands[j][0];
					int dy = islands[i][1] - islands[j][1];
					double len = Math.sqrt(dx*dx + dy * dy);
					double c = tex * (len * len);
					pq.offer(new Bridge(i,j,c));
				}
			}
			
			int n = 0;
			
			double answer = 0;
			
			while(n != N-1){
				Bridge bg = pq.poll();
				
				if(!union(bg.a, bg.b)) {
					continue;
				}
				
				answer += bg.cost;
				n++;
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean union(int a, int b) {
		
		a = find(a);
		b = find(b);
		
		if(a != b) {
			nodes[b] = a;
			return true;
		}
		
		return false;
	}
	
	public static int find(int a) {
		if(nodes[a] == a) {
			return a;
		}
		
		return nodes[a] = find(nodes[a]);
	}
}
