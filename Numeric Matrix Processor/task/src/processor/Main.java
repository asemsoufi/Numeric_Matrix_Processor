package processor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        getUserSelection();
    }

    public static void getUserSelection() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix to a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
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
                switch (Matrix.getTranspositionChoice()) {
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
                        Matrix.getTranspositionChoice();
                }
            case "0":
                return;
            default:
                getUserSelection();
        }
    }
}
