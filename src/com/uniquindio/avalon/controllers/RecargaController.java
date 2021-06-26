package com.uniquindio.avalon.controllers;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
/**
 * Controlador de Recarga
 * 
 *
 */
public class RecargaController {

	
	@FXML
    private TextField tfFecha;

    @FXML
    private TextField tfTotal;

    @FXML
    private HBox panelSuperior;

    @FXML
    private TextField tfCodigo;

    @FXML
    private AnchorPane panelDatos;

    @FXML
    private TextField tfValorRecargaSelec;

    @FXML
    private TableColumn<?, ?> columnCodigoRecarga;

    @FXML
    private TextField tfTotalSelec;

    @FXML
    private TableColumn<?, ?> columnTotalRecarga;

    @FXML
    private TextField tfCedulaClienteSelec;

    @FXML
    private Label lbCodigoSelec;

    @FXML
    private AnchorPane panelTabla;

    @FXML
    private TextField tfFechaSelec;

    @FXML
    private Label lbSelecCedulaCliente;

    @FXML
    private TableView<?> tablaListado;

    @FXML
    private TableColumn<?, ?> columFechaRecarga;

    @FXML
    private Button btnBorrar;

    @FXML
    private Label lbValorRecargaSelec;

    @FXML
    private Label lbCedulaEmpleadoSelec;

    @FXML
    private TableColumn<?, ?> columCedulaEmpleado;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TextField tfCedulaCliente;

    @FXML
    private Label lbFechaSelec;

    @FXML
    private Label lbSelecTotal;

    @FXML
    private TextField tfSelecCedulaEmpleado;

    @FXML
    private Label labelSuperiorListado;

    @FXML
    private TextField tfCedulaEmpleado;

    @FXML
    private TextField tfBuscar;

    @FXML
    private Button btnAgregar;

    @FXML
    private TextField tfCodigoSelec;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<?, ?> columCedulaCliente;

    @FXML
    private TableColumn<?, ?> columnValorRecarga;

    @FXML
    private TextField tfValorRecarga;

    
    
    
    
    
    
    @FXML
 	void initialize() {
 		lbSelecCedulaCliente.setVisible(false);
 		lbCedulaEmpleadoSelec.setVisible(false);
 		lbFechaSelec.setVisible(false);
 		lbCodigoSelec.setVisible(false);
 		lbValorRecargaSelec.setVisible(false);
 		lbSelecTotal.setVisible(false);
 		tfTotalSelec.setVisible(false);
 		tfValorRecargaSelec.setVisible(false);
 		tfFechaSelec.setVisible(false);
 		tfCedulaClienteSelec.setVisible(false);
 		tfSelecCedulaEmpleado.setVisible(false);
 		tfCodigoSelec.setVisible(false);
 		
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
    
    
    
    
    
    @FXML
    void keyListener(ActionEvent event) {

    }

    @FXML
    void actionListener(ActionEvent event) {

    }
	
	
	
	
	
	
	
	
	
	
	
}
