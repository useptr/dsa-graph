package org.example.graphs;

public class Vertex<T> {

    /**
     * Имя вершины
     */
    private String label;

    /**
     * Данные, связанные с вершиной
     */
    private T data;

    /**
     * Конструктор(): поле label не определено,
     */
    public Vertex() {}
    public Vertex(T data) {
        this.data = data;
    }

    /**
     * Конструктор(label): label - имя вершины
     */
    Vertex(String label) {
        this.label = label;
    }

    Vertex(String label, T data) { // TODO Этот конструктор ваще надо? Его нет в задании
        this.label = label;
        this.data = data;
    }

    /**
     * GetName() - возвращает имя вершины
     */
    public String label() {
        return label;
    }

    /**
     * GetData(): возвращает данные, связанные с вершиной
     */
    public T data() {
        return data;
    }

    /**
     * SetName(name) – задает имя вершины
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * SetData(data) – записывает данные data в дескриптор вершины
     */
    public void setData(T data) {
        this.data = data;
    }

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
        return label.equals(v.label) && data.equals(data); // TODO чо за х
    }
}
