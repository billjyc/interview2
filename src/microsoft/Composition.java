package microsoft;

import java.util.*;

/**
 * Created by billjyc on 2016/10/10.
 */
public class Composition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int N = sc.nextInt();
            String str = sc.next();
            int M = sc.nextInt();
            Set<String> invalidPairs = new HashSet<>();
            for(int i = 0; i < M; i++) {
                invalidPairs.add(sc.next());
            }

            int result = shortest(str, invalidPairs);
            System.out.println(result);
        }
    }

    public static int shortest(String str, Set<String> invalidPairs) {
        Queue<String> q = new LinkedList<>();
        q.offer(str);
        int result = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                String tmp = q.poll();
                boolean hasInvalid = false;
                for(int j = 0; j < tmp.length() - 1; j++) {
                    if(invalidPairs.contains(tmp.substring(j, j + 2))) {
                        q.offer(tmp.substring(0, j) + tmp.substring(j + 1));
                        if(j + 1 < tmp.length() - 1) {
                            q.offer(tmp.substring(0, j + 1) + tmp.substring(j + 2));
                        } else {
                            q.offer(tmp.substring(0, j + 1));
                        }
                        //result++;
                        hasInvalid = true;
                        break;
                    }
                }
                if(!hasInvalid) {
                    return result;
                }
            }

            result++;
        }
        return result;
    }
}
