package com.example.javagroup_10;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML
    private Button btn_register;
    @FXML
    private TextField txt_fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private Button btn_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Database.changeScene(actionEvent, "log-in.fxml", "Login");
            }
        });


        btn_register.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!txt_fName.getText().trim().isEmpty() && !lName.getText().trim().isEmpty() && !txt_username.getText().trim().isEmpty() && !txt_password.getText().trim().isEmpty()){
                    Database.signUpUser(actionEvent, txt_fName.getText(), lName.getText(), txt_username.getText(), txt_password.getText());
                    txt_password.clear();
                    txt_username.clear();
                    lName.clear();
                    txt_fName.clear();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Enter all information");
                    alert.show();
                }
            }
        });
    }
}
