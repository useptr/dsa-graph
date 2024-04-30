package com.example.dsagraph.graphs;

public class Edge<T> {
    /**
     * v1 - дескриптор вершины, из которой исходит ребро
     */
    private Vertex<T> src;

    /**
     * v2 - дескриптор вершины, в которую входит ребро
     */
    private Vertex<T> dst;

    /**
     * v2 - дескриптор вершины, в которую входит ребро
     */
    private double weight = Double.MAX_VALUE;

    /**
     * data - данные, связанные с ребром
     */
    private T data;

    public Edge() {}
    /**
     * Конструктор (v1, v2): v1 - дескриптор вершины, из которой исходит ребро,
     * v2 - дескриптор вершины, в которую входит ребро
     */
    public Edge(Vertex<T> src, Vertex<T> dst) {
        this.src = src;
        this.dst = dst;
    }

    /**
     * Конструктор (v1, v2, w): v1 - дескриптор вершины, из которой исходит ребро,
     * v2 - дескриптор вершины, в которую входит ребро, w - вес ребра
     */
    public Edge(Vertex<T> src, Vertex<T> dst, double weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public Edge(Vertex<T> src, Vertex<T> dst, T data, double weight) {
        this.src = src;
        this.dst = dst;
        this.data = data;
        this.weight = weight;
    }

    /**
     * SetW (w) - задание веса ребро
     */
    public void weight(double weight) {
        this.weight = weight;
    }

    /**
     * SetData (data) - изменение данных, связанных с ребром
     */
    public void data(T data) {
        this.data = data;
    }

    /**
     * v1( ) - возвращает дескриптор вершины, из которой исходит ребро
     */
    public Vertex<T> source() {
        return src;
    }

    /**
     * v2( ) - возвращает дескриптор вершины, в которую входит ребро
     */
    public Vertex<T> destination() {
        return dst;
    }

    /**
     * GetW ( ) - возвращает вес ребра
     */
    public double weight() {
        return weight;
    }

    /**
     * GetData ( ) - возвращает данные, связанные с ребром
     */
    public T data() {
        return data;
    }
}
