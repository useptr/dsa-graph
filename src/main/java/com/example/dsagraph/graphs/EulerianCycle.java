package com.example.dsagraph.graphs;

import java.util.ArrayList;
import java.util.List;

public class EulerianCycle<T> {
    private SimpleGraph<T> graph;
    private List<List<Integer>> result;
    public EulerianCycle(SimpleGraph<T> graph) {
        this.graph = graph;
        this.result = eval();
    }
    public void set(SimpleGraph<T> graph) {
        this.graph = graph;
        restart();
    }
    public EulerianCycle(EulerianCycle<T> another) {
        this.graph = another.graph;
        this.result = another.result;
        restart();
    }
    public void restart() {
        result = eval();
    }
    public List<List<Integer>> result() {
        return result;
    }
    private List<List<Integer>> eval() {
        int vertices = this.graph.vertices();
        List<Integer>[] graph = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (this.graph.get(i, j) != null)
                    graph[i].add(j);
            }
        }

        List<Integer> circuit = new ArrayList<>();
        eulerianCycle(graph, 0, circuit);
        // Формирование списка рёбер из цикла
        return edgesFromCycle(circuit);
    }
    private  void eulerianCycle(List<Integer>[] graph, int u, List<Integer> cycle) {
        while (!graph[u].isEmpty()) {
            int v = graph[u].remove(0);
            graph[v].remove(Integer.valueOf(u)); // Удаляем обратное ребро
            eulerianCycle(graph, v, cycle);
        }
        cycle.add(u);
    }

    // Функция для формирования списка рёбер из Эйлерова цикла
    private static List<List<Integer>> edgesFromCycle(List<Integer> circuit) {
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < circuit.size() - 1; i++) {
            List<Integer> edge = new ArrayList<>();
            edge.add(circuit.get(i));
            edge.add(circuit.get(i + 1));
            edges.add(edge);
        }
        // Добавляем ребро между последней и первой вершинами
        List<Integer> lastEdge = new ArrayList<>();
        lastEdge.add(circuit.get(circuit.size() - 1));
        lastEdge.add(circuit.get(0));
        edges.add(lastEdge);
        return edges;
    }
}
