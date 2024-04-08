package org.example;

import org.example.graphs.Graph;
import org.example.graphs.ListGraph;
import org.example.graphs.SimpleGraph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {

        SimpleGraph<Integer> listGraph = new SimpleGraph<>(4, false, Graph.Type.LIST_GRAPH);
        System.out.println(listGraph);

        SimpleGraph<Integer> matrixGraph = new SimpleGraph<>(4, false, Graph.Type.MATRIX_GRAPH);
        System.out.println(matrixGraph);

    }
}