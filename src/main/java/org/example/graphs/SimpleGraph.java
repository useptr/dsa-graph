package org.example.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleGraph<T> {

    // TODO Реализовать методы для работы с абстрактным классом Graph
    Graph graph; // TODO Обозначить дженерик графа Graph<T>, но пока что все ломается с ним


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
    public SimpleGraph(int vertices, boolean directed, Graph.Type type) {
        if (Graph.Type.LIST_GRAPH == type) {
            graph = new ListGraph<T>(directed, false);
        } else {
//            graph = new MatrixGraph<T>(directed, false);
        }
        Random rand = new Random();
        for (int i = 0; i < vertices; ++i) {
            Vertex vertex = new Vertex<Integer>(rand.nextInt());
            graph.add(vertex);
        }
    }

    /**
     * Конструктор(V, E, D, F) создает граф с V вершинами, с E случайными ребрами,
     * типа D (ориентированный / неориентированный), формы представления F (L- граф/M-граф)
     */
    public SimpleGraph(int vertices, int edges, boolean directed, Graph.Type type) {
        if (Graph.Type.LIST_GRAPH == type) {
            graph = new ListGraph<T>(directed, true);
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
}
