package org.example.graphs;

import java.util.Random;

public class SimpleGraph<T> {

    // TODO Реализовать методы для работы с абстрактным классом Graph
    AbstractGraph abstractGraph; // TODO Обозначить дженерик графа Graph<T>, но пока что все ломается с ним


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
            abstractGraph = new ListGraph<T>(directed, false);
        } else {
            abstractGraph = new MatrixGraph<T>(directed, false);
        }
        Random rand = new Random();
        for (int i = 0; i < vertices; ++i) {
            Vertex<Integer> vertex = new Vertex<>(rand.nextInt());
            // TODO add a check T is a Integer or fuck of Random
            abstractGraph.add(vertex);
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
//            graph = new MatrixGraph<T>(directed, true);
        }
//        List<Vertex<T>> vertexList = new ArrayList<>();
//        Random rand = new Random();
//        for (int i = 0; i < vertices; ++i) {
//            Vertex vertex = new Vertex<Integer>(rand.nextInt());
//            graph.add(vertex);
//
//            vertexList.add(vertex);
//        }
//
//        for (int i = 0; i < edges; ++i) {
//            int size = vertexList.size();
//            Vertex v1 = vertexList.get(rand.nextInt(size));
//            Vertex v2 = vertexList.get(rand.nextInt(size));
//            while (v1 == v2) {
//                v2 = vertexList.get(rand.nextInt(size));
//            }
//            graph.add(v1, v2, rand.nextInt());
//        }
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
    @Override
    public String toString() {
        return abstractGraph.toString();
    }
}
