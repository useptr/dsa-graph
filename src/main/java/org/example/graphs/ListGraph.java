package org.example.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListGraph<T> extends Graph<T> {
    private final Map<Vertex<T>, List<Vertex<T>>> adjVertices = new HashMap<>();

    public ListGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
    }

    @Override
    public Type dense() {
        return Type.LIST_GRAPH;
    }

    @Override
    public int K() {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void toListGraph() {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void toMatrixGraph() {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void add() {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Vertex<T> v) {
        adjVertices.putIfAbsent(v, new ArrayList<>());
    }

    @Override
    public void add(String label) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Vertex<T> v1, Vertex<T> v2) {
//        add(v1);
//        add(v2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }

    @Override
    public void add(Vertex<T> v1, Vertex<T> v2, int w) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Vertex<T> v) {
        Vertex<T> copy = new Vertex<>(v.label(), v.data());
        adjVertices.values().forEach(e -> e.remove(v));
        adjVertices.remove(copy);
    }

    @Override
    public void remove(Vertex<T> v1, Vertex<T> v2) {
        List<Vertex<T>> e1 = adjVertices.get(v1);
        List<Vertex<T>> e2 = adjVertices.get(v2);
        if (e1 == null || e2 == null) return;
        e1.remove(v2);
        e2.remove(v1);
    }

    @Override
    Edge<T> get(Vertex<T> v1, Vertex<T> v2) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return null;
    }
}
