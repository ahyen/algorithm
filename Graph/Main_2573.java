import java.io.*;
import java.util.*;

public class Main_2573 {

	static int N, M;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int island = Integer.MAX_VALUE;
		int ans = 0;
		while(true){
			island = count();
			
			int cnt = 0;
			for(int i = 0; i < N; i++){
				for(int j = 0; j < M; j++){
					if(map[i][j] == 0) cnt++; 
				}
			}
			if(cnt == N * M){
				System.out.println("0");
				System.exit(0);
			}
			if(island > 1){
				System.out.println(ans);
				break;
			}
			ans++;
			melt();
		}
	}
	
	 private static void melt(){
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) System.arraycopy(map[i], 0, temp[i], 0, M);
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < M; j++){
				if(map[i][j] == 0) continue;
				int count = 0;
				for(int d = 0; d < 4; d++){
					int nx = i + dx[d];
					int ny = j + dy[d];
					if(nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] != 0) continue;
					count++;
				}
				temp[i][j] = (map[i][j] - count) > 0 ? map[i][j] - count : 0; 
			}
		}
		
		for(int i = 0; i < N; i++) System.arraycopy(temp[i], 0, map[i], 0, M);
		
		for(int i = 0; i < N; i++) System.out.println(Arrays.toString(map[i]));
		System.out.println();
	}

	 private static int count(){
		 check = new boolean[N][M];
		 int isIsland = 0;
		 for(int i = 0; i < N; i++){
			 for(int j = 0; j < M; j++){
				 if(map[i][j] != 0 && !check[i][j]){
					 dfs(i, j);
					 isIsland++;
				 }
			 }
		 }
		 
		 return isIsland;
	 }
	 
	 private static void dfs(int x, int y){
		 check[x][y] = true;
		 for(int d = 0; d < 4; d++){
			 int nx = x + dx[d];
			 int ny = y + dy[d];
			 if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			 if(map[nx][ny] != 0 && !check[nx][ny]) dfs(nx, ny);
			 
		 }
	 }
}
