package org.example.graphs;

public class MatrixGraph<T> extends Graph<T> {

    private T vertex;

    public MatrixGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
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
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(String label) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Vertex<T> v1, Vertex<T> v2) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(Vertex<T> v1, Vertex<T> v2, int w) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Vertex<T> v) {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(Vertex<T> v1, Vertex<T> v2) {
        // TODO Implement method
        throw new UnsupportedOperationException();
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
