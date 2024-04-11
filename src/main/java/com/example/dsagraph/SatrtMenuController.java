package com.example.dsagraph;

import com.example.dsagraph.graphs.AbstractGraph;
import com.example.dsagraph.graphs.SimpleGraph;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

public class SatrtMenuController {
    private Scene scene;
    private Stage stage;
    private Parent root;

    @FXML
    private ToggleGroup graphType;
    @FXML
    private CheckBox directedCheckBox;
    @FXML
    private TextField edgesTextField;
    @FXML
    private TextField verticesTextField;

    @FXML
    public void switchToSceneGraph(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("graph.fxml"));
        root = fxmlLoader.load();
        GraphController graphController = fxmlLoader.getController();

        RadioButton selected = (RadioButton) graphType.getSelectedToggle();
        String GraphForm = selected.getText();

        AbstractGraph.Type type = AbstractGraph.Type.LIST_GRAPH;
        if (GraphForm.equals("M-граф" ))
            type = AbstractGraph.Type.MATRIX_GRAPH;

        int edges = 0;
        int vertices = 0;
        boolean directed = false;
        if (directedCheckBox.isSelected())
            directed = true;

        try {
            vertices = Integer.parseInt(verticesTextField.getText());
            edges = Integer.parseInt(edgesTextField.getText());
        } catch (NumberFormatException e) {
//                    System.out.println("Invalid integer input");
        }
//        System.out.println(vertices + " " + edges);

        SimpleGraph<String> graph;
        if (vertices > 0 && edges > 0)
            graph = new SimpleGraph<>(vertices, edges, directed, type);
        else if (vertices > 0)
            graph = new SimpleGraph<>(vertices, directed, type);
        else
            graph = new SimpleGraph<>();

        graphController.setGraph(graph);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
