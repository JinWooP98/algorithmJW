package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA7733 {
	
	static int N;
	static int maxNum;
	static int answer;
	static int[][] cheese;
	static boolean[][] visited;
	static Deque<int[]> dq = new ArrayDeque<>();
	// 우하좌상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T= Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			
			N = Integer.parseInt(in.readLine().trim());
			answer = 1;
			cheese = new int[N][N];
			maxNum = 0;
			
			StringTokenizer st;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<N; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
					
					if(cheese[i][j] > maxNum) maxNum = cheese[i][j];
				}
			}
			
			for(int i=1; i<maxNum; i++) {
				visited = new boolean[N][N];
				int num = 0;
				for(int j=0; j<N; j++) {
					for(int k=0; k<N; k++) {
						if(visited[j][k]) continue;
						if(cheese[j][k] <= i) continue;
						visited[j][k] = true;
						dq.offer(new int[] {j,k});
						bfs(i);
						num++;
					}
				}
				if(num > answer) answer = num;
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void bfs(int day) {
		while(!dq.isEmpty()) {
			int[] loc = dq.poll();
			for(int i=0; i<4; i++) {
				int nx = loc[0] + dx[i];
				int ny = loc[1] + dy[i];
				
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny])continue;
				if(cheese[nx][ny] <= day) continue;
				
				visited[nx][ny] = true;
				dq.offer(new int[] {nx,ny});
			}
		}
	}
	
	static public boolean inRange(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}
}

//성일
//일단 day를 100까지가 아니라 maxNum 뽑아서하면 더 조금 돌긴 할듯? Math.max 안쓰는거 위주로, 저번에 이거 있고 없고로 통과여부 갈림(시간초과)
// 실행시간 50ms 차이가 이것들 때문인듯?
