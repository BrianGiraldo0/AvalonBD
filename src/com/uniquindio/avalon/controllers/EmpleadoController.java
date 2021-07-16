package com.uniquindio.avalon.controllers;

import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Timer;

import com.uniquindio.avalon.database.Database;
import com.uniquindio.avalon.logica.Cliente;
import com.uniquindio.avalon.logica.Empleado;
import com.uniquindio.avalon.logica.Cliente;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

public class EmpleadoController {

    @FXML
    private Label lbCedulaSelec;

    @FXML
    private TextField tfCedulaSelec;

    @FXML
    private Label lbDireccionSelec;

    @FXML
    private TextField tfDireccionSelec;

    @FXML
    private TextField tfNombreSelec;

    @FXML
    private Label lbNombreSelec;

    @FXML
    private Label lbCiudadSelec;

    @FXML
    private ComboBox<String> cbCiudadSelec;

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
    private Label lbCorreoSelec;

    @FXML
    private TextField tfCorreoSelec;

    @FXML
    private AnchorPane panelTabla;

    @FXML
    private TableView<Empleado> tablaListado;

    @FXML
    private TableColumn<?, ?> columCedula;

    @FXML
    private TableColumn<?, ?> columNombre;

    @FXML
    private TableColumn<?, ?> columCorreo;

    @FXML
    private TableColumn<?, ?> columDireccion;

    @FXML
    private TableColumn<?, ?> columCiudad;

    @FXML
    private AnchorPane panelDatos;

    @FXML
    private TextField tfCedula;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfCorreo;

    @FXML
    private TextField tfDireccion;

    @FXML
    private ComboBox<?> cbCiudad;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnLimpiar;
    
    private Empleado select;
    @FXML
    private Label lblNotificacion;
    
    private ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    
    
    @FXML
	void initialize() {
		inicializarTabla();
		limpiarCampos();
		colocarIconos();
//		botonAgregar();
//		botonActualizar();
//		botonEliminar();
//		botonLimpiar();
		buscador();

	}
    
    public void inicializarTabla() {
		columCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
		columNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
		columDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		columCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

		tablaListado.setRowFactory(tv -> {
			TableRow<Empleado> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2) {
					Empleado rowData = row.getItem();
					select = rowData;
					if (rowData != null) {
						tfCedulaSelec.setText(select.getCedula());
						tfCorreoSelec.setText(select.getCorreo());
						tfNombreSelec.setText(select.getNombre());
						tfDireccionSelec.setText(select.getDireccion());
						cbCiudadSelec.setValue(select.getCiudad());
						cbCiudadSelec.setVisible(true);
						lbCedulaSelec.setVisible(true);
						lbCorreoSelec.setVisible(true);
						lbNombreSelec.setVisible(true);
						lbDireccionSelec.setVisible(true);
						lbCedulaSelec.setVisible(true);
						lbCiudadSelec.setVisible(true);
						tfCedulaSelec.setVisible(true);
						tfCorreoSelec.setVisible(true);
						tfNombreSelec.setVisible(true);
						tfDireccionSelec.setVisible(true);
						btnBorrar.setVisible(true);
						btnGuardar.setVisible(true);
					}
				}
			});

			return row;
		});

		try {
			listaEmpleados = Database.loadEmpleados();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Empleado> listaTabla = FXCollections.observableArrayList(listaEmpleados);
		tablaListado.setItems(listaTabla);
	}
    
//    public void botonActualizar() {
//		btnGuardar.setOnMouseClicked(e -> {
//			select.setCorreo(tfCorreoSelec.getText());
//			select.setDireccion(tfDireccionSelec.getText());
//			select.setCiudad("");
//			select.setNombre(tfNombreSelec.getText());
//			
//			try {
//				Database.actualizarClient(select);
//			} catch (SQLException ex) {
//				// TODO Auto-generated catch block
//				ex.printStackTrace();
//			}
//			actualizarTabla();
//		});
//
//	}

//	public void botonEliminar() {
//		btnBorrar.setOnMouseClicked(e -> {
//			try {
//				Database.borrarCliente(select);
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			limpiarCampos();
//			select = null;
//			actualizarTabla();
//		});
//	}

	public void botonLimpiar() {
		btnLimpiar.setOnMouseClicked(e -> {
			limpiarCampos();
		});
	}

//	public void botonAgregar() {
//		btnAgregar.setOnMouseClicked(e -> {
//			String cedula = tfCedula.getText();
//			String correo = tfCorreo.getText();
//			String clave = tfClave.getText();
//			String nickname = tfNickname.getText();
//			int saldo = 0;
//			if (!verificarExistencia(cedula, nickname)) {
//				Cliente cliente = new Cliente(cedula, nickname, clave, correo, saldo);
//				try {
//					Database.addClient(cliente);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//				actualizarTabla();
//				limpiarCampos();
//			} else {
//				lblNotificacion.setVisible(true);
//			}
//
//		});
//	}

	public void actualizarTabla() {
		try {
			listaEmpleados = Database.loadEmpleados();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<Empleado> listaTabla = FXCollections.observableArrayList(listaEmpleados);
		tablaListado.setItems(listaTabla);
	}

	
	
	public boolean verificarExistencia(String cedula) {
		for (Empleado c : listaEmpleados) {
			if (c.getCedula().equals(cedula)) {
				return true;
			}
		}

		return false;
	}
    
    public void limpiarCampos() {
		tfBuscar.setText("");
		tfCedula.setText("");
		tfNombre.setText("");
		tfDireccion.setText("");
		tfCedulaSelec.setText("");
		tfCorreoSelec.setText("");
		tfNombreSelec.setText("");
		tfDireccionSelec.setText("");
		cbCiudadSelec.setVisible(true);
		lbCedulaSelec.setVisible(true);
		lbCorreoSelec.setVisible(true);
		lbNombreSelec.setVisible(true);
		lbDireccionSelec.setVisible(true);
		lbCedulaSelec.setVisible(true);
		lbCiudadSelec.setVisible(true);
		tfCedulaSelec.setVisible(true);
		tfCorreoSelec.setVisible(true);
		tfNombreSelec.setVisible(true);
		tfDireccionSelec.setVisible(true);
		btnBorrar.setVisible(true);
		btnGuardar.setVisible(true);
		
		tfCedulaSelec.setEditable(false);
		select = null;

	}
    
    
    
    public void colocarIconos() {
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
    
    public void buscador() {
		tfBuscar.setOnKeyPressed(e -> {
			if (tfBuscar.isFocused()) {
				if (tfBuscar.getText() != null) {

					Timer timer = new Timer(1, new ActionListener() {

						@Override
						public void actionPerformed(java.awt.event.ActionEvent e) {
							tablaListado.setItems(getListFound());

						}
					});
					timer.start();
					timer.setRepeats(false);

				}

			}
		});
	}

	public ObservableList<Empleado> getListFound() {

		ObservableList<Empleado> listaTabla = FXCollections.observableArrayList(listaEmpleados);

		ObservableList<Empleado> founds = FXCollections.observableArrayList();

		for (Empleado c : listaTabla) {
			if (c.getNombre().toLowerCase().contains(tfBuscar.getText().toLowerCase())
					|| c.getCedula().toLowerCase().contains(tfBuscar.getText().toLowerCase())) {
				founds.add(c);
			}
		}

		return founds;
	}

}
