package org.example.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListGraph<T> extends Graph<T> {
    private Map<Vertex<T>, List<Vertex<T>>> adjVertices = new HashMap<>();

    public ListGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
    }
    @Override
    public void remove(Vertex<T> v) {
        Vertex<T> copy = new Vertex<T>(v.label(), v.data());
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(copy);
    }

    @Override
    public String dump() {
        return null;
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

    }

    @Override
    public void remove(Vertex<T> v1, Vertex<T> v2) {
        List<Vertex<T>> e1 = adjVertices.get(v1);
        List<Vertex<T>> e2 = adjVertices.get(v2);
        if (e1 == null || e2 == null)
            return;
            e1.remove(v2);
            e2.remove(v1);
    }

    @Override
    public Type dense() {
        return Type.LIST_GRAPH;
    }

    @Override
    public void add(Vertex<T> v) {
        adjVertices.putIfAbsent(v, new ArrayList<>());
    }
}
