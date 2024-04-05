package org.example.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListGraph<T> extends Graph<T> {
    private Map<Integer, List<Integer>> adj;

    public ListGraph(boolean directed, boolean weighted) {
        super(directed, weighted);
        adj = new HashMap<>();
    }

    @Override
    public Type dense() {
        return Type.LIST_GRAPH;
    }
    @Override
    public int K() {
        // TODO Implement method
        throw new UnsupportedOperationException();
    }

    @Override
    void add(Vertex<T> v) {
        super.addVertex(v);
        // TODO maybe need add new vertex to adjacency list
//        adj.putIfAbsent(srcIndex, new ArrayList<>());
    }
    @Override
    public void add(Vertex<T> src, Vertex<T> dst) {
        if (src == null || dst == null) {
            return;
        }
        addVertex(src);
        addVertex(dst);

        // add to adjacency list
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);
        adj.putIfAbsent(srcIndex, new ArrayList<>());
        boolean noConnection = !adj.get(srcIndex).contains(dstIndex);
        if (noConnection)
            adj.get(srcIndex).add(dstIndex);

        if (!directed) {
            adj.putIfAbsent(dstIndex, new ArrayList<>());
            noConnection = !adj.get(dstIndex).contains(srcIndex);
            if (noConnection)
                adj.get(dstIndex).add(srcIndex);
        }

        if (!weighted)
            return;
        // there will be one edge from src to dst if the graph is oriented
        // TODO if graph directed add a check no connection dst to src, if connection exist update data
        super.addEdge(new Edge<>(src, dst));
    }

    @Override
    public void add(Vertex<T> src, Vertex<T> dst, double weight) {
        if (src == null || dst == null) {
            return;
        }
        super.addVertex(src);
        super.addVertex(dst);

        // add to adjacency list
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);
        adj.putIfAbsent(srcIndex, new ArrayList<>());
        // TODO add a check adjacency list not contain connected src, dst
        adj.get(srcIndex).add(dstIndex);
        if (!directed) {
            adj.putIfAbsent(dstIndex, new ArrayList<>());
            // TODO add a check adjacency list not contain connected src, dst
            adj.get(dstIndex).add(srcIndex);
        }

        if (!weighted)
            return;
        super.addEdge(new Edge<>(src, dst, weight));
    }

    @Override
    public void remove(Vertex<T> v) {
        if (v == null)
            return;

        // remove from adjacency list
        int removedIndex = vertices.indexOf(v);
        if (removedIndex != -1) { // remove from adjacency list
            adj.remove(removedIndex);
            adj.values().forEach(connected -> connected.removeIf(index -> (index == removedIndex)));
        }

        super.removeVertex(v);
    }

    @Override
    public void remove(Vertex<T> src, Vertex<T> dst) {
        if (src == null || dst == null)
            return;

        // remove from adjacency list
        int srcIndex = vertices.indexOf(src);
        int dstIndex = vertices.indexOf(dst);
        if (adj.get(srcIndex) != null) {
            adj.get(srcIndex).removeIf(index -> (index == dstIndex));
        }
        if (!directed) {
            if (adj.get(dstIndex) != null) {
                adj.get(dstIndex).removeIf(index -> (index == srcIndex));
            }
        }

        if (!weighted)
            return;

        super.removeEdge(src, dst);
        if (!directed) {
            super.removeEdge(dst, src);
        }
    }

    @Override
    public String toString() {
        String adjMatrix = "";
//        for (int i = 0; i < vertices.size(); ++i) {
//            adjMatrix += "\t" + i;
//        }
//        adjMatrix += "\n";
//        for (int i = 0; i < vertices.size(); ++i) {
//
//            adjMatrix += i + ":";
//            for (int j = 0; j < vertices.size(); ++j) {
//                adjMatrix += "\t";
//
//                if (weighted) {
//                    Edge<T> edge = get(i, j);
//                    adjMatrix += edge.weight();
//                } else {
////                    List<Integer> connected = adj.get(i);
//                    if (adj.get(i) != null && adj.get(i).get(j) != null) {
//                        adjMatrix += "1";
//                    } else {
//                        adjMatrix += "0";
//                    }
//                }
//            }
//            adjMatrix += "\n";
//        }
        return adjMatrix;
    }
}
