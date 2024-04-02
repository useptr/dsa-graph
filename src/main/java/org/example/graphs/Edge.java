package org.example.graphs;

public class Edge<T> {
    public Vertex<T> src; // v1 - дескриптор вершины, из которой исходит ребро,
    public Vertex<T> dst; // v2 - дескриптор вершины, в которую входит ребро,
    public int weight = -1; // v2 - дескриптор вершины, в которую входит ребро,
    public T data; // data - данные, связанные с ребром,
    public Edge(Vertex<T> src, Vertex<T> dst) { // Конструктор (v1, v2): v1 - дескриптор вершины, из которой исходит ребро, v2 - дескриптор вершины, в которую входит ребро
        this.src = src;
        this.dst = dst;
    }
    public Edge(Vertex<T> src, Vertex<T> dst, int weight) { // Конструктор (v1, v2, w): v1 - дескриптор вершины, из которой исходит ребро, v2 - дескриптор вершины, в которую входит ребро, w - вес ребра,
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public void weight(int weight) {
        this.weight = weight;
    } // SetW (w) - задание веса ребра
    public void data(T data) {
        this.data = data;
    } // SetData (data) - изменение данных, связанных с ребром,
    public Vertex<T> source() { // v1( ) - возвращает дескриптор вершины, из которой исходит ребро,
        return src;
    }

    public Vertex<T> destination() { // v2( ) - возвращает дескриптор вершины, в которую входит ребро,
        return dst;
    }
    public int weight() {
        return weight;
    } // GetW ( ) - возвращает вес ребра,

    public T data() {
        return data;
    } // GetData ( ) - возвращает данные, связанные с ребром.
}
