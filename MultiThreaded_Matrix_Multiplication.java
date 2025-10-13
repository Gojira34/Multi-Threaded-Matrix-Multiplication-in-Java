/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.multithreaded_matrix_multiplication;
import java.util.Scanner;
import java.util.Random;

/**
 * In this project, you will implement a program that multiplies two square matrices using multi threading.
 * Instead of computing the entire result sequentially, you will create multiple threads where each thread computes a portion of the resulting matrix (for example, one row or a block of rows).
 * The parent thread will coordinate the work, start/join threads, and finally display the resulting matrix.
 * @author Nil Patel
 */

public class MultiThreaded_Matrix_Multiplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[][] matrixA;            // First input matrix
        int[][] matrixB;            // Second input matrix
        int[][] resMatrix;          // Result matrix A * B
        int size = 0;               // Dimension
        int randomMaxValue = 10;    // Max Random number value for matrix generation
        
        // Getting size for Matrix A
        System.out.print("Enter the size of squre Matrix: ");
        if (input.hasNextInt()) {
            size = input.nextInt();
            if (size <= 0) {
                System.out.print("Size must be greater than 0.");
            }
        } else {
            System.out.print("Invalid input. Please enter an integer.");
            input.next();
            return;
        }
        
        // Initialize matrices once we know the size
        matrixA = new int[size][size];
        matrixB = new int[size][size];
        resMatrix = new int[size][size];
        
        // Ask user how to fill matrices. manual or random
        System.out.print("Type '1' to manually enter matrices, or '2' for random: ");
        int choice = input.nextInt();
        
        // for Manual entry
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
        
        // for Random Entry
        else if (choice == 2) {
            System.out.print("What Max Value whould you like for the matrix to have: ");
            while (!input.hasNextInt()) {
                System.out.print("Please enter a positive integer: ");
                input.next();
                return;
            }
            randomMaxValue = input.nextInt();
            if (randomMaxValue < 1) randomMaxValue = 10;
            matrixA = randomMatrices(matrixA, randomMaxValue);
            matrixB = randomMatrices(matrixB, randomMaxValue);
        }
        
        else {
            System.out.println();
            return;
        }
        
        // Printing Matrix A
        System.out.println("Matrix A:");
        printMatrix(matrixA);
        
        // Printing Matrix B
        System.out.println("Matrix B:");
        printMatrix(matrixB);
        
        // Create worker threads
        Thread[] threads = new Thread[size];
       
        // create a worker that will calculate one row of resmatrix
        for (int i = 0; i < size; i++) {
            matrix_multiplication worker = new matrix_multiplication(resMatrix, i, matrixA, matrixB, size);
            threads[i] = new Thread(worker);
            threads[i].start();
        }
        
        // Wait for all threads to finish
        for (int i = 0; i < size; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Main thread interrupted while waiting for row threads.");
                e.printStackTrace();
            }
        }
        
        // Print the result matrix
        System.out.println("Result Matrix: ");
        printMatrix(resMatrix);

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
    
    // unused kept for learning
    static int[][] rowCopyMatrices (int[][] matrix, int rowToCopy) {
        int[][] matrixCopy = new int[1][matrix.length];
        
        for (int i = 0; i < matrix.length; i++) {
            matrixCopy[0][i] = matrix[rowToCopy][i];
        }
        return matrixCopy;
    }
    //unused kept for learning
    static int[][] colCopyMatrices (int[][] matrix, int colToCopy) {
        int[][] matrixCopy = new int[matrix.length][1];
        
        for (int i = 0; i < matrix.length; i++) {
            matrixCopy[i][0] = matrix[i][colToCopy];
        }
        return matrixCopy;
    }
}

/**
 * Runnable task that computes one row of the resulting matrix. Each thread runs
 * this class's run() method for its assigned row. This avoids data races since
 * each thread writes to a unique row.
 */
class matrix_multiplication implements Runnable {
    private final int[][] resMatrix;        // shared result matrix
    private final int rowToDo;              // which row this thread computes
    private final int[][] A;                // full matrixA
    private final int[][] B;                // full matrixB
    private final int n;                    // matrix size
    
    
    public matrix_multiplication(int[][] resMatrix, int rowToDo, int[][] A, int[][] B, int n) {
        this.resMatrix = resMatrix;
        this.rowToDo = rowToDo;
        this.A = A;
        this.B = B;
        this.n = n;
    }
    
    @Override
    public void run() {
        // For each column in B, compute the dot product of row(rowToDo) of A and column(i) of B
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += A[rowToDo][j] * B[j][i];
            }
            resMatrix[rowToDo][i] = sum;
        }
    }
}
