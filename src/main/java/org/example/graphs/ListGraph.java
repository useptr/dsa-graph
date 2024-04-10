package org.example.graphs;

import java.util.*;

public class ListGraph<T> extends AbstractGraph<T> {
    private final Map<Vertex<T>, List<Vertex<T>>> adj;

    public ListGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
        adj = new HashMap<>();
    }

    @Override
    public Type dense() {
        return Type.LIST_GRAPH;
    }

    @Override
    protected void add(Vertex<T> v) {
        super.addVertex(v);
        adj.putIfAbsent(v, new ArrayList<>());
    }

    @Override
    public void add(Vertex<T> src, Vertex<T> dst, double weight) {
        if (src == null || dst == null)
            return;
        super.addVertex(src);
        super.addVertex(dst);

        // add to adjacency list
        adj.putIfAbsent(src, new ArrayList<>());
        boolean noConnection = !adj.get(src).contains(dst);
        if (noConnection)
            adj.get(src).add(dst);

        if (!directed) {
            adj.putIfAbsent(dst, new ArrayList<>());
            noConnection = !adj.get(dst).contains(src);
            if (noConnection)
                adj.get(dst).add(src);
        }

        super.addEdge(new Edge<>(src, dst, weight));
    }

    @Override
    public void remove(Vertex<T> v) {
        if (v == null)
            return;

        // remove from adjacency list
        int removedIndex = vertices.indexOf(v);
        if (removedIndex != -1) { // remove from adjacency list
            adj.remove(v);
            adj.values().forEach(connected -> connected.removeIf(vertex -> (vertex == v)));
        }

        super.removeVertex(v);
    }

    @Override
    public void remove(Vertex<T> src, Vertex<T> dst) {
        if (src == null || dst == null)
            return;

        // remove from adjacency list
        if (adj.get(src) != null)
            adj.get(src).removeIf(vertex -> (vertex == dst));

        if (!directed && adj.get(dst) != null)
                adj.get(dst).removeIf(vertex -> (vertex == src));

        super.removeEdge(src, dst);
    }

    @Override
    protected List<Edge<T>> connections() { // return unique connection if graph is not directed
        if (weighted)
            return null;

        List<Edge<T>> connections = new ArrayList<>();
        for (Vertex<T> src : adj.keySet()) {
            for (Vertex<T> dst : adj.get(src)) {
                Edge<T> edge = new Edge<>(src, dst);
                if (!directed && contain(edge.destination(), edge.source(), connections)) { // skip repetitions
                    continue;
                }
                connections.add(edge);
            }
        }
        return connections;
    }

    @Override
    public String toString() {
        String adjList = "";
        for (Vertex<T> key : adj.keySet()) {
            adjList += vertices.indexOf(key) + " : ";
            for (Vertex<T> vertex : adj.get(key)) {
                adjList += vertices.indexOf(vertex) + " ";
            }
            adjList += "\n";
        }
        return adjList;
    }
}
