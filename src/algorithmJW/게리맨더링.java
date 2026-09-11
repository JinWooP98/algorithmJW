package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 게리맨더링 {

	static int N;
	static int[] area;
	static int[][] graph;
	static boolean[] selected;
	static Deque<Integer> Dq;
	static int answer;

	/**
	 * 
	 * 전략
	 * 1. Graph를 만들어 연결되어 있는 노드들 저장
	 * 2. DFS로 가능한 조합 완전 탐색(Selected True 그룹, false 그룹으로 나눔)
	 * 3. BFS로 각 그룹들이 연결되어있는지 확인
	 * 
	 * 코드 개선점
	 * 1. selected로 해당 노드가 그룹에 속해있는지 판별하기.. (Array.contains() 사용하면 O(N) => O(1))
	 * 2. List를 만들 필요 없지 가장 첫번째 노드만 가져오고... 그걸 기준으로 bfs로 연결된 노드 찾기
	 * 3. 연결되있는지 확인하는 메서드에 매개변수로 true false를 넣어, 코드 간소화
	 * 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(in.readLine().trim());
		area = new int[N + 1];
		answer = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		for (int i = 1; i <= N; i++) {
			area[i] = Integer.parseInt(st.nextToken());
		}

		graph = new int[N + 1][];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine().trim());
			int connectedNum = Integer.parseInt(st.nextToken());

			graph[i] = new int[connectedNum];

			for (int j = 0; j < connectedNum; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		selected = new boolean[N + 1];

		
		
		dfs(1);
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}

	static public void dfs(int depth) {
		if (depth == N + 1) {
			// 각 구역들이 연결되어있는지 확인
			if(checkConnect(true) && checkConnect(false)) {
				int aNum = 0;
				int bNum = 0;
				for(int i=1; i<=N; i++) {
					if(selected[i]) {
						aNum += area[i];
					} else {
						bNum += area[i];
					}
				}
				if(answer > Math.abs(aNum - bNum)) answer = Math.abs(aNum - bNum);
			}
			
			return;
		}

		selected[depth] = true;
		dfs(depth + 1);
		selected[depth] = false;
		dfs(depth + 1);
	}

	static public boolean checkConnect(boolean groupType) {
		Dq = new ArrayDeque<Integer>();

		int Group = -1;
		boolean[] visited = new boolean[N + 1];

		for(int i=1; i<=N; i++) {
			if(selected[i] == groupType) {
				Group = i;
				break;
			}
		}

		if (Group == -1) {
			return false;
		}

		Dq.offer(Group);
		visited[Group] = true;

		while (!Dq.isEmpty()) {

			int n = Dq.poll();

			for (int i = 0; i < graph[n].length; i++) {
				int next = graph[n][i];
				if (selected[next] == groupType) {

					if (visited[next])
						continue;

					visited[next] = true;
					Dq.offer(next);
				}
			}
		}
		for (int i = 1; i <= N; i++) {
	        if (selected[i] == groupType && !visited[i]) {
	            return false;
	        }
	    }
		
		return true;
	}
}
