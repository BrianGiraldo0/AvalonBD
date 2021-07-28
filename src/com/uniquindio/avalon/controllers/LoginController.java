package com.uniquindio.avalon.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.sound.midi.ControllerEventListener;

import com.uniquindio.avalon.application.Main;
import com.uniquindio.avalon.database.Database;
import com.uniquindio.avalon.logica.Administrador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label lblNoti;
    
    
    @FXML
    void onClick(MouseEvent event) throws IOException, SQLException {
    	Administrador admin = Database.loadAdministrador(tfNickname.getText());
    	if(admin!=null) {
    		if(tfPassword.getText().equals(admin.getClave())){
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/uniquindio/avalon/interfaces/Principal.fxml"));
        		Parent root = loader.load();
                Scene scene = new Scene(root);
              	PrincipalController controller = loader.getController();
                root.setStyle("-fx-background-color: #FFFFFF");
                Main.stage.setScene(scene);
    		}else {
    			lblNoti.setVisible(true);
    		}
    	}else {
			lblNoti.setVisible(true);
    	}
    	
    
    }
}
