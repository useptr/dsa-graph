package org.example.graphs;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraph<T> extends Graph<T> {

    private List<List<Boolean>> adj;

    public MatrixGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
        adj = new ArrayList<>();
    }

    @Override
    public Type dense() {
        return Type.MATRIX_GRAPH;
    }

    @Override
    public int K() {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    void add(Vertex<T> v) {
        if (v == null) {
            return;
        }
        super.addVertex(v);
        adj.forEach(connected -> connected.add(false));
    }

    @Override
    public void add(Vertex<T> src, Vertex<T> dst) {
        if (src == null || dst == null) {
            return;
        }
        super.addVertex(src);
        super.addVertex(dst);
        // add to adjacency matrix
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);
        Boolean connected = adj.get(srcIndex).get(dstIndex);
        connected = true;
    }

    @Override
    public void add(Vertex<T> src, Vertex<T> dst, double weight) {
        if (src == null || dst == null) {
            return;
        }
    }

    @Override
    public void remove(Vertex<T> v) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Vertex<T> src, Vertex<T> dst) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return null;
    }
}
