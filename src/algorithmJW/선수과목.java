package algorithmJW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 시간복잡도 : O(N+M)
 * 
 * 전략 
 * 1. 위상정렬 사용하기
 * 2. 자신을 선수과목으로 삼고있는 과목들을 graph로 저장
 * 3. 자신이 수료하기 위해 남은 선수과목 수를 int[]에 저장  (index = 과목번호, Value = 선수과목 수)
 * 
 * 개선점
 * 1. 학기 기준으로 반복하던 방식을 -> 선수과목수가 0이 되면 바로 다음학기로 넣기
 * 2. 선수과목 수료 학기 + 1 = 다음과목 식으로 정답을 도출
 *
 */
public class 선수과목 {

	static int N; // 과목 수
	static int M; // 선수과목 조건 수
	static int[] answer; // 수료한 학기를 담는 배열(출력할 정답)

	static int[] subjects; // 과목들 (Index = 과목번호, Value = 선수과목 수)
	static List<Integer>[] graph; // 자신을 선수과목으로 두고 있는 과목들을 담는 그래프

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(in.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		subjects = new int[N + 1];
		graph = new List[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine().trim());
			int prerequisite  = Integer.parseInt(st.nextToken());
			int subject  = Integer.parseInt(st.nextToken());

			graph[prerequisite].add(subject);
			subjects[subject]++;
		}

		
		answer = new int[N + 1];

		Deque<Integer> dq = new ArrayDeque<Integer>();
		
		// 선수과목이 없는 과목들을 꺼내 학기 1로 설정 및, 큐에 담기
		for (int i = 1; i <= N; i++) {
			if (subjects[i] == 0) {
				dq.offer(i);
				answer[i] = 1;
			}
		}
		
		// 큐에 담긴 과목들을 하나씩 순회하며 해당 과목을 선수과목으로 두고 있는 과목들의 선수과목수 -- , 만약 0이 되면 해당과목 학기 + 1 = 선수과목수가 0이된 과목의 학기
		while (!dq.isEmpty()) {
			int subject = dq.poll();

			for (int i = 0; i < graph[subject].size(); i++) {
				int next = graph[subject].get(i);
				subjects[next]--;
				
				if(subjects[next] == 0) {
					answer[next] = answer[subject] + 1;
					dq.offer(next);
				}
			}
			
		}

		for (int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(" ");
		}

		System.out.println(sb);
	}
}
