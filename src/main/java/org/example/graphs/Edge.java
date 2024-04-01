package org.example.graphs;

public class Edge<T> {
    public Vertex<T> src;
    public Vertex<T> dst;
    public int weight = -1;
    public T data;
    public Edge(Vertex<T> v1, Vertex<T> v2, int weight) {
        this.src = v1;
        this.dst = v2;
        this.weight = weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setData(T data) {
        this.data = data;
    }
    public int w() {
        return weight;
    }

    public T data() {
        return data;
    }
}
