package org.example;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkTarget;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.example.graphs.AbstractGraph;
import org.example.graphs.Edge;
import org.example.graphs.SimpleGraph;
import org.example.graphs.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.*;

public class Main {
    public static void main(String[] args) {

        //SimpleGraph<Integer> listGraph = new SimpleGraph<>(4,  4, true, AbstractGraph.Type.LIST_GRAPH);

        SimpleGraph<Integer> matrixGraph = new SimpleGraph<>(4, 4, true, AbstractGraph.Type.MATRIX_GRAPH);
        System.out.println(matrixGraph);
//        for (AbstractGraph<Integer>.VertexIterator it = matrixGraph.begin(); it.notEqual(matrixGraph.end()); it.next()) {
//            Vertex<Integer> vertex = it.get();
//            System.out.println("V");
//        }
//
//        for (AbstractGraph<Integer>.EdgeIterator it = matrixGraph.beginEdge(); it.notEqual(matrixGraph.endEdge()); it.next()) {
//            Edge<Integer> edge = it.get();
//            System.out.println("E");
//        }


//        matrixGraph.add(matrixGraph.get(0), matrixGraph.get(1), 228);
        //listGraph.add(listGraph.get(1), listGraph.get(0));
//        System.out.println(matrixGraph);
//        matrixGraph.render();
    }
}