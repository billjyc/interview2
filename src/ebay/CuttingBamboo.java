package ebay;

import java.util.*;

/**
 * Created by billjyc on 2016/10/25.
 */
public class CuttingBamboo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] nums = new int[T];
        for(int i = 0; i < T; i++) {
            int N = sc.nextInt();
            nums[i] = N;
        }

        for(int i = 0; i < T; i++) {
            System.out.println(cuttingBamboos(nums[i]));
        }
    }

    public static int cuttingBamboos(int N) {
        int[] dp = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Set<Integer> edges = new HashSet<>();
        return helper(N, dp, visited, edges);
    }

    int rst = 0;
    public static int helper(int N, int[] dp, boolean[] visited, Set<Integer> edges) {
//        if(visited[N]) {
//            return dp[N];
//        }
        if(N == 0) {
           return edges.size();
        }
//        if(N == 1) {
//            return 1;
//        } else if(N == 2) {
//            return 1;
//        } else if(N == 3 || N == 4 || N == 5) {
//            return 2;
//        }
//        visited[N] = true;
        for(int i = 1; i <= N; i++) {
            if(!edges.contains(i) && isNotTriangle((edges))) {
                edges.add(i);
                dp[N] = Math.max(dp[N], helper(N - i, dp, visited, edges));
                edges.remove(i);
            }
        }

        return dp[N];
    }

    public static boolean isNotTriangle(Set<Integer> edges) {
        if(edges.size() < 3) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        list.addAll(edges);
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++) {
            for(int j = 1; j < list.size(); j++) {
                for(int k = 2; k < list.size(); k++) {
                    if(list.get(i) + list.get(j) > list.get(k)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
