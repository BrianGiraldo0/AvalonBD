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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * Controlador de Producto
 * 
 *
 */
public class ProductoController {

    @FXML
    private Label lbCodigoSelec;

    @FXML
    private TextField tfCodigoSelec;

    @FXML
    private Label lbPrecioSelec;

    @FXML
    private TextField tfPrecioSelec;

    @FXML
    private TextField tfNombreSelec;

    @FXML
    private Label lbNombreSelec;

    @FXML
    private TextField tfNitProveedorSelec;

    @FXML
    private Label lbNitPreveedorSelec;

    @FXML
    private HBox panelSuperior;

    @FXML
    private TextField tfBuscar;

    @FXML
    private Label labelSuperiorListado;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnBorrar;

    @FXML
    private Label lbDescripcionSelec;

    @FXML
    private TextField tfDescripcionSelec;

    @FXML
    private AnchorPane panelTabla;

    @FXML
    private TableView<?> tablaListado;

    @FXML
    private TableColumn<?, ?> columnCodigoProducto;

    @FXML
    private TableColumn<?, ?> columNombreProducto;

    @FXML
    private TableColumn<?, ?> columDescripcionProducto;

    @FXML
    private TableColumn<?, ?> columPrecioProducto;

    @FXML
    private TableColumn<?, ?> columnProveedorProducto;

    @FXML
    private AnchorPane panelDatos;

    @FXML
    private TextField tfCodigo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfDescripcion;

    @FXML
    private TextField tfPrecio;

    @FXML
    private TextField tfNitProveedor;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnLimpiar;

    @FXML
 	void initialize() {
 		lbNombreSelec.setVisible(false);
 		lbCodigoSelec.setVisible(false);
 		lbPrecioSelec.setVisible(false);
 		lbDescripcionSelec.setVisible(false);
 		lbNitPreveedorSelec.setVisible(false);
 		tfNombreSelec.setVisible(false);
 		tfPrecioSelec.setVisible(false);
 		tfDescripcionSelec.setVisible(false);
 		tfNitProveedorSelec.setVisible(false);
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
    void actionListener(ActionEvent event) {

    }

    @FXML
    void keyListener(KeyEvent event) {

    }

}
