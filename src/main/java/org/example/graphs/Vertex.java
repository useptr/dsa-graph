package org.example.graphs;

public class Vertex<T> {
    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Vertex)) {
            return false;
        }
        Vertex<?> v = (Vertex<?>) obj;
        return label.equals(v.label) && data.equals(data);
    }
    String label;
    T data;
    public Vertex(T data) {
        this.data = data;
    }
    Vertex(String label) {
        this.label = label;
    }
    Vertex(String label, T data) {
        this.label = label;
        this.data = data;
    }
    public String label() {
        return label;
    }
    public T data() {
        return data;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setData(T data) {
        this.data = data;
    }
}
