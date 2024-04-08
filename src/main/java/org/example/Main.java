package org.example;

import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkTarget;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import org.example.graphs.AbstractGraph;
import org.example.graphs.SimpleGraph;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.*;

public class Main {
    public static void main(String[] args) throws IOException {

        SimpleGraph<Integer> listGraph = new SimpleGraph<>(4, false, AbstractGraph.Type.LIST_GRAPH);
        System.out.println(listGraph);

        SimpleGraph<Integer> matrixGraph = new SimpleGraph<>(4, false, AbstractGraph.Type.MATRIX_GRAPH);
        System.out.println(matrixGraph);


        MutableGraph g = mutGraph("example1").setDirected(false);
        // TODO replace key to Vertex
        HashMap<String,MutableNode> nodes = new HashMap<>();
        // TODO nodes fill all vertices
        for (int i = 0; i < 5; ++i) {
            String key = i + ":";
            nodes.put(key, mutNode(key));
        }

        int i = 0;
        // TODO nodes fill all edges
        for (Map.Entry<String, MutableNode> entry : nodes.entrySet()) {
            String key = entry.getKey();
            MutableNode node = entry.getValue();
            if (i > 0) {
                int to = i-1;
                System.out.println(key + " | " + to + ":");
                MutableNode nodeTo = nodes.get(to + ":");
                node.addLink(
                        to(nodeTo).with(Label.of("label: \nweight:"))
                );
            }
            ++i;
        }
        // add all nodes
        nodes.forEach((k, v) -> g.add(v));
        // render
        Graphviz.fromGraph(g).render(Format.PNG).toFile(new File("example/ex1m.png"));
    }
}