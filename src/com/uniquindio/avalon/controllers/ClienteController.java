package com.uniquindio.avalon.controllers;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ClienteController {

	 @FXML
	    private TextField tfClaveSelec;

	    @FXML
	    private TextField tfCorreo;

	    @FXML
	    private HBox panelSuperior;

	    @FXML
	    private TextField tfNicknameSelec;

	    @FXML
	    private AnchorPane panelDatos;

	    @FXML
	    private Label lbCorreoSelec;

	    @FXML
	    private TextField tfCedulaSelec;

	    @FXML
	    private AnchorPane panelTabla;

	    @FXML
	    private TableColumn<?, ?> columCorreoCliente;

	    @FXML
	    private TextField tfNickname;

	    @FXML
	    private TableColumn<?, ?> columNicknameCliente;

	    @FXML
	    private TableView<?> tablaListado;

	    @FXML
	    private Button btnBorrar;

	    @FXML
	    private Button btnLimpiar;

	    @FXML
	    private Label labelSuperiorListado;

	    @FXML
	    private Label lbNicknameSelec;

	    @FXML
	    private TextField tfBuscar;

	    @FXML
	    private Button btnAgregar;

	    @FXML
	    private TextField tfCedula;

	    @FXML
	    private TableColumn<?, ?> columClaveCliente;

	    @FXML
	    private Label lbSelecClave;

	    @FXML
	    private Label lbCedulaSelec;

	    @FXML
	    private Button btnGuardar;

	    @FXML
	    private TableColumn<?, ?> columCedulaCliente;

	    @FXML
	    private PasswordField tfClave;

	    @FXML
	    private TextField tfSelecCorreo;

    @FXML
    void keyListener(ActionEvent event) {

    }

    @FXML
    void actionListener(ActionEvent event) {

    }
    
    @FXML
	void initialize() {
		lbCedulaSelec.setVisible(false);
		lbSelecClave.setVisible(false);
		lbCorreoSelec.setVisible(false);
		lbNicknameSelec.setVisible(false);
		tfCedulaSelec.setVisible(false);
		tfClaveSelec.setVisible(false);
		tfSelecCorreo.setVisible(false);
		tfNicknameSelec.setVisible(false);
		btnBorrar.setVisible(false);
		btnGuardar.setVisible(false);
		//inicializarVentana();
		colorIconos();
	}
    
	public void colorIconos() {
		URL iconBucar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconAgregar.png");
		URL iconLimpiar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconLimpiar.png");
		URL iconGuardar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconGuardar.png");
		URL iconBorrar = getClass().getResource("/com/uniquindio/avalon/imagenes/iconBorrar.png");
		Image imagenBuscar = new Image(iconBucar.toString(), 24, 24, false, true);
		Image imagenBorrar = new Image(iconBorrar.toString(), 24, 24, false, true);
		Image imagenGuardar = new Image(iconGuardar.toString(), 24, 24, false, true);
		Image imagenLimpiar = new Image(iconLimpiar.toString(), 24, 24, false, true);

		btnBorrar.setGraphic(new ImageView(imagenBorrar));
		btnGuardar.setGraphic(new ImageView(imagenGuardar));
		btnAgregar.setGraphic(new ImageView(imagenBuscar));
		btnLimpiar.setGraphic(new ImageView(imagenLimpiar));
	}
  
    

}
