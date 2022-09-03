package com.example.javagroup_10;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomepageController implements Initializable {
    @FXML
    private Button btn_log_out;

    @FXML
    private Button btn_checkresult;
    @FXML
    private Button btn_checkbio;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_log_out.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Database.changeScene(actionEvent, "log-in.fxml", "Login");
            }
        });

        btn_checkresult.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Database.changeScene(actionEvent, "checkresult.fxml", "Result-Checker");
            }
        });
    }
}
