package org.example.graphs;

import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.*;

public abstract class AbstractGraph<T> {
    public enum Type {LIST_GRAPH, MATRIX_GRAPH}

    public class VertexIterator {
        private AbstractGraph<T> graph;
        private Vertex<T> current;

        public VertexIterator() {
        }

        public VertexIterator(AbstractGraph<T> graph, Vertex<T> current) {
            this.graph = graph;
            this.current = current;
        }

        public Vertex<T> get() { // *
            return current;
        }

        public VertexIterator next() { // ++
            return null;
        }

        public boolean equal(VertexIterator other) { // ==
            return current == other.current;
        }

        public boolean notEqual(VertexIterator other) { // !=
            return !equal(other);
        }
    }

    public class EdgeIterator {
        private AbstractGraph<T> graph;
        private Edge<T> current;

        public EdgeIterator() {
        }

        public EdgeIterator(AbstractGraph<T> graph, Edge<T> current) {
            this.graph = graph;
            this.current = current;
        }

        public Edge<T> get() { // *
            return current;
        }

        public EdgeIterator next() { // ++
            return null;
        }

        public boolean equal(EdgeIterator other) { // ==
            return current == other.current;
        }

        public boolean notEqual(EdgeIterator other) { // !=
            return !equal(other);
        }
    }

    public class OutgoingEdgeIterator {
        private AbstractGraph<T> graph;
        private Edge<T> current;

        public OutgoingEdgeIterator() {
        }

        public OutgoingEdgeIterator(AbstractGraph<T> graph, Edge<T> current) {
            this.graph = graph;
            this.current = current;
        }

        public Edge<T> get() { // *
            return current;
        }

        public OutgoingEdgeIterator next() { // ++
            return null;
        }

        public boolean equal(OutgoingEdgeIterator other) { // ==
            return current == other.current;
        }

        public boolean notEqual(OutgoingEdgeIterator other) { // !=
            return !equal(other);
        }
    }

    protected boolean directed;
    protected boolean weighted;

    protected List<Edge<T>> edges;
    protected List<Vertex<T>> vertices;

    public AbstractGraph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
        if (weighted) {
            edges = new ArrayList<>();
        }
        vertices = new ArrayList<>();
    }

    abstract protected List<Edge<T>> connections(); // return unique connection if graph is directed

    public void renderToPng(String path) {
        MutableGraph g = mutGraph("example").setDirected(directed);
        HashMap<Vertex<T>, MutableNode> nodes = new HashMap<>();
        // add all vertices to hash table nodes
        for (Vertex<T> vertex : vertices) {
            String label = vertex.label() != null ? vertex.label() : "";
            String data = vertex.data() != null ? vertex.data().toString() : "";
            String description = "id: " + vertices.indexOf(vertex) + "\n" + "label: " + label + "\n" + "data: " + data;
            nodes.put(vertex, mutNode(description));
        }
        // get all edges
        List<Edge<T>> connections = connections();
        if (weighted) {
            connections = new ArrayList<>();
            for (Edge<T> edge : edges) {
                if (!directed && contain(edge.destination(), edge.source(), connections)) { // skip repetitions
                    continue;
                }
                connections.add(edge);
            }
        }
        // add all links
        for (Edge<T> edge : connections) {
            String description = "";
            if (weighted) {
                String data = edge.data() != null ? edge.data().toString() : "";
                description += "weight: " + edge.weight() + "\n" + "data: " + data;
            }
            MutableNode src = nodes.get(edge.source());
            MutableNode dst = nodes.get(edge.destination());
            // create graph viz link to node
            src.addLink(to(dst).with(Label.of(description)));
        }
        // add all nodes to graph viz
        nodes.forEach((k, v) -> g.add(v));
        // render
        try {
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected boolean contain(Vertex<T> src, Vertex<T> dst, List<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            if (src == edge.source() && dst == edge.destination()) {
                return true;
            }
        }
        return false;
    }

    //    protected void removeAllEdgesWithVertex(Vertex<T> vertex) {
//        edges.removeIf(edge -> (edge.source() == vertex || edge.destination() == vertex));
//    }
    protected void addVertex(Vertex<T> vertex) {
        if (vertex == null || vertices.contains(vertex)) {
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

    public boolean weighted() {
        return weighted;
    }

    /**
     * Dense() - возвращает форму представления графа (L- граф / M- граф)
     */
    abstract public Type dense();

    /**
     * K() - возвращает коэффициент насыщенности графа
     */
    abstract public int saturation();

    /**
     * InsertV() добавляет безымянную вершину к графу и возвращает адрес дескриптора вновь созданной вершины
     */
    public void add() {
        vertices.add(new Vertex<>());
    }

    protected abstract void add(Vertex<T> v); // TODO чо за метод его нету в задании

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

    protected void removeVertex(Vertex<T> vertex) {
        if (vertex == null) {
            return;
        }
//        removeAllEdgesWithVertex(vertex);
        edges.removeIf(edge -> (edge.source() == vertex || edge.destination() == vertex));
        vertices.remove(vertex);
    }

    private Edge<T> find(Vertex<T> src, Vertex<T> dst) {
        for (Edge<T> edge : edges) {
            if (src == edge.source() && dst == edge.destination()) {
                return edge;
            }
        }
        return null;
    }

    public void addEdge(Edge<T> edge) {
        if (edge == null)
            return;
        // only a weighted graph has edges
        if (!weighted)
            return;
        Edge<T> existed = find(edge.source(), edge.destination());
        if (existed != null) {
            existed.weight(edge.weight());
            existed.data(edge.data());
        } else {
            edges.add(edge);
        }

        if (!directed) {
            existed = find(edge.destination(), edge.source());
            if (existed != null) {
                existed.weight(edge.weight());
                existed.data(edge.data());
            } else {
                edges.add(new Edge<>(edge.destination(), edge.source(), edge.data(), edge.weight()));
            }
        }
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
        // only a weighted graph has edges
        if (!weighted)
            return;
        edges.removeIf(edge -> (edge.source() == src && edge.destination() == dst));
        if (!directed) {
            edges.removeIf(edge -> (edge.source() == dst && edge.destination() == src));
        }
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
        return get(src, dst);
    }

    public Vertex<T> get(int index) {
        return vertices.get(index);
    }
}
