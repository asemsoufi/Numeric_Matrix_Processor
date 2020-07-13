package processor;

import java.util.Scanner;

public class Main {

    private static int matrixAN, matrixAM, matrixBN, matrixBM;
    private static int[][] matrixA;
    private static int[][] matrixB;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // getting first matrix
        matrixAN = scanner.nextInt();
        matrixAM = scanner.nextInt();
        matrixA = getMatrix(matrixAN, matrixAM);
        // getting second matrix
        matrixBN = scanner.nextInt();
        matrixBM = scanner.nextInt();
        matrixB = getMatrix(matrixBN, matrixBM);

        if (matrixAN != matrixBN || matrixAM != matrixBM) {
            System.out.println("ERROR");
        } else {
            int[][] matrixSum = sumMatrices(matrixA, matrixB);
            for (int i = 0; i < matrixAN; i++) {
                for (int j = 0; j < matrixAM; j++) {
                    System.out.print(matrixSum[i][j] + " ");
                }
                System.out.println();
            }
        }

    }

    public static int[][] getMatrix(int n, int m) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner. nextInt();
            }
        }
        return matrix;
    }

    public static int[][] sumMatrices(int[][] a, int[][] b) {
        int[][] sum = new int[matrixAN][matrixAM];
        for (int i = 0; i < matrixAN; i++) {
            for (int j = 0; j < matrixAM; j++) {
                sum[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return sum;
    }
}
