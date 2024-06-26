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
            int index = graph.vertices.indexOf(current);
            if (index+1 < vertices())
                current = graph.vertices.get(index+1);
            else
                current = graph.fakeVertex;
            return this;
        }

        public boolean equal(VertexIterator other) { // ==
            return current == other.current;
        }

        public boolean notEqual(VertexIterator other) { // !=
            return !equal(other);
        }
    }

    public VertexIterator begin() {
        return new VertexIterator(this, vertices.get(0));
    }
    public VertexIterator end() {
        return new VertexIterator(this, fakeVertex);
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
            int index = graph.edges.indexOf(current);
            if (index+1 < edges())
                current = graph.edges.get(index+1);
            else
                current = graph.fakeEdge;
            return this;
        }

        public boolean equal(EdgeIterator other) { // ==
            return current == other.current;
        }

        public boolean notEqual(EdgeIterator other) { // !=
            return !equal(other);
        }
    }

    public EdgeIterator beginEdge() {
        if (weighted)
            return new EdgeIterator(this, edges.get(0));
        return endEdge();
    }
    public EdgeIterator endEdge() {
        return new EdgeIterator(this, fakeEdge);
    }

    public class OutgoingEdgeIterator {
        private AbstractGraph<T> graph;
        private Vertex<T> vertex;
        private Edge<T> edge;

        public OutgoingEdgeIterator() {
        }

        public OutgoingEdgeIterator(AbstractGraph<T> graph, Vertex<T> vertex) {
            this.graph = graph;
            this.vertex = vertex;
            if (vertex == graph.fakeVertex)
                this.edge = graph.fakeEdge;
        }

        public Edge<T> get() { // *
            return edge;
        }

        public OutgoingEdgeIterator next() { // ++
            if (edge == graph.fakeEdge)
                return this;
            List<Edge<T>> outgoing = graph.outgoingEdges(vertex);
            int index = outgoing.indexOf(edge);
            if (-1 != index && index + 1 < outgoing.size())
                edge = outgoing.get(index+1);
            else
                edge = graph.fakeEdge;
            return this;
        }

        public boolean equal(OutgoingEdgeIterator other) { // ==
            return get() == other.get();
        }

        public boolean notEqual(OutgoingEdgeIterator other) { // !=
            return !equal(other);
        }
    }
    protected List<Edge<T>> outgoingEdges(Vertex<T> vertex) {
        List<Edge<T>> outgoing = new ArrayList<>();
        edges.forEach(edge -> {
            if (edge.source() == vertex)
                outgoing.add(edge);
        });
        return outgoing;
    }
    public OutgoingEdgeIterator begin(Vertex<T> vertex) {
        return new OutgoingEdgeIterator(this, vertex);
    }
    public OutgoingEdgeIterator end(Vertex<T> vertex) {
        return new OutgoingEdgeIterator(this, fakeVertex);
    }

    protected boolean directed;
    protected boolean weighted;
    protected List<Edge<T>> edges;
    protected List<Vertex<T>> vertices;
    protected Vertex<T> fakeVertex;

    protected Edge<T> fakeEdge;

    public AbstractGraph(boolean directed, boolean weighted) {
        this.directed = directed;
        this.weighted = weighted;
        if (weighted) {
            edges = new ArrayList<>();
        }
        vertices = new ArrayList<>();

        fakeVertex = new Vertex<>();
        fakeEdge = new Edge<>();
    }

    abstract protected List<Edge<T>> connections(); // return unique connection if graph is not directed

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
    protected void addVertex(Vertex<T> vertex) {
        if (vertex == null || vertices.contains(vertex)) {
            return;
        }
        vertices.add(vertex);
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public List<Vertex<T>> getVertices() {
        return vertices;
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
    public int saturation() {
        if (directed)
            return edges()/(vertices()*(vertices()-1));
        return vertices()*(vertices()-1)/2;
    }

    /**
     * InsertV() добавляет безымянную вершину к графу и возвращает адрес дескриптора вновь созданной вершины
     */

    protected abstract void add(Vertex<T> v);

    /**
     * InsertE(src, dst, weight) - добавляет ребро между вершинами графа, заданными адресами дескрипторов src и dst,
     * с весом weight и возвращает адрес дескриптора вновь созданного ребра
     */
    abstract public void add(Vertex<T> src, Vertex<T> dst, double weight);

    protected void removeVertex(Vertex<T> vertex) {
        if (vertex == null)
            return;
        if (weighted) // edges != null
            edges.removeIf(edge -> (edge.source() == vertex || edge.destination() == vertex));
        vertices.remove(vertex);
    }

    private Edge<T> find(Vertex<T> src, Vertex<T> dst) {
        for (Edge<T> edge : edges) {
            if (src == edge.source() && dst == edge.destination())
                return edge;
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
        } else
            edges.add(edge);

        if (!directed) {
            existed = find(edge.destination(), edge.source());
            if (existed != null) {
                existed.weight(edge.weight());
                existed.data(edge.data());
            } else
                edges.add(new Edge<>(edge.destination(), edge.source(), edge.data(), edge.weight()));
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
        if (!directed)
            edges.removeIf(edge -> (edge.source() == dst && edge.destination() == src));
    }

    /**
     * GetEdge (v1, v2) возвращает адрес дескриптора ребра соединяющего вершины, заданные дескрипторами v1 и v2
     */
    public Edge<T> get(Vertex<T> src, Vertex<T> dst) {
        if (!weighted)
            return null;
        for (Edge<T> edge : edges) {
            if (edge.source() == src && edge.destination() == dst)
                return edge;
        }
        return null;
    }

    public Edge<T> get(int srcIndex, int dstIndex) {
        Vertex<T> src = vertices.get(srcIndex);
        Vertex<T> dst = vertices.get(dstIndex);
        return get(src, dst);
    }

    public Vertex<T> get(int index) {
        if (index < 0 || index >= vertices.size())
            return null;
        return vertices.get(index);
    }
}
