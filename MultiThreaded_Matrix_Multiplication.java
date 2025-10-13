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
        
        int[][] matrixA;
        int[][] matrixB;
        int[][] resMatrix;
        int size = 0;
        int randomMaxValue = 10;
        
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
        
        matrixA = new int[size][size];
        matrixB = new int[size][size];
        resMatrix = new int[size][size];
        
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
            return;
        }
        
        // Printing Matrix A
        System.out.println("Matrix A:");
        printMatrix(matrixA);
        
        // Printing Matrix B
        System.out.println("Matrix B:");
        printMatrix(matrixB);
        
        Thread[] threads = new Thread[size];
       
        int row = 0;
        for (int o = 0; o < matrixA.length; o++) {
            for (int i = 0; i < matrixA.length; i++) {
                int[][] doneMatrixA;
                int[][] doneMatrixB;
                int temp = 0;

                doneMatrixA = rowCopyMatrices(matrixA, row);
                doneMatrixB = colCopyMatrices(matrixB, i);
                matrix_multiplication thread = new matrix_multiplication(resMatrix, row, doneMatrixA, doneMatrixB);
                threads[i] = new Thread(thread);
                threads[i].start();
            }
            row++;
        }
        
        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
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

class matrix_multiplication implements Runnable {
    private final int[][] resMatrix;
    private final int rowToDo;
    private final int[][] rowMatrix;
    private final int[][] colMatrix;
    
    
    public matrix_multiplication(int[][] resMatrix, int rowToDo, int[][] rowMatrix, int[][] colMatrix) {
        this.resMatrix = resMatrix;
        this.rowToDo = rowToDo;
        this.rowMatrix = rowMatrix;
        this.colMatrix = colMatrix;
    }
    
    @Override
    public void run() {
        
    }
}
