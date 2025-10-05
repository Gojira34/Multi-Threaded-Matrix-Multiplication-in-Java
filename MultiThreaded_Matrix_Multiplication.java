/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.multithreaded_matrix_multiplication;
import java.util.Scanner;

/**
 * In this project, you will implement a program that multiplies two square matrices using multithreading.
 * Instead of computing the entire result sequentially, you will create multiple threads where each thread computes a portion of the resulting matrix (for example, one row or a block of rows).
 * The parent thread will coordinate the work, start/join threads, and finally display the resulting matrix.
 * @author Nil Patel
 */

/*
class matrix_multiplication implements Runnable {
    
}
*/
public class MultiThreaded_Matrix_Multiplication {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        int[][] matrixA;    
        int[][] matrixB;
        int size = 0;
        
        System.out.print("Enter the size of Matrix A: ");
        if (input.hasNext()) {
            size = input.nextInt();
            if (size <= 0) {
                System.out.print("Size must be greater than 0.");
            }
        } else {
            System.out.print("Invalid input. Please enter an integer.");
            input.next();
        }
        
        matrixA = new int[size][size];
        
        System.out.print("Enter the Matrix A Values: ");
        for (int i = 0; i < matrixA.length; i++) {
            for(int j = 0; j < matrixA[i].length; j++) {
                while (!input.hasNextInt()) {
                    System.out.print("Please enter an integer: ");
                    input.next();
                }
                matrixA[i][j] = input.nextInt();
            }
        }
        
        for (int i = 0; i < matrixA.length; i++) {
            for(int j = 0; j < matrixA[i].length; j++) {
                System.out.print(matrixA[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
