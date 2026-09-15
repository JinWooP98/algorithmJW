package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * 1. 지뢰 = -1로 설정 및 주변 숫자 +1로 하며 맵 지정
 * 2. 맵을 순회하며 0이 나오는 경우 큐에 넣고 bfs로 0클릭시 한번에 열리는 구역 구하기
 * 3. 열리지 않는 구역 수를 answer에 더해주기
 *
 */
public class SWEA1868 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	// 상 하 좌 우 상좌 상우 하좌 하우
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1};
	
	static int answer;
	
	static Deque<int[]> dq = new ArrayDeque<>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			
			map = new int[N][N];
			visited = new boolean[N][N];
			answer = 0;
			// 맵에 지도 정보 담으며 지뢰 위치, 주변 폭탄개수 설정
			for(int i=0; i<N; i++) {
				String line = in.readLine().trim();
				for(int j=0; j<N; j++) {
					char c = line.charAt(j);
					if(c == '*') {
						map[i][j] = -1;
						visited[i][j] = true;
						for(int k=0; k<8; k++) {
							int nx = i + dx[k];
							int ny = j + dy[k];
							
							if(!inRange(nx, ny)) continue;
							if(map[nx][ny] == -1) continue;
							map[nx][ny]++;
						}
					}
				}
			}
			// map에서 0인 지점을 찾고 이미 visited된 곳이 아니면 그곳을 기준으로 bfs로 같이 열리는 곳 찾기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j])continue;
					if(map[i][j] != 0)continue;
					
					visited[i][j] = true;
					dq.offer(new int[] {i, j});
					bfs();
					answer++;
				}
			}
			// 0이 아니면서 방문되지 않은곳만 남았으므로 그 개수만큼 answer++
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!visited[i][j])answer++;
				}
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
			
		}
		System.out.println(sb);
	}
	// bfs로 0인 구역을 클릭시 같이 열리는 구역 찾는 목적
	static public void bfs() {
		while(!dq.isEmpty()) {
			int[] node = dq.poll();
			
			for(int i=0; i<8; i++) {
				int nx = node[0] + dx[i];
				int ny = node[1] + dy[i];
				
				if(!inRange(nx, ny)) continue;
				if(visited[nx][ny])continue;
				
				if(map[nx][ny]==0) {
					dq.offer(new int[] {nx,ny});
				}
				
				visited[nx][ny] = true;
			}
		}
	}
	
	static public boolean inRange(int r, int c) {
		return (r>=0 && r<N && c>=0 && c<N);
	}
}
