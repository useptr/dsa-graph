package org.example;

import org.example.graphs.ListGraph;

public class Main {
    public static void main(String[] args) {

        ListGraph<Integer> graph = new ListGraph<>(false, false);
        System.out.println("Здарова пашугандр");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}