package org.example.graphs;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph<T> {
    public enum Type {LIST_GRAPH, MATRIX_GRAPH}

    protected int vertices = 0;
    protected int edges = 0;
    protected boolean directed;
    protected boolean weighted;
    protected List<Edge<T>> edgeList;

    public Graph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
        if (weighted) {
            edgeList = new ArrayList<>();
        }
    }

    /**
     * V() - возвращает число вершин в графе
     */
    public int vertices() {
        return vertices;
    }

    /**
     * E() - возвращает число ребер в графе
     */
    public int edges() {
        return edges;
    }

    /**
     * Directed() - возвращает тип графа (ориентированный / неориентированный)
     */
    public boolean directed() {
        return directed;
    }

    /**
     * Dense() - возвращает форму представления графа (L- граф / M- граф)
     */
    abstract public Type dense();

    /**
     * K() - возвращает коэффициент насыщенности графа
     */
    abstract public int K();

    /**
     * ToListGraph() преобразует граф к L- графу
     */
    abstract public void toListGraph();

    /**
     * преобразует граф к M- графу
     */
    abstract public void toMatrixGraph();

    /**
     * InsertV() добавляет безымянную вершину к графу и возвращает адрес дескриптора вновь созданной вершины
     */
    abstract public void add();

    abstract public void add(Vertex<T> v); // TODO чо за метод его нету в задании

    /**
     * добавляет вершину c именем name к графу и возвращает адрес дескриптора вновь созданной вершины
     */
    abstract public void add(String label);

    /**
     * InsertE(v1, v2) - добавляет ребро между вершинами графа, заданными адресами дескрипторов v1 и v2
     * и возвращает адрес дескриптора вновь созданного ребра
     */
    abstract public void add(Vertex<T> v1, Vertex<T> v2);

    /**
     * InsertE(v1, v2, w) - добавляет ребро между вершинами графа, заданными адресами дескрипторов v1 и v2,
     * с весом w и возвращает адрес дескриптора вновь созданного ребра
     */
    abstract public void add(Vertex<T> v1, Vertex<T> v2, int w);

    /**
     * DeleteV(v) - удаляет вершину из графа, заданную адресом дескриптора v
     */
    abstract public void remove(Vertex<T> v);

    /**
     * Удаляет ребро, соединяющее вершины, заданные адресами дескрипторов v1 и v2
     */
    abstract public void remove(Vertex<T> v1, Vertex<T> v2);

    /**
     * GetEdge (v1, v2) возвращает адрес дескриптора ребра соединяющего вершины, заданные дескрипторами v1 и v2
     */
    abstract Edge<T> get(Vertex<T> v1, Vertex<T> v2);
}
