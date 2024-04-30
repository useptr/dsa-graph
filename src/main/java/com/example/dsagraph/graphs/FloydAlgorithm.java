package com.example.dsagraph.graphs;

import java.util.ArrayList;
import java.util.List;

public class FloydAlgorithm<T> {
    private SimpleGraph<T> graph;
    private Vertex<T> result;
    public FloydAlgorithm(SimpleGraph<T> graph) {
        this.graph = graph;
        this.result = eval();
    }
    public void set(SimpleGraph<T> graph) {
        this.graph = graph;
        restart();
    }
    public FloydAlgorithm(FloydAlgorithm<T> another) {
        this.graph = another.graph;
        this.result = another.result;
        restart();
    }
    public void restart() {
        result = eval();
    }
    public Vertex<T> result() {
        return result;
    }
    private Vertex<T> eval() {
        int size = this.graph.vertices();
        double[][] dist = new double[size][size];
        double INF = Double.MAX_VALUE;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                } else {
                    double weight = INF;
                    Edge<T> edge = graph.get(i, j);
                    if (edge != null)
                        weight = edge.weight();
                    dist[i][j] = weight;
                }
            }
        }

        double[] sums = new double[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sums[i] += dist[i][j];
            }
        }

        double minSum = INF;
        int center = -1;
        for (int i = 0; i < size; i++) {
            if (sums[i] < minSum) {
                minSum = sums[i];
                center = i;
            }
        }
        return graph.get(center);
    }

}
