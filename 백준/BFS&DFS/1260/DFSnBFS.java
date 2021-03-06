/*
문제

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 
정점 번호는 1번부터 N번까지이다.

입력

첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 
다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 
어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 
입력으로 주어지는 간선은 양방향이다.

출력

첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. 
V부터 방문된 점을 순서대로 출력하면 된다.
*/
import java.io.*;
import java.util.*;

public class DFSnBFS {
    public static int N, M, V;
    public static ArrayList<Integer>[] adj;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {

        System.setIn(new FileInputStream("Test/test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        for(int i=0;i<=N;i++)
            adj[i] = new ArrayList<>();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        for(int i=0;i<=N;i++)
            Collections.sort(adj[i]);
        visited = new boolean[N+1];
        dfs(V);
        System.out.println();
        visited = new boolean[N+1];
        bfs();
    }
    public static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        for(int i : adj[v])
            if(!visited[i])
                dfs(i);
    }
    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(V);
        visited[V] = true;
        while(!q.isEmpty()){
            int v = q.poll();
            System.out.print(v + " ");
            for(int i : adj[v]){
                if(!visited[i]){
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }
}