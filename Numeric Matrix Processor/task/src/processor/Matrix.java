package processor;

import java.util.Scanner;

public class Matrix {

    public int n;
    public int m;
    public double[][] elements;

    // constructor
    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        // initiate all elements to 0.0
        this.elements = new double[n][m];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.elements[i][j] = 0.0;
            }
        }
    }

    public void setElements() {
        //System.out.print("Enter matrix elements: ");
        Scanner scanner = new Scanner(System.in);
        double[][] matrix = new double[this.n][this.m];
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        this.elements = matrix;
    }

    public static Matrix getNewMatrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter size of new matrix: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Matrix tempMatrix = new Matrix(n, m);
        System.out.println("Enter matrix elements:");
        tempMatrix.setElements();
        return tempMatrix;
    }

    public double[] getRow(int rowNumber) {
        if (rowNumber < 1 || rowNumber > n) {
            System.out.println("Invalid row number!");
            return null;
        }
        double[] temp = new double[m];
        for (int i = 0; i < m; i++) {
            temp[i] = elements[rowNumber - 1][i];
        }
        return temp;
    }

    public double[] getColumn(int columnNumber) {
        if (columnNumber < 1 || columnNumber > m) {
            System.out.println("Invalid column number!");
            return null;
        }
        double[] temp = new double[n];
        for (int i = 0; i < n; i++) {
            temp[i] = elements[i][columnNumber - 1];
        }
        return temp;
    }

    public double[] getDiagonal() {
        double[] temp = new double[m];
        for (int i = 0; i < m; i++) {
            temp[i] = elements[i][i];
        }
        return temp;
    }

    public double[] getSideDiagonal() {
        double[] temp = new double[m];
        for (int i = 0; i < m; i++) {
            temp[i] = elements[m - 1 - i][i];
        }
        return temp;
    }

    public static Matrix sumMatrices(Matrix a, Matrix b) {
        if (a.n != b.n || a.m != b.m) {
            System.out.println("The two matrices can't be summed-up!");
            return null;
        }
        double[][] sum = new double[a.n][a.m];
        for (int i = 0; i < a.n; i++) {
            for (int j = 0; j < a.m; j++) {
                sum[i][j] = a.elements[i][j] + b.elements[i][j];
            }
        }
        Matrix sumMatrix = new Matrix(a.n, a.m);
        sumMatrix.elements = sum;
        return sumMatrix;
    }

    public static Matrix timesMatrix(Matrix matrix, double number) {
        double[][] times = new double[matrix.n][matrix.m];
        for (int i = 0; i < matrix.n; i++) {
            for (int j = 0; j < matrix.m; j++) {
                times[i][j] = matrix.elements[i][j] * number;
            }
        }
        Matrix timesMatrix = new Matrix(matrix.n, matrix.m);
        timesMatrix.elements = times;
        return timesMatrix;
    }

    public static Matrix multiplyMatrices(Matrix firstMatrix, Matrix secondMatrix) {
        if (firstMatrix.m != secondMatrix.n) {
            System.out.println("These two matrices can't be multiplied!");
            return null;
        }
        Matrix multiplicationMatrix = new Matrix(firstMatrix.n, secondMatrix.m);
        for (int i = 0; i < firstMatrix.n; i++) {
            for (int j = 0; j < secondMatrix.m; j++) {
                double[] thisRow = firstMatrix.getRow(i + 1);
                double[] thisColumn = secondMatrix.getColumn(j + 1);
                for (int k = 0; k < thisRow.length; k++) {
                    multiplicationMatrix.elements[i][j] += thisRow[k] * thisColumn[k];
                }
            }
        }
        return multiplicationMatrix;
    }

    public static Matrix transposeDiagonal(Matrix originalMatrix) {
        Matrix tempMatrix = new Matrix(originalMatrix.n, originalMatrix.m);
        for (int i = 0; i < originalMatrix.n; i++) {
            for (int j = i; j < originalMatrix.n; j++) {
                tempMatrix.elements[i][j] = originalMatrix.elements[j][i];
                tempMatrix.elements[j][i] = originalMatrix.elements[i][j];
            }
        }
        return tempMatrix;
    }

    public static Matrix transposeSideDiagonal(Matrix originalMatrix) {
        Matrix tempMatrix = new Matrix(originalMatrix.n, originalMatrix.m);
        for (int i = 0; i < originalMatrix.n; i++) {
            for (int j = i; j < originalMatrix.n; j++) {
                tempMatrix.elements[i][j] = originalMatrix.elements[originalMatrix.n - 1 - j][originalMatrix.n - 1 - i];
                tempMatrix.elements[j][i] = originalMatrix.elements[originalMatrix.n - 1 - i][originalMatrix.n - 1 - j];
            }
        }
        return tempMatrix;
    }

    public static Matrix transposeVertical(Matrix originalMatrix) {
        Matrix tempMatrix = new Matrix(originalMatrix.n, originalMatrix.m);
        for (int i = 0; i < originalMatrix.n; i++) {
            for (int j = 0; j < originalMatrix.n; j++) {
                tempMatrix.elements[j][i] = originalMatrix.elements[j][originalMatrix.m - 1 - i];
                tempMatrix.elements[j][originalMatrix.m - 1 - i] = originalMatrix.elements[j][i];
            }
        }
        return tempMatrix;
    }

    public static Matrix transposeHorizontal(Matrix originalMatrix) {
        Matrix tempMatrix = new Matrix(originalMatrix.n, originalMatrix.m);
        for (int i = 0; i < originalMatrix.n; i++) {
            for (int j = 0; j < originalMatrix.n; j++) {
                tempMatrix.elements[j][i] = originalMatrix.elements[originalMatrix.n - 1 - j][i];
                tempMatrix.elements[originalMatrix.n - 1 - j][i] = originalMatrix.elements[j][i];
            }
        }
        return tempMatrix;
    }

    public static String getTranspositionChoice() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "0. Back to main menu\n");
        System.out.print("Your choice: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public String toString() {
        String matrixToStrin = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrixToStrin += String.format("%f ",elements[i][j]);
            }
            matrixToStrin += "\n";
        }
        return matrixToStrin;
    }
}
