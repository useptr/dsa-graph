package com.example.dsagraph;

import com.example.dsagraph.graphs.AbstractGraph;
import com.example.dsagraph.graphs.SimpleGraph;
import com.example.dsagraph.graphs.Vertex;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GraphController {
    private SimpleGraph<String> graph = new SimpleGraph<>(4, 4, true, AbstractGraph.Type.LIST_GRAPH);
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
        updateGraphView();
    }

//    public void switchToSceneStartMenu(ActionEvent event) throws IOException {
//        root = FXMLLoader.load(getClass().getResource("menu.fxml"));
//        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        scene = new Scene(root, 800, 600);
//        stage.setScene(scene);
//        stage.show();
//    }

    private void updateGraphView() {
        graph.render();
        String path = System.getProperty("user.dir") + graphImagePath;
        Image image = new Image(path);
        ImageViewGraph.setFitWidth(image.getWidth());
        ImageViewGraph.setFitHeight(image.getHeight());
        ImageViewGraph.setImage(image);
    }
    public void setGraph(SimpleGraph<String> graph) {
        this.graph = graph;
        updateGraphView();
    }
    @FXML
    private void graphTask1() {
        List<List<Integer>> edges = graph.task1();
        String result = "Список рёбер в порядке Эйлерова цикла ";
        for (List<Integer> edge : edges) {
            result+= edge.get(0) + " - " + edge.get(1);
            if (edges.size()-1 != edge.indexOf(edge))
                result+=", ";
        }
        LabelMsg.setText(result);
    }
    @FXML
    private void graphTask2() {
        Vertex<String> vertex = graph.task2();
        LabelMsg.setText("Центр взвешенного орграфа " + graph.get(vertex));
    }
    @FXML
    private void graphVerticesBtnClick() {
        LabelMsg.setText("Вершин в графе: " + graph.vertices());
    }
    @FXML
    private void graphEdgesBtnClick() {
        LabelMsg.setText("Ребер в графе: " + graph.edges());
    }
    @FXML
    private void graphAddVertexBtnClick() {
        String label = TextFieldVertexLabel.getText();
        String data = TextFieldVertexData.getText();
        graph.add(label, data);

        updateGraphView();
    }
    private Integer parseIntFromTextField(TextField textField) {
        return Integer.parseInt(textField.getText());
    }
    @FXML
    private void graphRemoveVertexBtnClick() {
                try {
                    int id = parseIntFromTextField(TextFieldVertexId);
                    graph.remove(graph.get(id));

                    updateGraphView();
                } catch (NumberFormatException e) {
//                    System.out.println("Invalid integer input");
                }
    }
    @FXML
    private void graphAddEdgeBtnClick() {
        try {
            int src = parseIntFromTextField(TextFieldSrcVertexId);
            int dst = parseIntFromTextField(TextFieldDstVertexId);
            double weight = Double.parseDouble(TextFieldEdgeWeight.getText());
            String data = TextFieldEdgeData.getText();
            graph.add(graph.get(src), graph.get(dst), data, weight);

            updateGraphView();
        } catch (NumberFormatException e) {
//                    System.out.println("Invalid double input");
        }
    }
    @FXML
    private void graphRemoveEdgeBtnClick() {
        try {
            int src = parseIntFromTextField(TextFieldSrcVertexId);
            int dst = parseIntFromTextField(TextFieldDstVertexId);
            graph.remove(graph.get(src), graph.get(dst));

            updateGraphView();
        } catch (NumberFormatException e) {
//                    System.out.println("Invalid integer input");
        }
    }
}
