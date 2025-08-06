public class MatrixCalculator{
    public static void main(String[] args) {
        int[][] matrixA = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] matrixB = {
            {7, 8, 9},
            {10, 11, 12}
        };

        int[][] matrixC = {
            {1, 2},
            {3, 4},
            {5, 6}
        };

        System.out.println("矩陣 A：");
        printMatrix(matrixA);

        System.out.println("矩陣 B：");
        printMatrix(matrixB);

        System.out.println("矩陣 A + B：");
        int[][] sum = addMatrices(matrixA, matrixB);
        printMatrix(sum);

        System.out.println("矩陣 A × C：");
        int[][] product = multiplyMatrices(matrixA, matrixC);
        printMatrix(product);

        System.out.println("矩陣 A 的轉置：");
        int[][] transposeA = transposeMatrix(matrixA);
        printMatrix(transposeA);

        int max = findMax(matrixA);
        int min = findMin(matrixA);
        System.out.println("矩陣 A 中的最大值：" + max);
        System.out.println("矩陣 A 中的最小值：" + min);
    }

    // 矩陣加法
    public static int[][] addMatrices(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // 矩陣乘法
    public static int[][] multiplyMatrices(int[][] a, int[][] b) {
        int rowsA = a.length;
        int colsA = a[0].length;
        int rowsB = b.length;
        int colsB = b[0].length;

        if (colsA != rowsB) {
            throw new IllegalArgumentException("矩陣無法相乘：A 的欄數必須等於 B 的列數。");
        }

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }
    // 矩陣轉置
    public static int[][] transposeMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] transpose = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transpose[j][i] = matrix[i][j];
            }
        }
        return transpose;
    }
    // 尋找矩陣最大值
    public static int findMax(int[][] matrix) {
        int max = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }
    // 尋找矩陣最小值
    public static int findMin(int[][] matrix) {
        int min = matrix[0][0];
        for (int[] row : matrix) {
            for (int value : row) {
                if (value < min) {
                    min = value;
                }
            }
        }
        return min;
    }

    // 列印矩陣
    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
