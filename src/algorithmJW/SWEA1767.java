package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA1767 {

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int answer;
	static List<Core> cores;
	static int maxLinkedCore;

	// 우 하 좌 상
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static class Core {
		int r;
		int c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine().trim());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(in.readLine().trim());

			map = new int[N][N];
			visited = new boolean[N][N];
			cores = new ArrayList<>();
			maxLinkedCore = 0;
			answer = 0;

			StringTokenizer st;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
						if (map[i][j] == 1) {
							cores.add(new Core(i, j));
						}
					}
				}
			}

			dfs(0, 0, 0);

			sb.append("#").append(t + 1).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static public void dfs(int depth, int linkedNum, int wire) {
		
		if (depth == cores.size()) {
			if (maxLinkedCore < linkedNum) {
				maxLinkedCore = linkedNum;
				answer = wire;
			} else if (maxLinkedCore == linkedNum) {
				if (answer > wire)
					answer = wire;
			}
			return;
		}

		Core core = cores.get(depth);

		dfs(depth + 1, linkedNum, wire);

		for (int j = 0; j < 4; j++) {
			int nr = core.r;
			int nc = core.c;
			boolean isConnected = false;
			int wireNum = 0;
			while (true) {
				nr += dx[j];
				nc += dy[j];

				if (!inRange(nr, nc)) {
					isConnected = true;
					break;
				}

				if (visited[nr][nc])
					break;

				if (map[nr][nc] == 1)
					break;

				visited[nr][nc] = true;

				wireNum++;
			}

			if (isConnected) {
				dfs(depth + 1, linkedNum + 1, wire + wireNum);
			}

			int rr = core.r;
			int rc = core.c;
			for(int k=0; k<wireNum; k++) {
				rr += dx[j];
				rc += dy[j];
				
				visited[rr][rc] = false;
			}
		}

	}

	static public boolean inRange(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}
}
