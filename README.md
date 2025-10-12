# Multi-Threaded-Matrix-Multiplication-in-Java

# Project Description
Personal Project.
A program that multiplies two square matrices using multithreading.

# Program Structure
1. MultiThreaded_Matrix_Multiplication (Main Class)
   - Reads inputs for the size of matrices.
   - Generates or accepts two random integer matrices of size N*N.
   - Creates threads (for each row or block of rows).
   - Waits for threads to complete and then prints the result.

2. MultiplyThread (Worker Class)
   - Implements Runnable.
   - Responsible for computing one row (or block) of result matric.
