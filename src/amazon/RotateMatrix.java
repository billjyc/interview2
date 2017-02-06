package amazon;

/**
 * Created by billjyc on 2017/1/19.
 */
public class RotateMatrix {
    public int[][] rotate(int[][] matrix, int flag) {
        if(matrix == null || matrix.length == 0) {
            return matrix;
        }
        int n = matrix.length, m = matrix[0].length;
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                res[i][j] = matrix[j][i];
            }
        }

        if(flag == 1) {  //right rotate
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n / 2; j++) {
                    int temp = res[i][j];
                    res[i][j] = res[i][n - 1 - j];
                    res[i][n - 1 - j] = temp;
                }
            }
        } else { //left rotate
            for(int i = 0; i < m / 2; i++) {
                for(int j = 0; j < n; j++) {
                    int temp = res[i][j];
                    res[i][j] = res[m - 1 - i][j];
                    res[m - 1 - i][j] = temp;
                }
            }
        }
        return res;
    }

    public void printMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        RotateMatrix rm = new RotateMatrix();
        int[][] input = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] output1 = rm.rotate(input, 0);
        int[][] output2 = rm.rotate(input, 1);
        System.out.println("input");
        rm.printMatrix(input);
        System.out.println("output1");
        rm.printMatrix(output1);
        System.out.println("output2");
        rm.printMatrix(output2);
    }
}
