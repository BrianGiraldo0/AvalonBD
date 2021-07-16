package com.uniquindio.avalon.controllers;

import java.net.URL;
import java.sql.SQLException;

import com.uniquindio.avalon.database.Database;
import com.uniquindio.avalon.logica.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.DatePicker;

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
    private TableColumn<?, ?> columInicioGarantiaProducto;

    @FXML
    private TableColumn<?, ?> columFinGarantiaProducto;
    
    @FXML
    private DatePicker tfFechaInicioGarantia;

    @FXML
    private DatePicker tfFechaFinGarantia;

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
 	
 	public void inicializarTabla() {
		columnCodigoProducto.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		columDescripcionProducto.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		columNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columInicioGarantiaProducto.setCellValueFactory(new PropertyValueFactory<>("fechaInicioGarantia"));
		columFinGarantiaProducto.setCellValueFactory(new PropertyValueFactory<>("fechaFinGarantia"));

		tablaListado.setRowFactory(tv -> {
			TableRow<Cliente> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					Cliente rowData = row.getItem();
					select = rowData;
					if (rowData != null) {
						tfCedulaSelec.setText(select.getCedula());
						tfClaveSelec.setText(select.getClave());
						tfNicknameSelec.setText(select.getNickname());
						tfSelecCorreo.setText(select.getCorreo());
						lbCedulaSelec.setVisible(true);
						lbSelecClave.setVisible(true);
						lbCorreoSelec.setVisible(true);
						lbNicknameSelec.setVisible(true);
						tfCedulaSelec.setVisible(true);
						tfClaveSelec.setVisible(true);
						tfSelecCorreo.setVisible(true);
						tfNicknameSelec.setVisible(true);
						btnBorrar.setVisible(true);
						btnGuardar.setVisible(true);
					}
				}
			});

			return row;
		});

		try {
			listaClientes = Database.loadClients();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Cliente> listaTabla = FXCollections.observableArrayList(listaClientes);
		tablaListado.setItems(listaTabla);
	}

	public void actualizarTabla() {
		try {
			listaClientes = Database.loadClients();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Cliente> listaTabla = FXCollections.observableArrayList(listaClientes);
		tablaListado.setItems(listaTabla);
	}
    
  

}
