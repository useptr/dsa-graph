package org.example.graphs;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph<T> {
    public enum Type {LIST_GRAPH, MATRIX_GRAPH}

//    protected int vertices = 0;
//    protected int edges = 0;
    protected boolean directed;
    protected boolean weighted;

    protected List<Edge<T>> edges;
    protected List<Vertex<T>> vertices;

    public Graph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
        if (weighted) {
            edges = new ArrayList<>();
        }
        vertices = new ArrayList<>();
    }
//    protected void removeAllEdgesWithVertex(Vertex<T> vertex) {
//        edges.removeIf(edge -> (edge.source() == vertex || edge.destination() == vertex));
//    }
    protected void addVertex(Vertex<T> vertex) {
        if (vertex==null && vertices.contains(vertex)) {
            return;
        }
        vertices.add(vertex);
    }
    /**
     * V() - возвращает число вершин в графе
     */
    public int vertices() {
        return vertices.size();
    }

    /**
     * E() - возвращает число ребер в графе
     */
    public int edges() {
        return edges.size();
    }

    /**
     * Directed() - возвращает тип графа (ориентированный / неориентированный)
     */
    public boolean directed() {
        return directed;
    }
    public boolean weighted() {return weighted; }

    /**
     * Dense() - возвращает форму представления графа (L- граф / M- граф)
     */
    abstract public Type dense();

    /**
     * K() - возвращает коэффициент насыщенности графа
     */
    abstract public int K();

    /**
     * InsertV() добавляет безымянную вершину к графу и возвращает адрес дескриптора вновь созданной вершины
     */
    public void add() {
        vertices.add(new Vertex<>());
    }

    abstract void add(Vertex<T> v); // TODO чо за метод его нету в задании

    /**
     * добавляет вершину c именем name к графу и возвращает адрес дескриптора вновь созданной вершины
     */
    public void add(String label) {
        if (label == null)
            return;
        vertices.add(new Vertex<T>(label));
    }

    /**
     * InsertE(src, dst) - добавляет ребро между вершинами графа, заданными адресами дескрипторов src и dst
     * и возвращает адрес дескриптора вновь созданного ребра
     */
    abstract public void add(Vertex<T> src, Vertex<T> dst);

    /**
     * InsertE(src, dst, weight) - добавляет ребро между вершинами графа, заданными адресами дескрипторов src и dst,
     * с весом weight и возвращает адрес дескриптора вновь созданного ребра
     */
    abstract public void add(Vertex<T> src, Vertex<T> dst, double weight);
    public void removeVertex(Vertex<T> vertex) {
        if (vertex == null) {
            return;
        }
//        removeAllEdgesWithVertex(vertex);
        edges.removeIf(edge -> (edge.source() == vertex || edge.destination() == vertex));
        vertices.remove(vertex);
    }
    public void addEdge(Edge<T> edge) {
        if (edge == null)
            return;
        for (Edge<T> e : edges) { // check that there is no such edge
            if (e.source() == edge.source() && e.destination() == edge.destination()) { // if edge exist, update data
                e.weight(edge.weight());
                e.data(edge.data());
                return;
            }
        }
        edges.add(edge);
    }

    /**
     * DeleteV(v) - удаляет вершину из графа, заданную адресом дескриптора v
     */
    abstract public void remove(Vertex<T> v);

    /**
     * Удаляет ребро, соединяющее вершины, заданные адресами дескрипторов src и dst
     */
    abstract public void remove(Vertex<T> src, Vertex<T> dst);

    public void removeEdge(Vertex<T> src, Vertex<T> dst) {
        if (src == null || dst == null)
            return;
        edges.removeIf(edge -> (edge.source() == src && edge.destination() == dst));
    }

    /**
     * GetEdge (v1, v2) возвращает адрес дескриптора ребра соединяющего вершины, заданные дескрипторами v1 и v2
     */
    public Edge<T> get(Vertex<T> src, Vertex<T> dst) {
        if (!weighted) {
            return null;
        }
        for (Edge<T> edge : edges) {
            if (edge.source() == src && edge.destination() == dst) {
                return edge;
            }
        }
        return null;
    }
    public Edge<T> get(int srcIndex, int dstIndex) {
        Vertex<T> src = vertices.get(srcIndex);
        Vertex<T> dst = vertices.get(dstIndex);
        return  get( src, dst);
    }
    public Vertex<T> get(int index) {
        return vertices.get(index);
    }
}
