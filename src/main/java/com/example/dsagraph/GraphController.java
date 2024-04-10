package com.example.dsagraph;

import com.example.dsagraph.graphs.AbstractGraph;
import com.example.dsagraph.graphs.SimpleGraph;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GraphController {
    private SimpleGraph<Integer> listGraph = new SimpleGraph<>(10, 10, true, AbstractGraph.Type.LIST_GRAPH);
    @FXML
    private TextField TextFieldVertexLabel;
    @FXML
    private TextField TextFieldVertexData;
    @FXML
    private TextField TextFieldVertexId;
    @FXML
    private TextField TextFieldEdgeData;
    @FXML
    private TextField TextFieldEdgeWeight;
    @FXML
    private TextField TextFieldSrcVertexId;
    @FXML
    private TextField TextFieldDstVertexId;
    @FXML
    private Label LabelMsg;

    @FXML
    private ImageView ImageViewGraph;

    private final String graphImagePath = "\\example\\graph.png";
    @FXML
    private void initialize() {
        listGraph.render();
        String path = System.getProperty("user.dir") + graphImagePath;
        Image image = new Image(path);
        ImageViewGraph.setFitWidth(image.getWidth());
        ImageViewGraph.setFitHeight(image.getHeight());
        ImageViewGraph.setImage(image);
    }

    @FXML
    private void graphVerticesBtnClick() {
        LabelMsg.setText("Вершин в графе: " + listGraph.vertices());
    }
    @FXML
    private void graphEdgesBtnClick() {
        LabelMsg.setText("Ребер в графе: " + listGraph.edges());
    }
}
