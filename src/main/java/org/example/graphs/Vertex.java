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
    String label; // имя вершины,
    T data; // данные, связанные с вершиной,
    // Конструктор ():поле name не определено,
    public Vertex(T data) {
        this.data = data;
    }
    Vertex(String label) {
        this.label = label;
    } // Конструктор (label): label - имя вершины
    Vertex(String label, T data) {
        this.label = label;
        this.data = data;
    }
    public String label() {
        return label;
    } // GetData ( ) - возвращает данные, связанные с вершиной,
    public T data() {
        return data;
    } // GetName ( ) - возвращает имя вершины,
    public void setLabel(String label) {
        this.label = label;
    } // SetName (name) – задает имя вершины,
    public void setData(T data) {
        this.data = data;
    } // SetData (data) – записывает данные data в дескриптор вершины
}
