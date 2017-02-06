package google;

/**
 * Created by billjyc on 2016/10/20.
 * given a 2d n*n 2d array, which represents light on or off, writing a function
 * to compute the next state of all lights
 *
 * 8 neighbors per light, 2 on -> hold, 3 on -> on, otherwise -> off
 */
public class TurningLight {
    //0 off, 1 on
    public void nextState(int [][] lights) {
        if(lights == null || lights.length == 0 || lights[0].length == 0) {
            return;
        }

        int n = lights.length;
        int m = lights[0].length;

        int[][] next = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                int nextState = 0;
                //upper-left
                if(i > 0 && j > 0) {
                    nextState += lights[i-1][j-1];
                }
                //upper
                if(i > 0) {
                    nextState += lights[i-1][j];
                }
                //upper-right
                if(i > 0 && j < m - 1) {
                    nextState += lights[i-1][j+1];
                }
                //left
                if(j > 0) {
                    nextState += lights[i][j-1];
                }
                //right
                if(j < m - 1) {
                    nextState += lights[i][j+1];
                }
                //bottom-left
                if(i < n - 1 && j > 0) {
                    nextState += lights[i+1][j-1];
                }
                //bottom
                if(i < n - 1) {
                    nextState += lights[i+1][j];
                }
                //bottom-right
                if(i < n - 1 && j < m - 1) {
                    nextState += lights[i+1][j+1];
                }
                if(nextState == 2) {
                    next[i][j] = lights[i][j];
                } else if(nextState == 3) {
                    next[i][j] = 1;
                } else {
                    next[i][j] = 0;
                }
            }
        }
    }
}
