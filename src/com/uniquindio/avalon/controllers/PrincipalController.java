package com.uniquindio.avalon.controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class PrincipalController {

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnEmpleados;

    @FXML
    private Button btnRecargas;

    @FXML
    private Pane panelCambiante;

    @FXML
    private Button btnClientes;
    
    public  static Pane panel;
    
    @FXML
    void keyListener(ActionEvent event) {

    }

    @FXML
    void actionListener(ActionEvent event) {

    }
    
    @FXML
    void initialize() throws IOException{
    	Parent cliente = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Cliente.fxml"));
    	panelCambiante.getChildren().add(cliente);
    	panel = panelCambiante;
    	
    	
    	
    }
    
    


   

}
