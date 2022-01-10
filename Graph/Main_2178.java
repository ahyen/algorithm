import java.io.*;
import java.util.*;

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class Main_2178{
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M, cnt = 1;
    static int[][] map;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }
        bfs();
    }
    
    private static void bfs(){
        ArrayDeque<Point> q = new ArrayDeque<Point>();
        boolean[][] v = new boolean[N][M];
        v[0][0] = true;
        q.offer(new Point(0, 0));
        
        while(!q.isEmpty()){
        
        	int num = q.size();
        	for(int n = 0; n < num; n++){
	            Point cur = q.poll();
	            for(int d = 0; d < 4; d++){
	                int nx = cur.x + dx[d];
	                int ny = cur.y + dy[d];
	                if(nx < 0 || nx >= N || ny < 0 || ny >= M || v[nx][ny] || map[nx][ny] == 0) continue;
	                if(nx == N - 1 && ny == M - 1){
	                	System.out.println(cnt + 1);
	                	System.exit(0);
	                }
	                v[nx][ny] = true;
	                q.offer(new Point(nx, ny));
	            }
        	}
        	cnt++;
        }
    }
}