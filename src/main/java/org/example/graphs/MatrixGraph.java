package org.example.graphs;

import java.util.ArrayList;
import java.util.List;

public class MatrixGraph<T> extends AbstractGraph<T> {

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

        boolean vertexExist = vertices.indexOf(v) < adj.size();
        if (!vertexExist) {
            List<Boolean> connections = new ArrayList<>();
            for (int i = 0; i < adj.size(); ++i) {
                connections.add(false);
            }
            adj.add(connections);
            adj.forEach(connected -> connected.add(false));
        }
    }

    @Override
    public void add(Vertex<T> src, Vertex<T> dst) {
        if (src == null || dst == null) {
            return;
        }
        super.addVertex(src);
        super.addVertex(dst);

        // add to adjacency matrix
        // check vertex existing
        add(src);
        add(dst);
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);
        // update connection
        adj.get(srcIndex).set(dstIndex,true);
        if (!directed) {
            adj.get(dstIndex).set(srcIndex,true);
        }

        super.addEdge(new Edge<>(src, dst));
    }

    @Override
    public void add(Vertex<T> src, Vertex<T> dst, double weight) {
        if (src == null || dst == null) {
            return;
        }
        super.addVertex(src);
        super.addVertex(dst);

        // add to adjacency matrix
        // check vertex existing
        add(src);
        add(dst);
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);
        // update connection
        adj.get(srcIndex).set(dstIndex,true);
        if (!directed) {
            adj.get(dstIndex).set(srcIndex,true);
        }

        super.addEdge(new Edge<>(src, dst, weight));
    }

    @Override
    public void remove(Vertex<T> v) {
        if (v == null)
            return;

        // remove from adjacency matrix
        int removedIndex = vertices.indexOf(v);
        if (removedIndex != -1) {
            for (List<Boolean> connections : adj) {
                connections.remove(removedIndex);
            }
            adj.remove(removedIndex);
        }

        super.removeVertex(v);
    }

    @Override
    public void remove(Vertex<T> src, Vertex<T> dst) {
        if (src == null || dst == null)
            return;

        // remove from adjacency matrix
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);
        boolean validConnection = srcIndex != -1 && dstIndex != -1 && srcIndex < adj.size() && dstIndex < adj.size();
        if (validConnection) {
            adj.get(srcIndex).set(dstIndex,false);
            if (!directed) {
                adj.get(dstIndex).set(srcIndex,false);
            }
        }

        super.removeEdge(src, dst);
    }

    @Override
    protected List<Edge<T>> connections() { // return unique connection if graph is directed
        if (weighted) {
            return null;
        }
        List<Edge<T>> connections = new ArrayList<>();
        for (int i = 0; i < adj.size(); ++i) {
            List<Boolean> connected = adj.get(i);
            for (int j = 0; j < connected.size(); ++j) {
                if (connected.get(j)) {
                    Edge<T> edge = new Edge<>(vertices.get(i), vertices.get(j));
                    if (directed && contain(edge.destination(), edge.source(), connections)) { // skip repetitions
                        continue;
                    }
                    connections.add(edge);
                }
            }
        }
        return connections;
    }

    @Override
    public String toString() {
        String adjMatrix = "";
        for (int i = 0; i < vertices.size(); ++i) {
            adjMatrix += "\t" + i;
        }
        adjMatrix += "\n";
        for (int i = 0; i < adj.size(); ++i) {
            adjMatrix += i + ":";
            List<Boolean> connections = adj.get(i);
            for (int j = 0; j < connections.size(); ++j) {
                adjMatrix += "\t";
                if (connections.get(j)) {
                    adjMatrix += "1";
                } else {
                    adjMatrix += "0";
                }
            }
            adjMatrix += "\n";
        }
        return adjMatrix;
    }
}
