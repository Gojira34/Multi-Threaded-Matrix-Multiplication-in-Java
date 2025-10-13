/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.multithreaded_matrix_multiplication;
import java.util.Scanner;
import java.util.Random;

/**
 * In this project, you will implement a program that multiplies two square matrices using multithreading.
 * Instead of computing the entire result sequentially, you will create multiple threads where each thread computes a portion of the resulting matrix (for example, one row or a block of rows).
 * The parent thread will coordinate the work, start/join threads, and finally display the resulting matrix.
 * @author Nil Patel
 */


class matrix_multiplication implements Runnable {
    int[][] multiplicationMatrix;
    
    
    public matrix_multiplication(int[][] multiplicationMatrix) {
        this.multiplicationMatrix = multiplicationMatrix;
    }
    
    @Override
    public void run() {
        
    }
}

public class MultiThreaded_Matrix_Multiplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[][] matrixA;
        int[][] matrixB;
        int[][] resMatrix = new int[2][2];
        int size = 0;
        int randomMaxValue = 10;
        
        // Getting size for Matrix A
        System.out.print("Enter the size of Matrix A: ");
        if (input.hasNextInt()) {
            size = input.nextInt();
            if (size <= 0) {
                System.out.print("Size must be greater than 0.");
            }
        } else {
            System.out.print("Invalid input. Please enter an integer.");
            input.next();
        }
        
        matrixA = new int[size][size];
        
        // Getting size for Matrix B
        System.out.print("Enter the size of Matrix B: ");
        if (input.hasNextInt()) {
            size = input.nextInt();
            if (size <= 0) {
                System.out.print("Size must be greater than 0.");
            }
        } else {
            System.out.print("Invalid input. Please enter an integer.");
            input.next();
        }

        matrixB = new int[size][size];
        
        System.out.print("Type '1' to manually enter matrices, or '2' for random: ");
        int choice = input.nextInt();
        
        
        if (choice == 1) {
            System.out.print("Enter the Matrix A Values: ");
            for (int row = 0; row < matrixA.length; row++) {
                for (int col = 0; col < matrixA[row].length; col++) {
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter an integer: ");
                        input.next();
                    }
                    matrixA[row][col] = input.nextInt();
                }
            }

            System.out.print("Enter the Matrix B Values: ");
            for (int row = 0; row < matrixB.length; row++) {
                for (int col = 0; col < matrixB[row].length; col++) {
                    while (!input.hasNextInt()) {
                        System.out.print("Please enter an integer: ");
                        input.next();
                    }
                    matrixB[row][col] = input.nextInt();
                }
            }
        }
        
        else if (choice == 2) {
            System.out.print("What Max Value whould you like for the matrix to have: ");
            randomMaxValue = input.nextInt();
            
            matrixA = randomMatrices(matrixA, randomMaxValue);
            matrixB = randomMatrices(matrixB, randomMaxValue);
        }
        
        else {
            System.out.println();
        }

        
        // Printing Matrix A
        System.out.println("Matrix A:");
        printMatrix(matrixA);
        
        // Printing Matrix B
        System.out.println("Matrix B:");
        printMatrix(matrixB);
       
        
        
        for (int k = 0; k < matrixA.length; k++) {
            int[][] doneMatrixA;
            int[][] doneMatrixB;
            int[][] doneMatrix = new int[1][1];
            int temp = 0;

            doneMatrixA = colCopyMatrices(matrixA, k);
            System.out.println("Col Matrix: ");
            printMatrix(doneMatrixA);
            doneMatrixB = rowCopyMatrices(matrixB, k);
            System.out.println("Row Matrix: ");
            printMatrix(doneMatrixB);
            
            for (int i = 0; i < doneMatrixA.length;) {
                System.out.println("Starting i:" + i);
                for (int j = i; j <= i; j++){
                    System.out.println("Starting j:" + j);
                    doneMatrix[0][0] += doneMatrixA[j][0] * doneMatrixB[0][j];
                }
                printMatrix(doneMatrix);
                i++;
            }
        }
        
    }
    
    // meathod to print matrix
    static void printMatrix(int[][] M) {
        for (int row = 0; row < M.length; row++) {
            for(int col = 0; col < M[row].length; col++) {
                System.out.printf("%6d", M[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    
    // meathod to generate ramdom matrices
    static int[][] randomMatrices (int[][] matrix, int maxVal) {
        Random values = new Random();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                matrix[row][col] = values.nextInt(maxVal + 1);
            }
        }
        
        return matrix;
    }
    
    static int[][] rowCopyMatrices (int[][] matrix, int rowToCopy) {
        int[][] matrixCopy = new int[1][matrix.length];
        
        for (int i = 0; i < matrix.length; i++) {
            matrixCopy[0][i] = matrix[rowToCopy][i];
        }
        return matrixCopy;
    }
    
    static int[][] colCopyMatrices (int[][] matrix, int colToCopy) {
        int[][] matrixCopy = new int[matrix.length][1];
        
        for (int i = 0; i < matrix.length; i++) {
            matrixCopy[i][0] = matrix[i][colToCopy];
        }
        return matrixCopy;
    }
}
