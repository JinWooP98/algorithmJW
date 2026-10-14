package algorithmJW;

import java.io.*;
import java.util.*;

public class SWEA2105 {
	
	static int N; // 맵 크기
	static int[][] map;
	// 좌하, 우하, 우상, 좌상
	static int[] dx = { 1, 1, -1, -1 };
	static int[] dy = { -1, 1, 1, -1 };
	
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine().trim());
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(in.readLine().trim());
			map = new int[N][N];
			
			StringTokenizer st;
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Set<Integer> bucket = new HashSet<>();
			
			answer = -1;
			for(int i=0; i<N-2; i++) {
				for(int j=1; j<N-1; j++) {
					
					for(int k=j; k>0; k--) {
						for(int c=1; c<N-(i+k); c++) {
							
							bucket.clear();
							
							bucket.add(map[i][j]);
							
							int nx = i;
							int ny = j;
							
							for(int m=1; m<=k; m++) {
								nx += dx[0];
								ny += dy[0];
								if(!inRange(nx,ny))continue;
								bucket.add(map[nx][ny]);
							}
							
							if(bucket.size() != 1+k)continue;
							
							for(int m=1; m<=c; m++) {
								nx += dx[1];
								ny += dy[1];
								if(!inRange(nx,ny))continue;
								bucket.add(map[nx][ny]);
							}
							
							if(bucket.size() != 1+k+c)continue;
							
							for(int m=1; m<=k; m++) {
								nx += dx[2];
								ny += dy[2];
								if(!inRange(nx,ny))continue;
								bucket.add(map[nx][ny]);
							}
							
							if(bucket.size() != 1+k+c+k)continue;
							
							for(int m=1; m<=c; m++) {
								nx += dx[3];
								ny += dy[3];
								if(!inRange(nx,ny))continue;
								bucket.add(map[nx][ny]);
							}
							
							if(nx != i || ny != j) continue;
							if(bucket.size() != k+c+k+c)continue;
							
							if(answer < bucket.size()) answer = bucket.size();
							
							
						}
					}
					
				}
			}
			
			sb.append("#").append(t+1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static public boolean inRange(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<N);
	}
}
