import java.io.*;
import java.util.*;

public class Main_21610 {

	static int N, M, d, s;
	static int[][] map;
	static boolean[][] isCloud;
	static int[] di = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dj = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		isCloud = new boolean[N][N];
		isCloud[N - 1][0] = isCloud[N - 1][1] = isCloud[N - 2][0] = isCloud[N - 2][1] = true;
		
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(M-->0){
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			s %= N;
			moveCloud();
			rain();
			addWater();
			updateMap();
		}
		
		int ans = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				ans += map[i][j];
			}
		}
		System.out.println(ans);
	}

	private static void moveCloud() {
		boolean[][] temp = new boolean[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(!isCloud[i][j]) continue;
				int ni = i + s * di[d];
				int nj = j + s * dj[d];
				if(ni < 0) ni = ni + N;
				if(nj < 0) nj += N;
				
				if(ni >= N) ni -= N; if(nj >= N) nj -= N;
				temp[ni][nj] = true;
			}
		}
		for(int i = 0; i < N; i++) System.arraycopy(temp[i], 0, isCloud[i], 0, N);
		
	}

	private static void rain() {
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(!isCloud[i][j]) continue;
				map[i][j]++;
			}
		}
	}

	private static void addWater() {
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(!isCloud[i][j]) continue;
				int n = 0;
				for(int d = 2; d < 10; d += 2){
					int ni = i + di[d];
					int nj = j + dj[d];
					if(ni < 0 || nj < 0 || ni >= N || nj >= N) continue;
					if(map[ni][nj] > 0) n++;
				}
				map[i][j] += n;
			}
		}
	}

	private static void updateMap() {
		boolean[][] temp = new boolean[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(!isCloud[i][j] && map[i][j] >= 2){
					map[i][j] -= 2;
					temp[i][j] = true;
				}
			}
		}
		for(int i = 0; i < N; i++) System.arraycopy(temp[i], 0, isCloud[i], 0, N);
	}

}
