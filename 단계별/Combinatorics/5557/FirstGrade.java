/*
문제

상근이가 1학년 때, 덧셈, 뺄셈을 매우 좋아했다. 
상근이는 숫자가 줄 지어있는 것을 보기만 하면, 마지막 두 숫자 사이에 '='을 넣고, 나머지 숫자 사이에는 '+' 또는 '-'를 넣어 등식을 만들며 놀고 있다. 
 예를 들어, "8 3 2 4 8 7 2 4 0 8 8"에서 등식 "8+3-2-4+8-7-2-4-0+8=8"을 만들 수 있다.
상근이는 올바른 등식을 만들려고 한다. 
상근이는 아직 학교에서 음수를 배우지 않았고, 20을 넘는 수는 모른다. 
따라서, 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하이어야 한다. 
 예를 들어, "8+3+2-4-8-7+2+4+0+8=8"은 올바른 등식이지만, 8+3+2-4-8-7이 음수이기 때문에, 상근이가 만들 수 없는 등식이다.
숫자가 주어졌을 때, 상근이가 만들 수 있는 올바른 등식의 수를 구하는 프로그램을 작성하시오.

입력

첫째 줄에 숫자의 개수 N이 주어진다. (3 ≤ N ≤ 100) 
둘째 줄에는 0 이상 9 이하의 정수 N개가 공백으로 구분해 주어진다.

출력

첫째 줄에 상근이가 만들 수 있는 올바른 등식의 개수를 출력한다. 이 값은 2^63-1 이하이다.
*/

import java.io.*;
import java.util.*;

public class FirstGrade {
    public static void main(String[] args) throws IOException {
        
        System.setIn(new FileInputStream("test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            num[i] = Integer.parseInt(st.nextToken());
        
        long[][] dp = new long[N+1][21];
        dp[1][num[0]] = 1;
        for(int i=2; i<=N; i++){
            for(int j=0; j<=20; j++){
                if(dp[i-1][j] > 0){
                    if(j+num[i-1]<=20)
                        dp[i][j+num[i-1]] += dp[i-1][j];
                    if(j-num[i-1]>=0)
                        dp[i][j-num[i-1]] += dp[i-1][j];
                }
            }
        }
        bw.write(dp[N-1][num[N-1]] + "\n");
        bw.close();
    }
}