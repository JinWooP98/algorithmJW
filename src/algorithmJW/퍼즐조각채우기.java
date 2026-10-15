package algorithmJW;

import java.io.*;
import java.util.*;

/*
* 회전 (r, c) -> (c, -r) 90도 회전, r값이 -라면 c,r에 r을 더해줌
* 도형의 경우 (c, -r)이 여러개 이므로 가장 작은 -r값을 구해 더해주기
* 
* 전략
* 1. 보드와 테이블을 순회하여 보드 빈칸 좌표, 크기 및 도형 좌표, 크기 구하기... (크기는 좌표 담는 list 길이로 할까?..)
* 2. 도형으로 보드 빈칸을 하나씩 순회하기
* 2-1. 크기가 다르면 continue;
* 2-2. 가로길이 세로길이가 다르다면 continue; (가로 == 가로 && 세로 == 세로 || 가로 == 세로 && 세로 == 가로)
* 2-3. 정규화하여 0,0에 최대한 가깝게 만들고 비교, 원본, 90도 회전, 180도 회전, 270도 회전을 하여 비교하기
* 
* 추가
* 도형과 빈칸을 크기로 정렬하기..? List를 우선순위큐에 넣고 비교? 그냥 정렬 사용
* blocks.sort(Comparator.comparingInt(List::size));
* blocks.sort((a,b) -> Integer.compare(a.size(), b.size()))
*/

public class 퍼즐조각채우기 {
	static boolean[][] visited;
    static int[][] game_board;
    static int[][] table;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static Deque<int[]> dq;

    public int solution(int[][] game_board, int[][] table) {

    	퍼즐조각채우기.game_board = game_board;
    	퍼즐조각채우기.table = table;

        dq = new ArrayDeque<>();
        
        int answer = 0;

        List<List<int[]>> boardList = new ArrayList<>();
        List<List<int[]>> tableList = new ArrayList<>();

        visited = new boolean[table.length][table.length];

        // 게임판 빈 공간 탐색
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table.length; j++){

                if(game_board[i][j] == 1) continue;
                if(visited[i][j]) continue;

                visited[i][j] = true;
                dq.offer(new int[]{i,j});

                boardList.add(bfs(true));
            }
        }

        visited = new boolean[table.length][table.length];

        // 퍼즐 조각 탐색
        for(int i=0; i<table.length; i++){
            for(int j=0; j<table.length; j++){

                if(table[i][j] == 0) continue;
                if(visited[i][j]) continue;

                visited[i][j] = true;
                dq.offer(new int[]{i,j});

                tableList.add(bfs(false));
            }
        }
        
        boardList.sort((a,b) -> Integer.compare(a.size(), b.size()));
        tableList.sort((a,b) -> Integer.compare(a.size(), b.size()));
        
        boolean[] selected = new boolean[boardList.size()];
        
        for(int i=0; i<tableList.size(); i++) {
        	boolean isIn = true;
        	for(int j=0; j<boardList.size(); j++) {
        		if(selected[j]) continue;
        		List<int[]> t = tableList.get(i);
                List<int[]> b = boardList.get(j);

                if(t.size() > b.size()) continue;
                if(t.size() < b.size()) break;

                // 세로 길이
                int tr = t.get(t.size()-1)[0] - t.get(0)[0] + 1;
                int br = b.get(b.size()-1)[0] - b.get(0)[0] + 1;

                // 가로 길이
                int tc = 0;
                int bc = 0;

                for(int[] pos : t) {
                    tc = Math.max(tc, pos[1] + 1);
                }

                for(int[] pos : b) {
                    bc = Math.max(bc, pos[1] + 1);
                }

                // 가로 세로 크기 비교
                if(!((tr == br && tc == bc) || (tr == bc && tc == br))) continue;
                
                for(int k=0; k<4; k++) {
                	isIn = true;
                	for(int c=0; c<t.size(); c++) {
                		if(!(t.get(c)[0] == b.get(c)[0] && t.get(c)[1] == b.get(c)[1])) {
                			isIn = false;
                			break;
                		}
                	}
                	
                	if(!isIn) {
                		rotateShape(t);
                		continue;
                	} else {
                		selected[j] = true;
                		answer += t.size();
                		break;
                	}
                }
                if(isIn) break;
        	}
        	
        }

        return answer; // 이후 정답 계산 구현
    }

    static public List<int[]> bfs(boolean type){

        List<int[]> nowBoardList = new ArrayList<>();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        while(!dq.isEmpty()){

            int[] arr = dq.poll();
            nowBoardList.add(arr);

            if(minX > arr[0]) minX = arr[0];
            if(minY > arr[1]) minY = arr[1];

            for(int i=0; i<4; i++){

                int nx = arr[0] + dx[i];
                int ny = arr[1] + dy[i];

                if(!inRange(nx,ny)) continue;
                if(visited[nx][ny]) continue;

                if(type && game_board[nx][ny] != 0) continue;
                if(!type && table[nx][ny] != 1) continue;

                visited[nx][ny] = true;
                dq.offer(new int[]{nx, ny});
            }
        }

        // 좌표 정규화
        for(int i=0; i<nowBoardList.size(); i++){
            nowBoardList.get(i)[0] -= minX;
            nowBoardList.get(i)[1] -= minY;
        }
        
        // 좌표 정렬
        nowBoardList.sort((a, b) -> {
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });


        return nowBoardList;
    }

    static public boolean inRange(int x, int y){
        return (x >= 0 && x < table.length && y >= 0 && y < table.length);
    }
    
    static void rotateShape(List<int[]> block) {
        // 도형 90도 회전
    	
    	int minX = Integer.MAX_VALUE;
    	int minY = Integer.MAX_VALUE;
    	
    	for(int i=0; i<block.size(); i++) {
    		int r = block.get(i)[0];
    		int c = block.get(i)[1];
    		
    		if(minX > c) minX = c;
    		if(minY > -r) minY = -r;
    		
    		block.get(i)[0] = c;
    		block.get(i)[1] = -r;
    	}
    	
    	for(int i=0; i<block.size(); i++) {
    		block.get(i)[0] -= minX;
    		block.get(i)[1] -= minY;
    	}
    	
    	block.sort((a, b) -> {
            if(a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
    }
}
