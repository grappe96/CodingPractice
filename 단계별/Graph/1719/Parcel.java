/*
문제

명우기업은 2008년부터 택배 사업을 새로이 시작하기로 하였다. 
우선 택배 화물을 모아서 처리하는 집하장을 몇 개 마련했지만, 택배 화물이 각 집하장들 사이를 오갈 때 어떤 경로를 거쳐야 하는지 결정하지 못했다. 
어떤 경로를 거칠지 정해서, 이를 경로표로 정리하는 것이 여러분이 할 일이다.
예시된 그래프에서 굵게 표시된 1, 2, 3, 4, 5, 6은 집하장을 나타낸다. 
정점간의 간선은 두 집하장간에 화물 이동이 가능함을 나타내며, 가중치는 이동에 걸리는 시간이다. 
이로부터 얻어내야 하는 경로표는 다음과 같다.
경로표는 한 집하장에서 다른 집하장으로 최단경로로 화물을 이동시키기 위해 가장 먼저 거쳐야 하는 집하장을 나타낸 것이다. 
예를 들어 4행 5열의 6은 4번 집하장에서 5번 집하장으로 최단 경로를 통해 가기 위해서는 제일 먼저 6번 집하장으로 이동해야 한다는 의미이다.
이와 같은 경로표를 구하는 프로그램을 작성하시오.

입력

첫째 줄에 두 수 n과 m이 빈 칸을 사이에 두고 순서대로 주어진다. 
n은 집하장의 개수로 200이하의 자연수, m은 집하장간 경로의 개수로 10000이하의 자연수이다. 
이어서 한 줄에 하나씩 집하장간 경로가 주어지는데, 두 집하장의 번호와 그 사이를 오가는데 필요한 시간이 순서대로 주어진다. 
집하장의 번호들과 경로의 소요시간은 모두 1000이하의 자연수이다.

출력

예시된 것과 같은 형식의 경로표를 출력한다.
*/

import java.io.*;
import java.util.*;

public class Parcel {
    public static int[][] map;
    public static int n;
    public static void main(String[] args) throws IOException {
        
        System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];

        for(int i=0;i<m;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            map[u][v] = w;
            map[v][u] = w;
        }
        for(int i=1; i<=n; i++)
            bw.write(dijkstra(i) + "\n");
        bw.close();
    }
    public static String dijkstra(int s) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        int INF = Integer.MAX_VALUE;
        int[] time = new int[n+1];
        int[] sta = new int[n+1];

        Arrays.fill(time, INF);
        time[s] = 0;
        sta[s] = s;
        pQueue.add(s);
        
        while(!pQueue.isEmpty()){
            int tmp = pQueue.poll();
            for(int i=1; i<=n; i++){
                if(map[tmp][i] == 0)
                    continue;
                if(time[i] > map[tmp][i]+time[tmp]){
                    time[i] = map[tmp][i]+time[tmp];
                    sta[i] = tmp;
                    pQueue.add(i);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            if(i == s)
                sb.append("- ");
            else{
                int t = i;
                for(int j=sta[i];j!=s;j=sta[j])
                    t = j;
                sb.append(t + " ");
            }
        }
        return sb.toString();
    }
}