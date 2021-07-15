package com.uniquindio.avalon.controllers;

import java.io.IOException;

import javax.sound.midi.ControllerEventListener;

import com.uniquindio.avalon.application.Main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private Button btnIngresar;

    @FXML
    private TextField tfNickname;

    @FXML
    private PasswordField tfPassword;
    
    
    @FXML
    void onClick(MouseEvent event) throws IOException {
    	if(tfNickname.getText().equalsIgnoreCase("admin") && tfPassword.getText().equals("admin")) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uniquindio/avalon/interfaces/Principal.fxml"));
    		Parent root = loader.load();
            Scene scene = new Scene(root);
          	PrincipalController controller = loader.getController();
            root.setStyle("-fx-background-color: #FFFFFF");
            Main.stage.setScene(scene);
    	}
    }
}
