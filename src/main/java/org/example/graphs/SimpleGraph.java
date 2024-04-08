package org.example.graphs;

import java.util.Random;

public class SimpleGraph<T> {

    // TODO Реализовать методы для работы с абстрактным классом Graph
    AbstractGraph<T> abstractGraph; // TODO Обозначить дженерик графа Graph<T>, но пока что все ломается с ним


    /**
     * Конструктор() по умолчанию: создает пустой L - граф с нулевым числом вершин и ребер
     */
    public SimpleGraph() {
        abstractGraph = new ListGraph<T>(false, false);
    }

    /**
     * Конструктор(V, D, F) создает граф с V вершинами, без ребер,
     * типа D (ориентированный/ неориентированный), формы представления F (L- граф/M-граф)
     */
    public SimpleGraph(int vertices, boolean directed, AbstractGraph.Type type) {
        if (AbstractGraph.Type.LIST_GRAPH == type) {
            abstractGraph = new ListGraph<>(directed, false);
        } else {
            abstractGraph = new MatrixGraph<>(directed, false);
        }

        for (int i = 0; i < vertices; ++i) {
            // TODO fuck random
            abstractGraph.add(new Vertex<>());
        }
    }

    /**
     * Конструктор(V, E, D, F) создает граф с V вершинами, с E случайными ребрами,
     * типа D (ориентированный / неориентированный), формы представления F (L- граф/M-граф)
     */
    public SimpleGraph(int vertices, int edges, boolean directed, AbstractGraph.Type type) {
        if (AbstractGraph.Type.LIST_GRAPH == type) {
            abstractGraph = new ListGraph<T>(directed, true);
        } else {
            abstractGraph = new MatrixGraph<T>(directed, true);
        }

        Random rand = new Random();
        for (int i = 0; i < vertices; ++i) {
            abstractGraph.add(new Vertex<>());
        }

        for (int i = 0; i < edges; ++i) {
            int size = abstractGraph.vertices();
            Vertex<T> v1 = abstractGraph.get(rand.nextInt(size));
            Vertex<T> v2 = abstractGraph.get(rand.nextInt(size));
            while (v1 == v2) {
                v2 = abstractGraph.get(rand.nextInt(size));
            }
            abstractGraph.add(v1, v2, rand.nextInt(100));
        }
    }

    /**
     * Конструктор(G) - конструктор копирования создает объект – копию графа G
     */
    public SimpleGraph(SimpleGraph<T> graph) {

    }
    /**
     * ToListGraph() преобразует граф к L- графу
     */
    public void toListGraph() {
        if (abstractGraph.dense() == AbstractGraph.Type.LIST_GRAPH)
            return;
        AbstractGraph<T> listAbstractGraph = new ListGraph<T>(abstractGraph.directed(), abstractGraph.weighted());
        // TODO Implement method
        abstractGraph = listAbstractGraph;
    }

    /**
     * преобразует граф к M- графу
     */
    public void toMatrixGraph() {
        if (abstractGraph.dense() == AbstractGraph.Type.MATRIX_GRAPH)
            return;
        AbstractGraph<T> matrixAbstractGraph = new MatrixGraph<>(abstractGraph.directed(), abstractGraph.weighted());
        // TODO Implement method
        abstractGraph = matrixAbstractGraph;
    }

    /**
     * V() - возвращает число вершин в графе
     */
    public int vertices() {
        return abstractGraph.vertices();
    }

    /**
     * E() - возвращает число ребер в графе
     */
    public int edges() {
        return abstractGraph.edges();
    }

    /**
     * Directed() - возвращает тип графа (ориентированный / неориентированный)
     */
    public boolean directed() {
        return abstractGraph.directed();
    }

    public boolean weighted() {
        return abstractGraph.weighted();
    }

    /**
     * Dense() - возвращает форму представления графа (L- граф / M- граф)
     */
    public AbstractGraph.Type dense() {
        return abstractGraph.dense();
    }

    /**
     * K() - возвращает коэффициент насыщенности графа
     */
    public int saturation() {
        return abstractGraph.saturation();
    }

    /**
     * InsertV() добавляет безымянную вершину к графу и возвращает адрес дескриптора вновь созданной вершины
     */
    public void add() {
        abstractGraph.add();
    }

    public void add(String label) {
        abstractGraph.add(label);
    }

    public void add(Vertex<T> src, Vertex<T> dst) {
        abstractGraph.add(src, dst);
    }

    public void add(Vertex<T> src, Vertex<T> dst, double weight) {
        abstractGraph.add(src, dst, weight);
    }

    public void remove(Vertex<T> v) {
        abstractGraph.remove(v);
    }

    public void remove(Vertex<T> src, Vertex<T> dst) {
        abstractGraph.remove(src, dst);
    }

    public Edge<T> get(int srcIndex, int dstIndex) {
        return abstractGraph.get(srcIndex, dstIndex);
    }

    public Vertex<T> get(int index) {
        return abstractGraph.get(index);
    }

    public Edge<T> get(Vertex<T> src, Vertex<T> dst) {
        return abstractGraph.get(src, dst);
    }

    public void render() {
        abstractGraph.renderToPng("example/ex1m2.png");
    }

    @Override
    public String toString() {
        return abstractGraph.toString();
    }
}
