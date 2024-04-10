package com.example.dsagraph.graphs;

import java.util.Objects;

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
    public Vertex() {
        label = "";
    }
    public Vertex(T data) {
        this.data = data;
        label = "";
    }

    /**
     * Конструктор(label): label - имя вершины
     */
    Vertex(String label) {
        this.label = label;
    }

    Vertex(String label, T data) {
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
}
