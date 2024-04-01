package org.example.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleGraph<T> {
    //    void toListGraph();
    //    void toMatrixGraph();
    Graph graph;
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

    public SimpleGraph(int vertices, int edges, boolean directed, Graph.Type type) {
        if (Graph.Type.LIST_GRAPH == type) {
            graph = new ListGraph<T>(directed, true);
        } else {
//            graph = new MatrixGraph<T>(directed, true);
        }

        List<Vertex<T>> vertexList = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < vertices; ++i) {
            Vertex vertex = new Vertex<Integer>(rand.nextInt());
            graph.add(vertex);

            vertexList.add(vertex);
        }

        for (int i = 0; i < edges; ++i) {
            int size = vertexList.size();
            Vertex v1 = vertexList.get(rand.nextInt(size));
            Vertex v2 = vertexList.get(rand.nextInt(size));
            while (v1 == v2) {
                v2 = vertexList.get(rand.nextInt(size));
            }
            graph.add(v1, v2, rand.nextInt());
        }
    }
}
