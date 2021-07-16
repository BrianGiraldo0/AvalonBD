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
    
    @FXML
    private Button btnReportes;
    
    public  static Pane panel;
    
    @FXML
    void keyListener(ActionEvent event) {

    }

    @FXML
    void actionListener(ActionEvent event) {

    }
    
    @FXML
    void initialize() throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Cliente.fxml"));
    	panelCambiante.getChildren().add(root);
    	panel = panelCambiante;
    	btnClientes.setStyle("-fx-background-color: #90EE90");
		
    	clientes();
    	empleados();
    	recargas();
    	productos();
    	reportes();
    	
    	
    }
    
    public void clientes() {
    	btnClientes.setOnMouseClicked(e->{
    		btnRecargas.setStyle("-fx-background-color: #F0F7FF");
    		btnEmpleados.setStyle("-fx-background-color: #F0F7FF");
    		btnProductos.setStyle("-fx-background-color: #F0F7FF");
    		btnReportes.setStyle("-fx-background-color: #F0F7FF");
    		btnClientes.setStyle("-fx-background-color: #90EE90");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Cliente.fxml"));
				panelCambiante.getChildren().clear();
				panelCambiante.getChildren().add(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    }
    
    public void empleados() {
    	btnEmpleados.setOnMouseClicked(e->{
    		btnRecargas.setStyle("-fx-background-color: #F0F7FF");
    		btnEmpleados.setStyle("-fx-background-color: #90EE90");
    		btnProductos.setStyle("-fx-background-color: #F0F7FF");
    		btnReportes.setStyle("-fx-background-color: #F0F7FF");
    		btnClientes.setStyle("-fx-background-color: #F0F7FF");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Empleado.fxml"));
				panelCambiante.getChildren().clear();
				panelCambiante.getChildren().add(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    }
    
    public void recargas() throws IOException {
    	btnRecargas.setOnMouseClicked(e->{
    		btnRecargas.setStyle("-fx-background-color: #90EE90");
    		btnEmpleados.setStyle("-fx-background-color: #F0F7FF");
    		btnProductos.setStyle("-fx-background-color: #F0F7FF");
    		btnReportes.setStyle("-fx-background-color: #F0F7FF");
    		btnClientes.setStyle("-fx-background-color: #F0F7FF");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Recarga.fxml"));
				panelCambiante.getChildren().clear();
				panelCambiante.getChildren().add(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
    	});
    }
    
    public void productos() {
    	btnProductos.setOnMouseClicked(e->{
    		btnRecargas.setStyle("-fx-background-color: #F0F7FF");
    		btnEmpleados.setStyle("-fx-background-color: #F0F7FF");
    		btnProductos.setStyle("-fx-background-color: #90EE90");
    		btnReportes.setStyle("-fx-background-color: #F0F7FF");
    		btnClientes.setStyle("-fx-background-color: #F0F7FF");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Producto.fxml"));
				panelCambiante.getChildren().clear();
				panelCambiante.getChildren().add(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    }
    
    public void reportes() {
    	btnReportes.setOnMouseClicked(e->{
    		btnRecargas.setStyle("-fx-background-color: #F0F7FF");
    		btnEmpleados.setStyle("-fx-background-color: #F0F7FF");
    		btnProductos.setStyle("-fx-background-color: #F0F7FF");
    		btnReportes.setStyle("-fx-background-color: #90EE90");
    		btnClientes.setStyle("-fx-background-color: #F0F7FF");
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/com/uniquindio/avalon/interfaces/Reporte.fxml"));
				panelCambiante.getChildren().clear();
				panelCambiante.getChildren().add(root);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    }
    
    


   

}
