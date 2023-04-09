import java.io.*;
import java.util.*;

public class Main_16987 {
    
    static class Egg{
        int S, W;

        public Egg(int S, int W){
            this.S = S;
            this.W = W;
        }
    }

    static Egg[] eggs;
    static int N, ans;

    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(S, W);
        }

        ans = 0;
        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int i, int cnt){
        // 마지막 계란이면 종료
        if(i == N){
            ans = Math.max(cnt, ans);
            return;
        }

        Egg egg = eggs[i];
        // 손에 든 계란이 깨졌거나, 모든 계란이 깨졌으면 다음 계란으로 넘어감
        if(egg.S <= 0 || cnt == N - 1){
            dfs(i + 1, cnt);
            return;
        }

        int n = cnt;
        for(int j = 0; j < N; j++){
            // 자기 자신이면 통과
            if(j == i) continue;
            // 이미 깨진 계란이면 통과
            if(eggs[j].S <= 0) continue;

            // 손에 든 계란 내구성 감소
            egg.S -= eggs[j].W;
            // 부딪힌 계란 내구성 감소
            eggs[j].S -= egg.W;

            // 손에 든 계란 깨졌으면 cnt++
            if(egg.S <= 0) cnt++;
            // 부딪힌 계란 깨졌으면 cnt++
            if(eggs[j].S <= 0) cnt++;

            // 다음 계란 들기
            dfs(i + 1, cnt);

            // 데이터 원상 복구
            egg.S += eggs[j].W;
            eggs[j].S += egg.W;
            cnt = n;
        }
    }


}

