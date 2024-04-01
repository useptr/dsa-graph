package org.example;

import org.example.graphs.MatrixGraph;

public class Main {
    public static void main(String[] args) {
        MatrixGraph mGraph = new MatrixGraph<>();
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}