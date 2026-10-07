package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 1. 맵을 입력받으면서 가장 큰 값 찾기
 * 2. DFS?
 * 3. 본인 높이 + 공사가능높이 - 1 인 부분 만나면 공사하기..
 * 3-1. 했는지 안했는지 여부는 매개변수로 주면 될듯?
 * 
 */
public class SWEA1949 {
	
	static int N; // 지도 한변의 길이
	static int[][] map; // 지도
	static int K; // 공사하는 최대 깊이
	static int maxHeight;
	static int answer;
	static boolean[][] visited;
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1}; 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			maxHeight = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > maxHeight) maxHeight = map[i][j];
				}
			}
			
			Deque<int[]> dq = new ArrayDeque<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == maxHeight) dq.offer(new int[] {i, j});
				}
			}
			answer = 1;
			visited = new boolean[N][N];
			while(!dq.isEmpty()) {
				int[] target = dq.poll();
				visited[target[0]][target[1]] = true;
				dfs(target, 1, false, map[target[0]][target[1]]);
				visited[target[0]][target[1]] = false;
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public void dfs(int[] target, int cnt, boolean isDig, int num) {
		
		if(cnt > answer) answer = cnt;
		
		for(int i=0; i<4; i++) {
			int x = target[0];
			int y = target[1];
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(!inRange(nx, ny)) continue;
			if(visited[nx][ny]) continue;
			if(num <= map[nx][ny]) {
				if(!isDig && map[nx][ny] < (num + K)) {
					visited[nx][ny] = true;
					dfs(new int[] {nx, ny}, cnt+1, !isDig, num-1);
					visited[nx][ny] = false;
				} 
				continue;
			}
			visited[nx][ny] = true;
			dfs(new int[] {nx, ny}, cnt+1, isDig, map[nx][ny]);
			visited[nx][ny] = false;
		}
	}
	
	static public boolean inRange(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}
}
