package com.example.dsagraph.graphs;

import java.util.List;
import java.util.Random;

public class SimpleGraph<T> {
    private AbstractGraph<T> graph;

    /**
     * Конструктор() по умолчанию: создает пустой L - граф с нулевым числом вершин и ребер
     */
    public SimpleGraph() {
        graph = new ListGraph<T>(false, false);
    }

    /**
     * Конструктор(V, D, F) создает граф с V вершинами, без ребер,
     * типа D (ориентированный/ неориентированный), формы представления F (L- граф/M-граф)
     */
    public SimpleGraph(final int vertices, boolean directed, final AbstractGraph.Type type) {
        if (AbstractGraph.Type.LIST_GRAPH == type) {
            graph = new ListGraph<>(directed, false);
        } else {
            graph = new MatrixGraph<>(directed, false);
        }
        while (graph.vertices() < vertices)
            graph.add(new Vertex<>());
    }

    /**
     * Конструктор(V, E, D, F) создает граф с V вершинами, с E случайными ребрами,
     * типа D (ориентированный / неориентированный), формы представления F (L- граф/M-граф)
     */
    public SimpleGraph(final int vertices, final int edges, final boolean directed, final AbstractGraph.Type type) {
        if (AbstractGraph.Type.LIST_GRAPH == type) {
            graph = new ListGraph<>(directed, true);
        } else {
            graph = new MatrixGraph<>(directed, true);
        }

        while (graph.vertices() <= vertices)
            graph.add(new Vertex<>());

        Random rand = new Random();
        while ((directed && graph.edges() <= edges) || (!directed && graph.connections().size() < edges)) {
            int size = graph.vertices();
            Vertex<T> v1 = graph.get(rand.nextInt(size));
            Vertex<T> v2 = graph.get(rand.nextInt(size));
            while (v1 == v2) {
                v2 = graph.get(rand.nextInt(size));
            }
            graph.add(v1, v2, null, rand.nextInt(100));
        }
    }

    /**
     * Конструктор(G) - конструктор копирования создает объект – копию графа G
     */
    public SimpleGraph(SimpleGraph<T> graph) {
        for (Vertex<T> vertex : graph.graph.getVertices())
            this.graph.add(vertex);

        List<Edge<T>> edges = graph.graph.connections();
        if (graph.weighted())
            edges = graph.graph.getEdges();
        for (Edge<T> edge : edges)
            this.graph.add(edge.source(), edge.destination(), edge.data(), edge.weight());
    }
    /**
     * ToListGraph() преобразует граф к L- графу
     */
    public void toListGraph() {
        if (graph.dense() == AbstractGraph.Type.LIST_GRAPH)
            return;
        AbstractGraph<T> listGraph = new ListGraph<T>(graph.directed(), graph.weighted());

        for (Vertex<T> vertex : graph.getVertices())
            listGraph.add(vertex);

        List<Edge<T>> edges = graph.connections();
        if (graph.weighted())
            edges = graph.getEdges();
        for (Edge<T> edge : edges)
            listGraph.add(edge.source(), edge.destination(), edge.data(), edge.weight());

        graph = listGraph;
    }

    /**
     * преобразует граф к M- графу
     */
    public void toMatrixGraph() {
        if (graph.dense() == AbstractGraph.Type.MATRIX_GRAPH)
            return;
        AbstractGraph<T> matrixGraph = new MatrixGraph<>(graph.directed(), graph.weighted());
        for (Vertex<T> vertex : graph.getVertices())
            matrixGraph.add(vertex);

        List<Edge<T>> edges = graph.connections();
        if (graph.weighted())
            edges = graph.getEdges();
        for (Edge<T> edge : edges)
            matrixGraph.add(edge.source(), edge.destination(), edge.data(), edge.weight());
        graph = matrixGraph;
    }

    /**
     * V() - возвращает число вершин в графе
     */
    public int vertices() {
        return graph.vertices();
    }

    /**
     * E() - возвращает число ребер в графе
     */
    public int edges() {
        return graph.edges();
    }

    /**
     * Directed() - возвращает тип графа (ориентированный / неориентированный)
     */
    public boolean directed() {
        return graph.directed();
    }

    public boolean weighted() {
        return graph.weighted();
    }

    /**
     * Dense() - возвращает форму представления графа (L- граф / M- граф)
     */
    public AbstractGraph.Type dense() {
        return graph.dense();
    }

    /**
     * K() - возвращает коэффициент насыщенности графа
     */
    public int saturation() {
        return graph.saturation();
    }

    /**
     * InsertV() добавляет безымянную вершину к графу и возвращает адрес дескриптора вновь созданной вершины
     */
    public void add() {
        graph.add(new Vertex<>());
    }

    public void add(String label, T data) {
        graph.add(new Vertex<>(label, data));
    }

    public void add(Vertex<T> src, Vertex<T> dst) {
        graph.add(src, dst, null,0);
    }

    public void add(Vertex<T> src, Vertex<T> dst, double weight) {
        graph.add(src, dst, null, weight);
    }
    public void add(Vertex<T> src, Vertex<T> dst, T data, double weight) {
        graph.add(src, dst, data, weight);
    }

    public void remove(Vertex<T> v) {
        graph.remove(v);
    }

    public void remove(Vertex<T> src, Vertex<T> dst) {
        graph.remove(src, dst);
    }

    public Edge<T> get(int srcIndex, int dstIndex) {
        return graph.get(srcIndex, dstIndex);
    }

    public Vertex<T> get(int index) {
        return graph.get(index);
    }

    public Edge<T> get(Vertex<T> src, Vertex<T> dst) {
        return graph.get(src, dst);
    }

    public void render() {
        graph.renderToPng("example/graph.png");
    }
    public AbstractGraph<T>.VertexIterator begin() {
        return graph.begin();
    }
    public AbstractGraph<T>.VertexIterator end() {
        return graph.end();
    }
    public AbstractGraph<T>.EdgeIterator beginEdge() {
        return graph.beginEdge();
    }
    public AbstractGraph<T>.EdgeIterator endEdge() {
        return graph.endEdge();
    }
    public AbstractGraph<T>.OutgoingEdgeIterator begin(Vertex<T> vertex) {
        return graph.begin(vertex);
    }
    public AbstractGraph<T>.OutgoingEdgeIterator end(Vertex<T> vertex) {
        return graph.end(vertex);
    }
    @Override
    public String toString() {
        return graph.toString();
    }
}
