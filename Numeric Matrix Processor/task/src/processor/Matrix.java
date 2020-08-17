package processor;

import java.util.Scanner;

public class Matrix {

    private final int n;
    private final int m;
    private double[][] elements;
    private double determinant;

    // constructor
    public Matrix(int n, int m) {
        this.n = n;
        this.m = m;
        this.elements = new double[n][m];
        /* initiate all elements to 0.0
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.m; j++) {
                this.elements[i][j] = 0.0;
            }
        }*/
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

    public double getDeterminant() {
        if (n == 1) {
            return elements[0][0];
        }
        if (n == 2) {
            return elements[0][0] * elements[1][1] - elements[0][1] * elements[1][0];
        }
        for (int k = 0; k < n; k++) {
            determinant += elements[0][k] * this.getCofactor(1, k + 1);
        }
        return determinant;
    }

    public double getCofactor(int k, int l) {
        Matrix smallerMatrix = new Matrix(n - 1, n - 1);
        int x = 0;
        int y = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i != k - 1 && j != l - 1) {
                    smallerMatrix.elements[x][y] = elements[i][j];
                    y++;
                }
            }
            if (y > 0) {
                x++;
            }
            y = 0;
        }
        return Math.pow(-1, k + l) * smallerMatrix.getDeterminant();
    }

    public static Matrix identityMatrix(int n, int m) {
        Matrix temp = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == j) {
                    temp.elements[i][j] = 1.0;
                }
            }
        }
        return temp;
    }

    public Matrix cofactorsMatrix() {
        Matrix temp = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp.elements[i][j] = getCofactor(i + 1, j + 1);
            }
        }
        return temp;
    }

    public Matrix adjacencyMatrix() {
        return transposeDiagonal(this);
    }

    public Matrix inverseMatrix() {
        return timesMatrix(this.cofactorsMatrix().adjacencyMatrix(), 1 / this.getDeterminant());
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

    public static void getTranspositionChoice() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Vertical line\n" +
                "4. Horizontal line\n" +
                "0. Back to main menu\n");
        System.out.print("Your choice: ");
        Scanner scanner = new Scanner(System.in);
        String transpositionChoice = scanner.next();
        switch (transpositionChoice) {
            case "1":
                Matrix originalMatrix1 = Matrix.getNewMatrix();
                System.out.println("The transposition relatively main diagonal result is:");
                System.out.println(Matrix.transposeDiagonal(originalMatrix1));
                getUserSelection();
                break;
            case "2":
                Matrix originalMatrix2 = Matrix.getNewMatrix();
                System.out.println("The transposition relatively side diagonal result is:");
                System.out.println(Matrix.transposeSideDiagonal(originalMatrix2));
                getUserSelection();
                break;
            case "3":
                Matrix originalMatrix3 = Matrix.getNewMatrix();
                System.out.println("The transposition by a vertical line result is:");
                System.out.println(Matrix.transposeVertical(originalMatrix3));
                getUserSelection();
                break;
            case "4":
                Matrix originalMatrix4 = Matrix.getNewMatrix();
                System.out.println("The transposition by a horizontal line result is:");
                System.out.println(Matrix.transposeHorizontal(originalMatrix4));
                getUserSelection();
                break;
            default:
                getUserSelection();
        }
    }

    public static void getUserSelection() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        switch (selection) {
            case "1":
                // get first matrix
                Matrix matrix1 = Matrix.getNewMatrix();
                // get second matrix
                Matrix matrix2 = Matrix.getNewMatrix();
                // calculate and print the multiplication of the two matrices
                System.out.println("The addition result is:");
                System.out.println(Matrix.sumMatrices(matrix1, matrix2));
                getUserSelection();
                break;
            case "2":
                Matrix matrix12 = Matrix.getNewMatrix();
                // get a number to multiply with
                double number = scanner.nextDouble();
                // calculate and print the multiplication of the matrix by the number
                System.out.println("The multiplication result is:");
                System.out.println(Matrix.timesMatrix(matrix12, number));
                getUserSelection();
                break;
            case "3":
                Matrix matrix13 = Matrix.getNewMatrix();
                // get second matrix
                Matrix matrix23 = Matrix.getNewMatrix();
                // calculate and print the multiplication of the two matrices
                System.out.println("The multiplication result is:");
                System.out.println(Matrix.multiplyMatrices(matrix13, matrix23));
                getUserSelection();
                break;
            case "4":
                Matrix.getTranspositionChoice();
                break;
            case "5":
                // get first matrix
                Matrix matrix15 = Matrix.getNewMatrix();
                // calculate and print the multiplication of the two matrices
                System.out.println("The determinant is:");
                System.out.println(matrix15.getDeterminant());
                getUserSelection();
                break;
            case "6":
                // get original matrix
                Matrix matrix16 = Matrix.getNewMatrix();
                // get and print inverse matrix
                System.out.println(matrix16.inverseMatrix());
                // get next user selection
                getUserSelection();
                break;
            case "0":
                return;
            default:
                getUserSelection();
        }
    }



    public String toString() {
        String matrixToString = "";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrixToString += String.format("%f ",elements[i][j]);
            }
            matrixToString += "\n";
        }
        return matrixToString;
    }
/*
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double[][] getElements() {
        return elements;
    }

    public void setElements(double[][] elements) {
        this.elements = elements;
    }

 */
}