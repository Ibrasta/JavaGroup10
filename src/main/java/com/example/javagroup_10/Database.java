package com.example.javagroup_10;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Database {

    public static void changeScene(ActionEvent event, String fxmlFile, String title){
        Parent root = null;


        try {
                root = FXMLLoader.load(Database.class.getResource(fxmlFile));
            } catch (IOException e){
                e.printStackTrace();
            }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root, 700, 500));
        stage.show();
    }



    public static void signUpUser(ActionEvent event, String fName, String lName, String username, String password){
        Connection connection = null;
        PreparedStatement psInsert = null;
        PreparedStatement psCheckUserExist = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/group10_db",
                    "root", "");
            psCheckUserExist = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckUserExist.setString(1, username);
            resultSet = psCheckUserExist.executeQuery();

            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists!");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You cannot use this username");
                alert.show();
            } else {

                String sql = "INSERT INTO users (fName, lName, username, password) VALUES (?,?,?,?)";
                psInsert = connection.prepareStatement(sql);
                psInsert.setString(1, fName);
                psInsert.setString(2, lName);
                psInsert.setString(3, username);
                psInsert.setString(4, password);

                int row = psInsert.executeUpdate();
                if (row > 0){
                    System.out.println("A new user was inserted successfully!");

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("A new user added successfully!");
                    alert.show();

                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }    }



    public static void logInUser(ActionEvent event, String username, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/group10_db",
                    "root", "");
            preparedStatement = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("User not found");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect!");
                alert.show();
            } else {
                while (resultSet.next()) {
                    String retrievedPassword = resultSet.getString("password");

                    if (retrievedPassword.equals(password)) {
                        changeScene(event, "welcomepage.fxml", "Welcome");
                    } else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Provided credentials are incorrect!");
                        alert.show();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
