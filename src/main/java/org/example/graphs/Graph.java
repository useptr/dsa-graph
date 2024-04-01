package org.example.graphs;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph<T> {
    public enum Type {LIST_GRAPH, MATRIX_GRAPH}
    protected int vertices = 0;
    protected int edges = 0;
    protected boolean directed = false;
    protected boolean weighted = false;
    protected List<Edge<T>> edgeList;
    public Graph(boolean directed, boolean weighted) {
        this.directed = directed;
        if (weighted) {
            edgeList = new ArrayList<>();
        }
    }
    public int vertices() {
        return vertices;
    }
    public int edges() {
        return edges;
    }
    public boolean directed() {
        return directed;
    }
    abstract public Type dense();
//    abstract public int K();
    //    abstract public void toListGraph();
    //    abstract public void toMatrixGraph();
    abstract public void add(Vertex<T> v);
//abstract public void add(String label);
    abstract public void remove(Vertex<T> v);
abstract public void add(Vertex<T> v1, Vertex<T> v2);
abstract public void add(Vertex<T> v1, Vertex<T> v2, int w);
abstract public void remove(Vertex<T> v1, Vertex<T> v2);
//    abstract Edge<T> edge(Vertex<T> v1, Vertex<T> v2);
    abstract public String dump();
}
