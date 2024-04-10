module com.example.dsagraph {
    requires javafx.controls;
    requires javafx.fxml;
    requires guru.nidi.graphviz;


    opens com.example.dsagraph to javafx.fxml;
    exports com.example.dsagraph;
}